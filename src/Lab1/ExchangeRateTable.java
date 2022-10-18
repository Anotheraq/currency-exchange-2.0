package Lab1;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateTable implements IExchangeRateTable {
    private static ExchangeRateTable instance;
    private List<Currency> currencyList = new ArrayList<>();
    private String dateOfPublication;
    private String tableNumber;

    private ExchangeRateTable() {

    }

    public static ExchangeRateTable getInstance() {
        if (instance == null) {
            instance = new ExchangeRateTable();
        }
        return instance;
    }


    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @Override
    public void addCurrencyToTheList(Currency currency) {
        currencyList.add(currency);
    }
}