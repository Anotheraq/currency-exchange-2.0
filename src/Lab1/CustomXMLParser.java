package Lab1;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.net.URL;

public class CustomXMLParser{

    private static final ExchangeRateTable exchangeRateTable = ExchangeRateTable.getInstance();
    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    private static CustomXMLParser instance = null;

    private CustomXMLParser(){

    }

    public static CustomXMLParser getInstance() {
        if (instance == null) {
            instance = new CustomXMLParser();
        }
        return instance;
    }

    public void XMLParse(String url) throws IOException, ParserConfigurationException, SAXException {

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());

        doc.getDocumentElement().normalize();

        String tableNumber = doc.getElementsByTagName("numer_tabeli").item(0).getTextContent();
        exchangeRateTable.setTableNumber(tableNumber);
        String dateOfPublication = doc.getElementsByTagName("data_publikacji").item(0).getTextContent();
        exchangeRateTable.setDateOfPublication(dateOfPublication);

        NodeList list = doc.getElementsByTagName("pozycja");

        Currency currency = new Currency("polski zloty", 1, "PLN", 1);
        exchangeRateTable.addCurrencyToTheList(currency);
        for(int i = 0; i < list.getLength(); i++){
            Node node = list.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String nazwaWaluty = element.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
                String przelicznik = element.getElementsByTagName("przelicznik").item(0).getTextContent();
                String kodWaluty = element.getElementsByTagName("kod_waluty").item(0).getTextContent();
                String kursSredni = element.getElementsByTagName("kurs_sredni").item(0).getTextContent();

                currency = new Currency(nazwaWaluty, Float.parseFloat(przelicznik.replace(",", "."))
                        , kodWaluty, Float.parseFloat(kursSredni.replace(",", ".")));
                exchangeRateTable.addCurrencyToTheList(currency);
            }
        }
        currency = null;
    }
}