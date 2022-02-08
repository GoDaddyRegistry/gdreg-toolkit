package godaddy.registry.jtoolkit2.se.cnnic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.ContactUpdateCommand;
import godaddy.registry.jtoolkit2.se.IntPostalInfo;
import godaddy.registry.jtoolkit2.se.Status;

public class ContactUpdateCNNICExtensionTest {

    private CNContact cnContact;

    @Before
    public void setUp() throws Exception {
        cnContact = new CNContact();
        cnContact.setContactTypeId("QT");
        cnContact.setContactTypeName("updatedddd");
        cnContact.setMobile("+1.5777778888");
        cnContact.setType("E");
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCNNICContactUpdateCommandStringString() {
        Command cmd = new ContactUpdateCommand("JTKCON", "jtkUT3st");
        ContactUpdateCNNICExtension contactUpdateCNNICExtension = new ContactUpdateCNNICExtension("E", "QT", "updatedddd", "+1.5777778888");
        try {
            cmd.appendExtension(contactUpdateCNNICExtension);
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><update><update xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKCON</id><chg><authInfo><pw>jtkUT3st</pw></authInfo></chg></update></update><extension><cnnic-contact:update xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:chg><cnnic-contact:type>E</cnnic-contact:type><cnnic-contact:contact type=\"QT\">updatedddd</cnnic-contact:contact><cnnic-contact:mobile>+1.5777778888</cnnic-contact:mobile></cnnic-contact:chg></cnnic-contact:update></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testCNNICContactUpdateCommandStringStringStatusArrayStringArrayIntPostalInfoLocalPostalInfoStringStringStringStringStringDisclose() {
        Command cmd = new ContactUpdateCommand("JTKCON", "jtkUT3st",
                new Status[] {new Status("clientUpdateProhibited", "testing")},
                new String[] {"clientDeleteProhibited"},
                new IntPostalInfo("AusRegistry", "Melbourne", "au"), null,
                "+61.398663710", null, "+61.398661970", null, "jtk@ausregistry.com.au", null);
        ContactUpdateCNNICExtension contactUpdateCNNICExtension = new ContactUpdateCNNICExtension("E", "QT", "updatedddd", "+1.5777778888");
        try {
            cmd.appendExtension(contactUpdateCNNICExtension);
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><update><update xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKCON</id><add><status s=\"clientUpdateProhibited\">testing</status></add><rem><status s=\"clientDeleteProhibited\"/></rem><chg><postalInfo type=\"int\"><name>AusRegistry</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtk@ausregistry.com.au</email><authInfo><pw>jtkUT3st</pw></authInfo></chg></update></update><extension><cnnic-contact:update xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:chg><cnnic-contact:type>E</cnnic-contact:type><cnnic-contact:contact type=\"QT\">updatedddd</cnnic-contact:contact><cnnic-contact:mobile>+1.5777778888</cnnic-contact:mobile></cnnic-contact:chg></cnnic-contact:update></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateAuthInfoTagWithAuthInfoIfProvided() {
        Command cmd = new ContactUpdateCommand("JTKCON", "jtkUT3st",
                new Status[] {new Status("clientUpdateProhibited", "testing")},
                new String[] {"clientDeleteProhibited"},
                new IntPostalInfo("AusRegistry", "Melbourne", "au"), null,
                "+61.398663710", null, "+61.398661970", null, "jtk@ausregistry.com.au", null);
        ContactUpdateCNNICExtension contactUpdateCNNICExtension = new ContactUpdateCNNICExtension("E", "QT", "updatedddd", "+1.5777778888");
        try {
            cmd.appendExtension(contactUpdateCNNICExtension);
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><update><update xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKCON</id><add><status s=\"clientUpdateProhibited\">testing</status></add><rem><status s=\"clientDeleteProhibited\"/></rem><chg><postalInfo type=\"int\"><name>AusRegistry</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtk@ausregistry.com.au</email><authInfo><pw>jtkUT3st</pw></authInfo></chg></update></update><extension><cnnic-contact:update xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:chg><cnnic-contact:type>E</cnnic-contact:type><cnnic-contact:contact type=\"QT\">updatedddd</cnnic-contact:contact><cnnic-contact:mobile>+1.5777778888</cnnic-contact:mobile></cnnic-contact:chg></cnnic-contact:update></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateAuthInfoTagWithEmptyAuthInfoIfEmptyStringProvided() {
        Command cmd = new ContactUpdateCommand("JTKCON", "",
                new Status[] {new Status("clientUpdateProhibited", "testing")},
                new String[] {"clientDeleteProhibited"},
                new IntPostalInfo("AusRegistry", "Melbourne", "au"), null,
                "+61.398663710", null, "+61.398661970", null, "jtk@ausregistry.com.au", null);
        ContactUpdateCNNICExtension contactUpdateCNNICExtension = new ContactUpdateCNNICExtension("E", "QT", "updatedddd", "+1.5777778888");
        try {
            cmd.appendExtension(contactUpdateCNNICExtension);
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><update><update xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKCON</id><add><status s=\"clientUpdateProhibited\">testing</status></add><rem><status s=\"clientDeleteProhibited\"/></rem><chg><postalInfo type=\"int\"><name>AusRegistry</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtk@ausregistry.com.au</email><authInfo><pw/></authInfo></chg></update></update><extension><cnnic-contact:update xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:chg><cnnic-contact:type>E</cnnic-contact:type><cnnic-contact:contact type=\"QT\">updatedddd</cnnic-contact:contact><cnnic-contact:mobile>+1.5777778888</cnnic-contact:mobile></cnnic-contact:chg></cnnic-contact:update></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateAuthInfoTagWithEmptyAuthInfoIfNullAndRemoveAuthInfoTrue() {
        Command cmd = new ContactUpdateCommand("JTKCON", "",
                new Status[] {new Status("clientUpdateProhibited", "testing")},
                new String[] {"clientDeleteProhibited"},
                new IntPostalInfo("AusRegistry", "Melbourne", "au"), null,
                "+61.398663710", null, "+61.398661970", null, "jtk@ausregistry.com.au", null, true);
        ContactUpdateCNNICExtension contactUpdateCNNICExtension = new ContactUpdateCNNICExtension("E", "QT", "updatedddd", "+1.5777778888");
        try {
            cmd.appendExtension(contactUpdateCNNICExtension);
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><update><update xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKCON</id><add><status s=\"clientUpdateProhibited\">testing</status></add><rem><status s=\"clientDeleteProhibited\"/></rem><chg><postalInfo type=\"int\"><name>AusRegistry</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtk@ausregistry.com.au</email><authInfo><pw/></authInfo></chg></update></update><extension><cnnic-contact:update xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:chg><cnnic-contact:type>E</cnnic-contact:type><cnnic-contact:contact type=\"QT\">updatedddd</cnnic-contact:contact><cnnic-contact:mobile>+1.5777778888</cnnic-contact:mobile></cnnic-contact:chg></cnnic-contact:update></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }
}

