package uz.dev.infomoneybot.service.impl;

import jakarta.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.dev.infomoneybot.dto.CurrencyDTO;
import uz.dev.infomoneybot.entity.Currency;
import uz.dev.infomoneybot.enums.CurrencyType;
import uz.dev.infomoneybot.repository.CurrencyRepository;
import uz.dev.infomoneybot.service.CurrencyService;


@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    private final WebClient webClient;
    private final CurrencyRepository currencyRepository;

    @Value("${currency.api.url}")
    private String apiUrl;

    @Override
    @Cacheable(value = "currencyRates", key = "#currencyType")
    public Currency getCurrencyRate(CurrencyType currencyType) {
        return currencyRepository.findByCode(currencyType)
                .orElseThrow(() -> new RuntimeException("Currency rate not found: " + currencyType));
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 1 * * ?", zone = "Asia/Tashkent")
    public void updateCurrencyRates() {
        log.info("Updating currency rates...");

        try {
            // 1. API javobini DTO listiga o'qish
            Mono<CurrencyDTO[]> response = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(CurrencyDTO[].class)
                    .timeout(Duration.ofSeconds(5));

            CurrencyDTO[] currencyDTOs = response.block();

            Optional.ofNullable(currencyDTOs)
                    .map(Arrays::asList)
                    .ifPresentOrElse(list -> {
                        // 2. Faqat USD va RUB ni filterlash
                        List<CurrencyDTO> filtered = list.stream()
                                .filter(dto ->
                                        "USD".equalsIgnoreCase(dto.getCcy()) ||
                                                "RUB".equalsIgnoreCase(dto.getCcy())
                                )
                                .toList();

                        // 3. DTO -> Entity ga o'tkazish
                        filtered.forEach(dto -> {
                            Currency currency = new Currency();
                            currency.setCode(CurrencyType.valueOf(dto.getCcy())); // USD yoki RUB ga convert
                            currency.setRate(dto.getRate());
                            currency.setDiff(dto.getDiff());
                            currency.setNominal(dto.getNominal());
                            currency.setDate(dto.getDate());

                            currencyRepository.findByCode(currency.getCode())
                                    .ifPresentOrElse(existing -> {
                                        existing.setRate(currency.getRate());
                                        existing.setDiff(currency.getDiff());
                                        currencyRepository.save(existing);
                                    }, () -> currencyRepository.save(currency));
                        });

                        log.info("Currency rates updated successfully.");
                    }, () -> log.warn("No currencies received from API"));

        } catch (Exception e) {
            log.error("Failed to fetch currency rates from API: {}", e.getMessage());
        }
    }

    @PostConstruct
    @Transactional
    public void initCurrencyRates() {
        if (currencyRepository.findByCode(CurrencyType.USD).isEmpty() ||
                currencyRepository.findByCode(CurrencyType.RUB).isEmpty()) {
            log.info("Currency rates not found in database. Fetching from API...");
            updateCurrencyRates();
        } else {
            log.info("Currency rates already exist in database.");
        }
    }
}