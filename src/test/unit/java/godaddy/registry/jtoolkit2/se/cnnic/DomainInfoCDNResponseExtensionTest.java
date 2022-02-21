package godaddy.registry.jtoolkit2.se.cnnic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.DomainInfoResponse;
import godaddy.registry.jtoolkit2.xml.ParsingException;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class DomainInfoCDNResponseExtensionTest {

    private static final String XML_1 =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
            "<epp\r\n" +
            "    xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"\r\n" +
            "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "    xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">\r\n" +
            "    <response>\r\n" +
            "        <result code=\"1000\">\r\n" +
            "            <msg>Command completed successfully</msg>\r\n" +
            "        </result>\r\n" +
            "        <resData>\r\n" +
            "            <infData\r\n" +
            "                xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" " +
            "           xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">\r\n" +
            "                <name>example.cn</name>\r\n" +
            "                <roid>D0000003-AR</roid>\r\n" +
            "                <status s=\"ok\" lang=\"en\"/>\r\n" +
            "                <registrant>EXAMPLE</registrant>\r\n" +
            "                <contact type=\"tech\">EXAMPLE</contact>\r\n" +
            "                <ns>\r\n" +
            "                    <hostObj>ns1.example.cn</hostObj>\r\n" +
            "                    <hostObj>ns2.example.cn</hostObj>\r\n" +
            "                </ns>\r\n" +
            "                <host>ns1.example.cn</host>\r\n" +
            "                <host>ns2.exmaple.cn</host>\r\n" +
            "                <clID>Registrar</clID>\r\n" +
            "                <crID>Registrar</crID>\r\n" +
            "                <crDate>2006-02-09T15:44:58.0Z</crDate>\r\n" +
            "                <exDate>2008-02-10T00:00:00.0Z</exDate>\r\n" +
            "                <authInfo>\r\n" +
            "                    <pw>0192pqow</pw>\r\n" +
            "                </authInfo>\r\n" +
            "            </infData>\r\n" +
            "        </resData>\r\n" +
            "        <extension>\r\n" +
            "            <cdn:infData\r\n" +
            "                xmlns=\"urn:ietf:params:xml:ns:cdn-1.0\"\r\n" +
            "                xmlns:cdn=\"urn:ietf:params:xml:ns:cdn-1.0\" " +
            "                xsi:schemaLocation=\"urn:ietf:params:xml:ns:cdn-1.0 cdn-1.0.xsd\">\r\n" +
            "                <OCDNPunycode>oCDNPunnyCode</OCDNPunycode>\r\n" +
            "                <SCDN>scdn</SCDN>\r\n" +
            "                <SCDNPunycode>sdnPunnyCode</SCDNPunycode>\r\n" +
            "                <TCDN>tcdn</TCDN>\r\n" +
            "                <TCDNPunycode>tdnPunnyCode</TCDNPunycode>\r\n" +
            "                <VCDNList>\r\n" +
            "                    <VCDN>vcdn1</VCDN>\r\n" +
            "                    <VCDN>vcdn2</VCDN>\r\n" +
            "                    <VCDNPunycode>vcdnPunnyCode1</VCDNPunycode>\r\n" +
            "                    <VCDNPunycode>vcdnPunnyCode2</VCDNPunycode>\r\n" +
            "                    <VCDNPunycode>vcdnPunnyCode3</VCDNPunycode>\r\n" +
            "                </VCDNList>\r\n" +
            "            </cdn:infData>\r\n" +
            "        </extension>\r\n" +
            "        <trID>\r\n" +
            "            <clTRID>ABC-12345</clTRID>\r\n" +
            "            <svTRID>54321-XYZ</svTRID>\r\n" +
            "        </trID>\r\n" +
            "    </response>\r\n" +
            "</epp>";

    private DomainInfoResponse response;
    private XMLParser parser;
    private CDN cdn;
    private DomainInfoCDNResponseExtension cdnExtension;

    @Before
    public void setUp() throws Exception {
        response = new DomainInfoResponse();
        cdn = new CDN();
        cdn.setOcdnPunycode("oCDNPunnyCode");
        cdn.setScdn("scdn");
        cdn.setScdnPunycode("sdnPunnyCode");
        cdn.setTcdn("tcdn");
        cdn.setTcdnPunycode("tdnPunnyCode");
        Vector<String> vcdn = new Vector<String>();
        vcdn.add("vcdn1");
        vcdn.add("vcdn2");
        Vector<String> vcdnPunnyCode = new Vector<String>();
        vcdnPunnyCode.add("vcdnPunnyCode1");
        vcdnPunnyCode.add("vcdnPunnyCode2");
        vcdnPunnyCode.add("vcdnPunnyCode3");
        cdn.setVcdn(vcdn);
        cdn.setVcdnPunycode(vcdnPunnyCode);
        Timer.setTime("20070101.010101");
        CLTRID.setClID("ABC-12345");

        cdnExtension =
            new DomainInfoCDNResponseExtension(cdn);
        response.registerExtension(cdnExtension);
        parser = new XMLParser();
        XMLDocument doc = parser.parse(XML_1);
        response.fromXML(doc);
    }

    @Test
    public void testGetCdn() {
        assertEquals(cdn, cdnExtension.getCDN());
    }

    @Test
    public void testDomainInfoCDNResponseExtension() {
        XMLDocument tmpDoc;
        try {
            tmpDoc = parser.parse(XML_1);
            DomainInfoResponse tmpResponse = new DomainInfoResponse();
            tmpResponse.registerExtension(cdnExtension);
            tmpResponse.fromXML(tmpDoc);
        } catch (ParsingException e) {
            fail(e.getMessage());
        }
    }

}
