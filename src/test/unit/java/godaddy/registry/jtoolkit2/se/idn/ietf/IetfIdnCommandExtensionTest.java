package godaddy.registry.jtoolkit2.se.idn.ietf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.DomainCheckCommand;
import godaddy.registry.jtoolkit2.se.DomainCreateCommand;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

public class IetfIdnCommandExtensionTest {

    public static final String DOMAIN_NAME = "jtkutest.com.au";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void testDomainCreateIdnCommandWithTableAndUname() {
        Command cmd = new DomainCreateCommand(DOMAIN_NAME, "jtkUT3st");
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension("es", "uname");
        cmd.appendExtension(idnExt);

        String expectedExtension = new StringBuilder("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\">")
                .append("<table>es</table>")
                .append("<uname>uname</uname>")
                .append("</data>")
                .toString();
        try {
            String xml = cmd.toXML();
            assertEquals(expectedDomainCreateXmlWithExtension(expectedExtension), xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testDomainCreateIdnCommandWithTableButWithoutUname() {
        Command cmd = new DomainCreateCommand(DOMAIN_NAME, "jtkUT3st");
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension("es", null);
        cmd.appendExtension(idnExt);
        String expectedExtension = new StringBuilder("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\">")
                .append("<table>es</table>")
                .append("</data>")
                .toString();
        try {
            String xml = cmd.toXML();
            assertEquals(expectedDomainCreateXmlWithExtension(expectedExtension), xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void failCreateCommandWhenTableNotProvided() throws SAXException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Table must not be null or empty.");
        Command cmd = new DomainCreateCommand(DOMAIN_NAME, "jtkUT3st");
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension(null, null);
        cmd.appendExtension(idnExt);
        cmd.toXML();
    }

    @Test
    public void testDomainCheckIdnCommandWithTableAndUname() {
        Command cmd = new DomainCheckCommand(DOMAIN_NAME);
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension("es", "uname");
        cmd.appendExtension(idnExt);

        String expectedExtension = new StringBuilder("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\">")
                .append("<table>es</table>")
                .append("<uname>uname</uname>")
                .append("</data>")
                .toString();
        try {
            String xml = cmd.toXML();
            assertEquals(expectedDomainCheckXmlWithExtension(expectedExtension), xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }


    @Test
    public void testDomainCheckIdnCommandWithTableButWithoutUname() {
        Command cmd = new DomainCheckCommand(DOMAIN_NAME);
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension("es", null);
        cmd.appendExtension(idnExt);
        String expectedExtension = new StringBuilder("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\">")
                .append("<table>es</table>")
                .append("</data>")
                .toString();
        try {
            String xml = cmd.toXML();
            assertEquals(expectedDomainCheckXmlWithExtension(expectedExtension), xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void failCheckCommandWhenTableNotProvided() throws SAXException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Table must not be null or empty.");
        Command cmd = new DomainCheckCommand(DOMAIN_NAME);
        IetfIdnCommandExtension idnExt = new IetfIdnCommandExtension(null, null);
        cmd.appendExtension(idnExt);
        cmd.toXML();
    }
    private String expectedDomainCreateXmlWithExtension(String extensionSection) {
        return new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"")
                .append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">")
                .append("<command>")
                .append("<create>")
                .append("<create xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">")
                .append("<name>jtkutest.com.au")
                .append("</name>")
                .append("<authInfo>")
                .append("<pw>jtkUT3st")
                .append("</pw>")
                .append("</authInfo>")
                .append("</create>")
                .append("</create>")
                .append("<extension>")
                .append(extensionSection)
                .append("</extension>")
                .append("<clTRID>JTKUTEST.20070101.010101.0")
                .append("</clTRID>")
                .append("</command>")
                .append("</epp>")
                .toString();
    }

    private String expectedDomainCheckXmlWithExtension(String extensionSection) {
        return new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"")
               .append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
               .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command>")
               .append("<check><check xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"")
               .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">")
               .append("<name>jtkutest.com.au")
               .append("</name>")
               .append("</check>")
               .append("</check>")
               .append("<extension>")
               .append(extensionSection)
               .append("</extension>")
               .append("<clTRID>JTKUTEST.20070101.010101.0")
               .append("</clTRID>")
               .append("</command>")
               .append("</epp>")
               .toString();
    }
}

