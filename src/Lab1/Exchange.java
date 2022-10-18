package Lab1;

import java.util.Scanner;

public class Exchange{
    private static final ExchangeRateTable exchangeRateTable = ExchangeRateTable.getInstance();
    Scanner input = new Scanner(System.in);
    private float valueInitial;
    private float rateInitial;
    private float rateTarget;
    private float factorInitial;
    private float factorTarget;
    private String codeInitial;
    private String codeTarget;

    private void input() throws Exception{
        System.out.println("Numer tabeli: " + exchangeRateTable.getTableNumber());
        System.out.println("Data publikacji tabeli: " + exchangeRateTable.getDateOfPublication());

        System.out.println("Podaj kod waluty zrodlowej: ");
        codeInitial = input.nextLine();
        System.out.println("Podaj kod waluty docelowej: ");
        codeTarget = input.nextLine();
        if( codeInitial.trim().length() != 3 || codeTarget.trim().length() != 3){throw new Exception("Wrong currency code");}
        System.out.println("Podaj wartosc: ");
        valueInitial = Float.parseFloat(input.nextLine());
    }

    public void exchange() throws Exception {
        this.input();
        for(Currency c : exchangeRateTable.getCurrencyList()) {

            if (c.getCurrencyCode().equalsIgnoreCase(codeInitial)) {
                rateInitial = c.getCurrencyRate();
                factorInitial = c.getConversionFactor();
            }
            if (c.getCurrencyCode().equalsIgnoreCase(codeTarget)) {
                rateTarget = c.getCurrencyRate();
                factorTarget = c.getConversionFactor();
            }
        }
        if(rateInitial == 0 || rateTarget == 0){throw new Exception("Currency not found");}

        float valueExchanged = ((rateInitial * factorTarget / (rateTarget * factorInitial)) * valueInitial);
        System.out.printf("Exchanged: %.4f", valueExchanged);

    }
}
