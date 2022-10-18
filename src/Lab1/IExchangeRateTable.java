package Lab1;

import java.util.List;

public interface IExchangeRateTable {
    List<Currency> getCurrencyList();
    void addCurrencyToTheList(Currency currency);
}
