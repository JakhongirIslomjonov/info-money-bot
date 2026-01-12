package uz.dev.infomoneybot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dev.infomoneybot.service.CurrencySchedulerService;
import uz.dev.infomoneybot.service.CurrencyService;


@Service
@RequiredArgsConstructor
public class CurrencySchedulerServiceImpl implements CurrencySchedulerService {
    private final CurrencyService currencyService;

    public void scheduleCurrencyUpdate() {
        currencyService.updateCurrencyRates();
    }
}

