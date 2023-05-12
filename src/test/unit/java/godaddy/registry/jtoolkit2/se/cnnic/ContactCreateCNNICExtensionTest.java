package godaddy.registry.jtoolkit2.se.cnnic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.ContactCreateCommand;
import godaddy.registry.jtoolkit2.se.Disclose;
import godaddy.registry.jtoolkit2.se.IntPostalInfo;

public class ContactCreateCNNICExtensionTest {

    private static IntPostalInfo commonPostalInfo1, commonPostalInfo2;
    private static String email;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    static {
        commonPostalInfo1 = new IntPostalInfo("JTK Unit Test", "Melbourne", "au");
        commonPostalInfo2 = new IntPostalInfo("JTK Unit Test", "AusRegistry",
            new String[] {"Level 8", "10 Queens Road"}, "Melbourne",
            "VIC", "3004", "au");
        email = "jtktest@ausregistry.com.au";
    }

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCNNICContactCreateCommandStringStringIntPostalInfoString1() {

        Command cmd = new ContactCreateCommand("JTKUTEST", "jtkUt3st",
            commonPostalInfo1, email);
        ContactCreateCNNICExtension contactCreateCNNICExtension = new ContactCreateCNNICExtension("I", "QT", "testcontact", "+1.5777777778");

        try {
            cmd.appendExtension(contactCreateCNNICExtension);

            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><create><create xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKUTEST</id><postalInfo type=\"int\"><name>JTK Unit Test</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><email>jtktest@ausregistry.com.au</email><authInfo><pw>jtkUt3st</pw></authInfo></create></create><extension><cnnic-contact:create xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:type>I</cnnic-contact:type><cnnic-contact:contact type=\"QT\">testcontact</cnnic-contact:contact><cnnic-contact:mobile>+1.5777777778</cnnic-contact:mobile></cnnic-contact:create></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

            assertTrue(cmd.toXML().contains(expectedXml));
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testCNNICContactCreateCommandStringStringIntPostalInfoString2() {
        Command cmd = new ContactCreateCommand("JTKUTEST", "jtkUt3st",
                commonPostalInfo2, email);

        ContactCreateCNNICExtension contactCreateCNNICExtension = new ContactCreateCNNICExtension("I", "QT", "testcontact", "+1.5777777778");

        try {
            cmd.appendExtension(contactCreateCNNICExtension);

            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><create><create xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKUTEST</id><postalInfo type=\"int\"><name>JTK Unit Test</name><org>AusRegistry</org><addr><street>Level 8</street><street>10 Queens Road</street><city>Melbourne</city><sp>VIC</sp><pc>3004</pc><cc>au</cc></addr></postalInfo><email>jtktest@ausregistry.com.au</email><authInfo><pw>jtkUt3st</pw></authInfo></create></create><extension><cnnic-contact:create xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:type>I</cnnic-contact:type><cnnic-contact:contact type=\"QT\">testcontact</cnnic-contact:contact><cnnic-contact:mobile>+1.5777777778</cnnic-contact:mobile></cnnic-contact:create></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

            assertTrue(cmd.toXML().contains(expectedXml));
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testCNNICContactCreateCommandStringStringIntPostalInfoLocalPostalInfoStringStringStringStringStringDisclose1() {
        Command cmd = new ContactCreateCommand("JTKUTEST", "jtkUt3st",
            commonPostalInfo1, null, "+61.398663710", null, "+61.398661970", null,
            email, new Disclose(false));

        ContactCreateCNNICExtension contactCreateCNNICExtension = new ContactCreateCNNICExtension("I", "QT", "testcontact", "+1.5777777778");

        try {
            cmd.appendExtension(contactCreateCNNICExtension);

            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><create><create xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKUTEST</id><postalInfo type=\"int\"><name>JTK Unit Test</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtktest@ausregistry.com.au</email><authInfo><pw>jtkUt3st</pw></authInfo><disclose flag=\"0\"/></create></create><extension><cnnic-contact:create xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:type>I</cnnic-contact:type><cnnic-contact:contact type=\"QT\">testcontact</cnnic-contact:contact><cnnic-contact:mobile>+1.5777777778</cnnic-contact:mobile></cnnic-contact:create></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

            assertTrue(cmd.toXML().contains(expectedXml));
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testCNNICContactCreateCommandStringStringIntPostalInfoLocalPostalInfoStringStringStringStringStringDisclose2() {
        Disclose disclose = new Disclose(false);
        disclose.setAddrInt();
        disclose.setVoice();
        Command cmd = new ContactCreateCommand("JTKUTEST", "jtkUt3st",
                commonPostalInfo2, null, "+61.398663710", null, "+61.398661970", null,
                email, disclose);

        ContactCreateCNNICExtension contactCreateCNNICExtension = new ContactCreateCNNICExtension("I", "QT", "testcontact", "+1.5777777778");

        try {
            cmd.appendExtension(contactCreateCNNICExtension);

            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><create><create xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKUTEST</id><postalInfo type=\"int\"><name>JTK Unit Test</name><org>AusRegistry</org><addr><street>Level 8</street><street>10 Queens Road</street><city>Melbourne</city><sp>VIC</sp><pc>3004</pc><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtktest@ausregistry.com.au</email><authInfo><pw>jtkUt3st</pw></authInfo><disclose flag=\"0\"><addr type=\"int\"/><voice/></disclose></create></create><extension><cnnic-contact:create xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:type>I</cnnic-contact:type><cnnic-contact:contact type=\"QT\">testcontact</cnnic-contact:contact><cnnic-contact:mobile>+1.5777777778</cnnic-contact:mobile></cnnic-contact:create></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

            assertTrue(cmd.toXML().contains(expectedXml));
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testCNNICContactCreateCommandWithEmptyAuthInfo() {
        Command cmd = new ContactCreateCommand("JTKUTEST", null,
            commonPostalInfo1, null, "+61.398663710", null, "+61.398661970", null,
            email, new Disclose(false));

        ContactCreateCNNICExtension contactCreateCNNICExtension = new ContactCreateCNNICExtension("I", "QT", "testcontact", "+1.5777777778");

        try {
            cmd.appendExtension(contactCreateCNNICExtension);

            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><create><create xmlns=\"urn:ietf:params:xml:ns:contact-1.0\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd\"><id>JTKUTEST</id><postalInfo type=\"int\"><name>JTK Unit Test</name><addr><city>Melbourne</city><cc>au</cc></addr></postalInfo><voice>+61.398663710</voice><fax>+61.398661970</fax><email>jtktest@ausregistry.com.au</email><authInfo><pw/></authInfo><disclose flag=\"0\"/></create></create><extension><cnnic-contact:create xmlns:cnnic-contact=\"urn:ietf:params:xml:ns:cnnic-contact-1.0\"><cnnic-contact:type>I</cnnic-contact:type><cnnic-contact:contact type=\"QT\">testcontact</cnnic-contact:contact><cnnic-contact:mobile>+1.5777777778</cnnic-contact:mobile></cnnic-contact:create></extension><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

            assertTrue(cmd.toXML().contains(expectedXml));
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
