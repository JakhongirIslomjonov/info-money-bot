package uz.dev.infomoneybot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.infomoneybot.entity.Currency;
import uz.dev.infomoneybot.enums.CurrencyType;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCode(CurrencyType code);
}