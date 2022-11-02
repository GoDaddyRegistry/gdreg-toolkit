package godaddy.registry.jtoolkit2.se.fee10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.DomainCheckCommand;
import godaddy.registry.jtoolkit2.se.Period;
import godaddy.registry.jtoolkit2.xml.XmlOutputConfig;


public class DomainCheckFeeCommandExtensionTest {

    public static final String DOMAIN_NAME = "jtkutest.com.au";
    public static final String CURRENCY = "AUD";
    public static final String COMMAND = "create";
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
    public void shouldCreateValidXmlWhenSupplyFeeExtension() throws SAXException {

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(COMMAND, PHASE, SUBPHASE);
        command.setPeriod(new Period(NUMBER_OF_YEARS));

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(CURRENCY, Collections.singletonList(command));

        try {
            cmd.appendExtension(ext);
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command>" +
                    "<check>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">" +
                    "<name>jtkutest.com.au</name>" +
                    "</check>" +
                    "</check>" +
                    "<extension>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:epp:fee-1.0\">" +
                    "<currency>" + CURRENCY + "</currency>" +
                    "<command name=\"" + COMMAND + "\" phase=\"" + PHASE + "\" subphase=\"" + SUBPHASE + "\">" +
                    "<period unit=\"y\">" + NUMBER_OF_YEARS + "</period>" +
                    "</command>" +
                    "</check>" +
                    "</extension>" +
                    "<clTRID>JTKUTEST.20070101.010101.0</clTRID>" +
                    "</command>" +
                    "</epp>";

            assertEquals(expectedXml, cmd.toXML());

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateValidXmlWhenSubphaseNotProvided() throws SAXException {

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(COMMAND, PHASE);
        command.setPeriod(new Period(NUMBER_OF_YEARS));

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(CURRENCY, Collections.singletonList(command));

        try {
            cmd.appendExtension(ext);
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command>" +
                    "<check>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">" +
                    "<name>jtkutest.com.au</name>" +
                    "</check>" +
                    "</check>" +
                    "<extension>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:epp:fee-1.0\">" +
                    "<currency>" + CURRENCY + "</currency>" +
                    "<command name=\"" + COMMAND + "\" phase=\"" + PHASE + "\">"  +
                    "<period unit=\"y\">" + NUMBER_OF_YEARS + "</period>" +
                    "</command>" +
                    "</check>" +
                    "</extension>" +
                    "<clTRID>JTKUTEST.20070101.010101.0</clTRID>" +
                    "</command>" +
                    "</epp>";

            assertEquals(expectedXml, cmd.toXML());

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateValidXmlWhenCurrencyProvided() throws SAXException {

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(COMMAND, PHASE, SUBPHASE);
        command.setPeriod(new Period(NUMBER_OF_YEARS));

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(null, Collections.singletonList(command));
        try {
            cmd.appendExtension(ext);
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command>" +
                    "<check>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">" +
                    "<name>jtkutest.com.au</name>" +
                    "</check>" +
                    "</check>" +
                    "<extension>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:epp:fee-1.0\">" +
                    "<command name=\"" + COMMAND + "\" phase=\"" + PHASE + "\" subphase=\"" + SUBPHASE + "\">" +
                    "<period unit=\"y\">" + NUMBER_OF_YEARS + "</period>" +
                    "</command>" +
                    "</check>" +
                    "</extension>" +
                    "<clTRID>JTKUTEST.20070101.010101.0</clTRID>" +
                    "</command>" +
                    "</epp>";

            assertEquals(expectedXml, cmd.toXML());

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void failWhenCommandNameNotProvided() throws SAXException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Field 'name' is required.");

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(null, PHASE, SUBPHASE);
        command.setPeriod(new Period(NUMBER_OF_YEARS));

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(CURRENCY, Collections.singletonList(command));

        cmd.appendExtension(ext);
        cmd.toXML();
    }

    @Test
    public void shouldCreateValidXmlWhenNoPeriodSupplied() throws SAXException {

        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(COMMAND, PHASE, SUBPHASE);

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(CURRENCY, Collections.singletonList(command));
        try {
            cmd.appendExtension(ext);
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command>" +
                    "<check>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">" +
                    "<name>jtkutest.com.au</name>" +
                    "</check>" +
                    "</check>" +
                    "<extension>" +
                    "<check xmlns=\"urn:ietf:params:xml:ns:epp:fee-1.0\">" +
                    "<currency>" + CURRENCY + "</currency>" +
                    "<command name=\"" + COMMAND + "\" phase=\"" + PHASE + "\" subphase=\"" + SUBPHASE + "\"/>" +
                    "</check>" +
                    "</extension>" +
                    "<clTRID>JTKUTEST.20070101.010101.0</clTRID>" +
                    "</command>" +
                    "</epp>";

            assertEquals(expectedXml, cmd.toXML());

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateValidXmlWhenFeeExtensionSuppliedAndRequireNamespacePrefix() {
        final Command cmd = new DomainCheckCommand(DOMAIN_NAME);

        FeeCheckCommand command = new FeeCheckCommand(COMMAND, PHASE, SUBPHASE);
        command.setPeriod(new Period(NUMBER_OF_YEARS));

        final DomainCheckFeeCommandExtension ext =
                new DomainCheckFeeCommandExtension(CURRENCY, Collections.singletonList(command));

        final String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<e:epp xmlns:e=\"urn:ietf:params:xml:ns:epp-1.0\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                + "<e:command>"
                + "<e:check>"
                + "<domain:check xmlns:domain=\"urn:ietf:params:xml:ns:domain-1.0\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                + "<domain:name>" + DOMAIN_NAME + "</domain:name>"
                + "</domain:check>"
                + "</e:check>"
                + "<e:extension>"
                + "<feeV1_0:check xmlns:feeV1_0=\"urn:ietf:params:xml:ns:epp:fee-1.0\">"
                + "<feeV1_0:currency>" + CURRENCY + "</feeV1_0:currency>"
                + "<feeV1_0:command name=\"" + COMMAND + "\" phase=\"" + PHASE + "\" subphase=\"" + SUBPHASE + "\">"
                + "<feeV1_0:period unit=\"y\">" + NUMBER_OF_YEARS + "</feeV1_0:period>"
                + "</feeV1_0:command>"
                + "</feeV1_0:check>"
                + "</e:extension>"
                + "<e:clTRID>JTKUTEST.20070101.010101.0</e:clTRID>"
                + "</e:command>"
                + "</e:epp>";
        try {
            cmd.appendExtension(ext);
            assertEquals(expectedXml, cmd.toXML(XmlOutputConfig.prefixAllNamespaceConfig()));

        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}