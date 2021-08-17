package godaddy.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import godaddy.registry.jtoolkit2.EPPDateFormatter;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class MzbTransferResponseTest {
    private static final String XML =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
                + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><response><result code=\"1001\">"
                + "<msg>Command completed successfully; action pending</msg></result>"
                + "<resData>"
                + "<mzb:trnData xmlns:mzb=\"urn:gdreg:params:xml:ns:mzb-1.0\""
                + " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                + "<mzb:roid>roid-roid</mzb:roid>"
                + "<mzb:trStatus>pending</mzb:trStatus>"
                + "<mzb:reID>ClientX</mzb:reID>"
                + "<mzb:reDate>2000-06-08T22:00:00.0Z</mzb:reDate>"
                + "<mzb:acID>ClientY</mzb:acID>"
                + "<mzb:acDate>2000-06-13T22:00:00.0Z</mzb:acDate>"
                + "</mzb:trnData>"
                + "</resData>"
                + "<trID><clTRID>ABC-12345</clTRID><svTRID>54322-XYZ</svTRID></trID>"
                + "</response>"
                + "</epp>";

    private MzbTransferResponse response;

    @Before
    public void setUp() throws Exception {
        response = new MzbTransferResponse();
        XMLParser parser = new XMLParser();
        XMLDocument doc = parser.parse(XML);
        response.fromXML(doc);
    }

    @Test
    public void testGetTransferStatus() {
        assertEquals("pending", response.getTransferStatus());
    }

    @Test
    public void testGetRequestingClID() {
        assertEquals("ClientX", response.getRequestingClID());
    }

    @Test
    public void testGetRequestDate() {
        assertEquals(EPPDateFormatter.fromXSDateTime("2000-06-08T22:00:00.0Z"), response.getRequestDate());
    }

    @Test
    public void testGetActioningClID() {
        assertEquals("ClientY", response.getActioningClID());
    }

    @Test
    public void testGetActionDate() {
        assertEquals(EPPDateFormatter.fromXSDateTime("2000-06-13T22:00:00.0Z"), response.getActionDate());
    }

    @Test
    public void testGetCLTRID() {
        assertEquals("ABC-12345", response.getCLTRID());
    }

}
