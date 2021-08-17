package godaddy.registry.jtoolkit2.se.mzb;

import static godaddy.registry.jtoolkit2.se.mzb.MzbInfoResponseTest.EpsInfoResponseBuilder.infoResponseBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

import org.junit.Test;

import godaddy.registry.jtoolkit2.EPPDateFormatter;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class MzbInfoResponseTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void testFromXML() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
    }

    @Test
    public void testGetPW() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
        assertEquals("0192pqow", response.getPW());
    }

    @Test
    public void testGetCreateDate() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
        GregorianCalendar exp = EPPDateFormatter.fromXSDateTime("2006-02-09T15:44:58.0Z");
        GregorianCalendar act = response.getCreateDate();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void testGetExpireDate() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
        GregorianCalendar exp = EPPDateFormatter.fromXSDateTime("2008-02-10T00:00:00.0Z");
        GregorianCalendar act = response.getExpireDate();
        assertEquals(exp, act);
    }

    @Test
    public void testGetRegistrantID() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
        String exp = "EXAMPLE";
        String act = response.getRegistrantID();
        assertEquals(exp, act);
    }

    @Test
    public void shouldRetrieveLabels() throws Exception {
        MzbInfoResponse response = new MzbInfoResponse();
        XMLDocument doc = PARSER.parse(infoResponseBuilder("XXX-YYY").build());
        response.fromXML(doc);
        String exp = "fred";
        final String[] labels = response.getLabels();
        assertNotNull(labels);
        assertEquals(1, labels.length);
        assertEquals(exp, labels[0]);
    }

    static final class EpsInfoResponseBuilder {

        private final String roid;

        private EpsInfoResponseBuilder(String roid) {
            this.roid = roid;
        }

        static EpsInfoResponseBuilder infoResponseBuilder(String domainName) {
            return new EpsInfoResponseBuilder(domainName);
        }

        private String build() {
            final StringBuilder result = new StringBuilder();
            result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            result.append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"");
            result.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
            result.append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">");
            result.append("<response>");
            result.append("<result code=\"1000\">");
            result.append("<msg>Command completed successfully</msg>");
            result.append("</result>");
            result.append("<resData>");
            result.append("<infData xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"");
            result.append(" xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\"");
            result.append(" type=\"standard\">");

            result.append("<roid>" + roid + "</roid>");
            result.append("<labels><label>fred</label></labels>");
            result.append("<registrant>EXAMPLE</registrant>");
            result.append("<clID>Registrar</clID>");
            result.append("<crID>Registrar</crID>");
            result.append("<crDate>2006-02-09T15:44:58.0Z</crDate>");
            result.append("<exDate>2008-02-10T00:00:00.0Z</exDate>");
            result.append("<authInfo>");
            result.append("<pw>0192pqow</pw>");
            result.append("</authInfo>");
            result.append("</infData>");
            result.append("</resData>");

            result.append("<trID>");
            result.append("<clTRID>ABC-12345</clTRID>");
            result.append("<svTRID>54321-XYZ</svTRID>");
            result.append("</trID>");
            result.append("</response>");
            result.append("</epp>");
            return result.toString();
        }
    }

}
