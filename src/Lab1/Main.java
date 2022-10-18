package Lab1;

public class Main {
    private static final String url = "https://www.nbp.pl/kursy/xml/lasta.xml";
    private static final Exchange convert = new Exchange();
    private static final CustomXMLParser customXMLParser = CustomXMLParser.getInstance();

    public static void main(String[] args) {
        try {
            customXMLParser.XMLParse(url);
            convert.exchange();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
