import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.LinkedList;

public class StreetAnalysis {

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean row = false; // Detect start of new street in the xml
                boolean eng = false;
                boolean chi = false;
                boolean district = false;
                boolean language = false;
                LinkedList<Street> streets = new LinkedList<>();

                @Override
                public void startDocument() throws SAXException {
                    super.startDocument();
                }

                @Override
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    switch (qName) {
                        case "Row":
                            row = true;
                            break;
                        case "English_Street_Name":
                            eng = true;
                            break;
                        case "Chinese_Street_Name":
                            chi = true;
                            break;
                        case "District_Code":
                            district = true;
                            break;
                        case "Language":
                            language = true;
                            break;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if (row) {
                        streets.add(new Street());
                        row = false;
                    } else if (eng) {
                        streets.getLast().setEnglishName(String.valueOf(ch));
                        eng = false;
                    } else if (chi) {
                        streets.getLast().setCantoneseName(String.valueOf(ch));
                        chi = false;
                    } else if (district) {
                        DistrictCode cur;
                        switch (String.valueOf(ch)) {
                            case "CandW":
                                cur = CandW;
                                break;
                            case "WC":
                                cur = WC;
                                break;
                            case "E":
                                cur = E;
                                break;
                            case "S":
                                cur = S;
                                break;
                            case "YTM":
                                cur = YTM;
                                break;
                            case "SSP":
                                cur = SSP;
                                break;
                            case "KC":
                                cur = KC;
                                break;
                            case "WTS":
                                cur = WTS;
                                break;
                            case "KT":
                                cur = KT;
                                break;
                            case "TW":
                                cur = TW;
                                break;
                            case "TM":
                                cur = TM;
                                break;
                            case "YL":
                                cur = YL;
                                break;
                            case "N":
                                cur = N;
                                break;
                            case "TP":
                                cur = TP;
                                break;
                            case "SK":
                                cur = SK;
                                break;
                            case "ST":
                                cur = ST;
                                break;
                            case "KandT":
                                cur = KandT;
                                break;
                            case "IS":
                                cur = IS;
                                break;
                            default:
                                cur = null;
                        }
                        streets.getLast().setDistrict(cur);
                        district = false;
                    } else if (language) {
                        Language cur;
                        switch (ch[0]) {
                            case 'E': cur = English;
                            break;
                            case 'C': cur = Cantonese;
                            default: cur = Neutral;
                            break;
                        }
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}