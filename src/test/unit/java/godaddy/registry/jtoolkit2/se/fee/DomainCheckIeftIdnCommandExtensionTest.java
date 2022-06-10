package godaddy.registry.jtoolkit2.se.fee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.DomainCheckCommand;
import godaddy.registry.jtoolkit2.se.idn.ietf.DomainCheckIeftIdnEPPCommandExtension;


public class DomainCheckIeftIdnCommandExtensionTest {

    public static final String DOMAIN_NAME = "jtkutest.com.au";
    public static final String CURRENCY = "AUD";
    public static final String COMMAND = "CREATE";
    public static final String PHASE = "claims";
    public static final int NUMBER_OF_YEARS = 1;
    public static final String SUBPHASE = "landrush";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void testDomainCheckIdnCommandWithTableWithoutUname() throws SAXException {

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        final DomainCheckIeftIdnEPPCommandExtension ext =
                new DomainCheckIeftIdnEPPCommandExtension("en", null);

        try {
            cmd.appendExtension(ext);
            String expectedXml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
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
                   .append("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\"><table>en</table>")
                   .append("</data>")
                   .append("</extension>")
                   .append("<clTRID>JTKUTEST.20070101.010101.0")
                   .append("</clTRID>")
                   .append("</command>")
                   .append("</epp>")
                   .toString();
            assertEquals(expectedXml, cmd.toXML());

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void testDomainCheckIdnCommandWithTableAndUname() {
        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);
        final DomainCheckIeftIdnEPPCommandExtension ext =
                new DomainCheckIeftIdnEPPCommandExtension("en", "uname");
        cmd.appendExtension(ext);

        try {
            String expectedXml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
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
            .append("<data xmlns=\"urn:ietf:params:xml:ns:idn-1.0\"><table>en</table>")
            .append("<uname>uname</uname></data>")
            .append("</extension>")
            .append("<clTRID>JTKUTEST.20070101.010101.0")
            .append("</clTRID>")
            .append("</command>")
            .append("</epp>")
            .toString();
            assertEquals(expectedXml, cmd.toXML());
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void failWhenNameNotProvided() throws SAXException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ErrorPkg.getMessage("se.ar.domain.create.ietf.idn.table"));

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);
        final DomainCheckIeftIdnEPPCommandExtension ext =
                new DomainCheckIeftIdnEPPCommandExtension(null, null);

        cmd.appendExtension(ext);
        cmd.toXML();
    }

}