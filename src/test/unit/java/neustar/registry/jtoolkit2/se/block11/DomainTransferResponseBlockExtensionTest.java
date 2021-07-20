package neustar.registry.jtoolkit2.se.block11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import neustar.registry.jtoolkit2.se.DomainTransferResponse;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class DomainTransferResponseBlockExtensionTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldGetBlockExtension() throws Exception {
        String id = "BD-001";
        final DomainTransferResponse response = new DomainTransferResponse();
        final DomainTransferBlockResponseExtension responseExtension = new DomainTransferBlockResponseExtension();
        final XMLDocument doc = PARSER.parse(getTransferResponseExpectedXml(id));

        response.registerExtension(responseExtension);
        response.fromXML(doc);
        assertTrue("Extension should have been initialised", responseExtension.isInitialised());
        assertEquals(id, responseExtension.getBlockId());

    }

    private static String getTransferResponseExpectedXml(final String id) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                + "<response>"
                + "<result code=\"1000\">"
                + "<msg>Command completed successfully</msg>"
                + "</result>"
                + "<resData>"
                + "<domain:trnData xmlns:domain=\"urn:ietf:params:xml:ns:domain-1.0\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                + "<domain:name>domainName</domain:name>"
                + "<domain:trStatus>pending</domain:trStatus>"
                + "<domain:reID>ClientX</domain:reID>"
                + "<domain:reDate>2000-06-08T22:00:00.0Z</domain:reDate>"
                + "<domain:acID>ClientY</domain:acID>"
                + "<domain:acDate>2000-06-13T22:00:00.0Z</domain:acDate>"
                + "<domain:exDate>2002-09-08T22:00:00.0Z</domain:exDate>"
                + "</domain:trnData>"
                + "</resData>"
                + "<extension>"
                + "<block:trnData  xmlns:block=\"urn:gdreg:params:xml:ns:block-1.1\" "
                + "xsi:schemaLocation=\"urn:gdreg:params:xml:ns:block-1.1 block-1.1.xsd\">"
                + "<block:id>" + id + "</block:id>"
                + "</block:trnData>"
                + "</extension>"
                + "<trID>"
                + "<clTRID>ABC-12345</clTRID>"
                + "<svTRID>54321-XYZ</svTRID>"
                + "</trID>"
                + "</response>"
                + "</epp>";
    }
}
