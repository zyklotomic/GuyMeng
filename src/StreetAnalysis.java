import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class StreetAnalysis {

    public static void main(String[] args) {
        SAXParserFactory factor = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            boolean row = false; // Detect start of new street in the xml
            boolean 
        }

    }
}