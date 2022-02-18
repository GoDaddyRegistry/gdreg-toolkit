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
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><response><result code=\"1000\"><msg>Command completed successfully</msg></result><resData><infData xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\"><name>example.cn</name><roid>D0000003-AR</roid><status s=\"ok\" lang=\"en\"/><registrant>EXAMPLE</registrant><contact type=\"tech\">EXAMPLE</contact><ns><hostObj>ns1.example.cn</hostObj><hostObj>ns2.example.cn</hostObj></ns><host>ns1.example.cn</host><host>ns2.exmaple.cn</host><clID>Registrar</clID><crID>Registrar</crID><crDate>2006-02-09T15:44:58.0Z</crDate><exDate>2008-02-10T00:00:00.0Z</exDate><authInfo><pw>0192pqow</pw></authInfo></infData></resData><extension><cdn:infData xmlns=\"urn:ietf:params:xml:ns:cdn-1.0\" xmlns:cdn=\"urn:ietf:params:xml:ns:cdn-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:cdn-1.0 cdn-1.0.xsd\"><OCDNPunycode>oCDNPunnyCode</OCDNPunycode><SCDN>scdn</SCDN><SCDNPunycode>sdnPunnyCode</SCDNPunycode><TCDN>tcdn</TCDN><TCDNPunycode>tdnPunnyCode</TCDNPunycode><VCDNList><VCDN>vcdn1</VCDN><VCDN>vcdn2</VCDN><VCDNPunycode>vcdnPunnyCode1</VCDNPunycode><VCDNPunycode>vcdnPunnyCode2</VCDNPunycode><VCDNPunycode>vcdnPunnyCode3</VCDNPunycode></VCDNList></cdn:infData></extension><trID><clTRID>ABC-12345</clTRID><svTRID>54321-XYZ</svTRID></trID></response></epp>";

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
