package godaddy.registry.jtoolkit2.se.block11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.DomainUpdateCommand;

public class DomainUpdateBlockCommandExtensionTest {
    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldGenerateValidXMLForBlock() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";

        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        DomainUpdateBlockCommandExtension ext = new DomainUpdateBlockCommandExtension();
        ext.setId(id);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:gdreg:params:xml:ns:block-1.1\">"
                    + "<id>" + id + "</id>"
                    + "</update>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldGenerateValidXMLForChgToExpire() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";

        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        DomainUpdateBlockCommandExtension ext = new DomainUpdateBlockCommandExtension();
        ext.setId(id);
        ext.setOnExpiryType(OnExpiryAction.EXPIRE);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:gdreg:params:xml:ns:block-1.1\">"
                    + "<id>" + id + "</id>"
                    + "<chg><onExpiry action=\"expire\"/></chg>"
                    + "</update>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldGenerateValidXMLForChgToCustom() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";

        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        DomainUpdateBlockCommandExtension ext = new DomainUpdateBlockCommandExtension();
        ext.setId(id);
        ext.setOnExpiryType(OnExpiryAction.CUSTOM);
        ext.setOnExpiryAction("convert");
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:gdreg:params:xml:ns:block-1.1\">"
                    + "<id>" + id + "</id>"
                    + "<chg><onExpiry action=\"custom\">convert</onExpiry></chg>"
                    + "</update>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}