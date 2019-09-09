import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

// SAXParser
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
// DocumentBuilder

public class StreetAnalysis {
    static private LinkedList<Street> streets = new LinkedList<>();

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
                    String text = String.valueOf(Arrays.copyOfRange(ch, start, start + length));
                    if (row) {
                        streets.add(new Street());
                        row = false;
                    } else if (eng) {
                        streets.getLast().setEnglishName(text);
                        eng = false;
                    } else if (chi) {
                        streets.getLast().setCantoneseName(text);
                        chi = false;
                    } else if (district) {
                        DistrictCode cur;
                        switch (text) {
                            case "CandW":
                                cur = DistrictCode.CandW;
                                break;
                            case "WC":
                                cur = DistrictCode.WC;
                                break;
                            case "E":
                                cur = DistrictCode.E;
                                break;
                            case "S":
                                cur = DistrictCode.S;
                                break;
                            case "YTM":
                                cur = DistrictCode.YTM;
                                break;
                            case "SSP":
                                cur = DistrictCode.SSP;
                                break;
                            case "KC":
                                cur = DistrictCode.KC;
                                break;
                            case "WTS":
                                cur = DistrictCode.WTS;
                                break;
                            case "KT":
                                cur = DistrictCode.KT;
                                break;
                            case "TW":
                                cur = DistrictCode.TW;
                                break;
                            case "TM":
                                cur = DistrictCode.TM;
                                break;
                            case "YL":
                                cur = DistrictCode.YL;
                                break;
                            case "N":
                                cur = DistrictCode.N;
                                break;
                            case "TP":
                                cur = DistrictCode.TP;
                                break;
                            case "SK":
                                cur = DistrictCode.SK;
                                break;
                            case "ST":
                                cur = DistrictCode.ST;
                                break;
                            case "KandT":
                                cur = DistrictCode.KandT;
                                break;
                            case "IS":
                                cur = DistrictCode.IS;
                                break;
                            default:
                                cur = null;
                        }
                        streets.getLast().setDistrict(cur);
                        district = false;
                    } else if (language) {
                        Language cur;
                        switch (ch[start]) {
                            case 'E': cur = Language.English;
                            break;
                            case 'C': cur = Language.Cantonese;
                            break;
                            default: cur = Language.Neutral;
                            break;
                        }
                        streets.getLast().setLanguage(cur);
                    }
                }
            };

            saxParser.parse("streets.xml", handler);
            Scanner console = new Scanner(System.in);

            for (Street s: streets) {
                if (s.getLanguage() == null) {
                    System.out.println("English: " + s.getEnglishName()
                            + "\nCantonese: " + s.getCantoneseName());
                    Language lang = null;
                    // 1 = Eng, 2 = Cantonese, 3 = Neutral
                    int code = console.nextInt();
                    // Only accept valid codes
                    while (code > 3 || code < 1) {
                        code = console.nextInt();
                    }

                    switch (code) {
                        case 1: lang = Language.English;
                        break;
                        case 2: lang = Language.Cantonese;
                        break;
                        case 3: lang = Language.Neutral;
                        break;
                    }
                    s.setLanguage(lang);

                    System.out.print("\033[H\033[2J"); // Clear terminal
                    System.out.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}