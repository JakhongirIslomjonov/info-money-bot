package uz.dev.infomoneybot.service;


import uz.dev.infomoneybot.entity.Currency;
import uz.dev.infomoneybot.enums.CurrencyType;

public interface CurrencyService {
    Currency getCurrencyRate(CurrencyType currencyType);
    void updateCurrencyRates();
}
