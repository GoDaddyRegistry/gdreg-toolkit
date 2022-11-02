package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import godaddy.registry.jtoolkit2.se.DomainCreateResponse;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class DomainCreateFeeResponseExtensionTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldGetFeeExtension() throws Exception {
        final String dnsForm = "xn--xha91b83h.com";
        final DomainCreateResponse response = new DomainCreateResponse();
        final DomainCreateFeeResponseExtension feeResponseExtension = new DomainCreateFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml(dnsForm, true));

        response.registerExtension(feeResponseExtension);
        response.fromXML(doc);
        assertEquals(dnsForm, response.getName());
        assertTrue("Fee extension should have been initialised", feeResponseExtension.isInitialised());
        assertEquals("USD", feeResponseExtension.getCurrency());
        assertEquals("30.00", feeResponseExtension.getRegistrationFee().toPlainString());

    }

    @Test
    public void shouldGetFeeDetails() throws Exception {
        final String dnsForm = "xn--xha91b83h.com";
        final DomainCreateResponse response = new DomainCreateResponse();
        final DomainCreateFeeResponseExtension feeResponseExtension = new DomainCreateFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseFullFeeExpectedXml(dnsForm, true));

        response.registerExtension(feeResponseExtension);
        response.fromXML(doc);
        assertEquals(dnsForm, response.getName());
        assertTrue("Fee extension should have been initialised", feeResponseExtension.isInitialised());
        assertEquals("USD", feeResponseExtension.getCurrency());
        final Map<String, FeeType> feeType = feeResponseExtension.getFeeType();
        assertEquals(1, feeType.size());
        final FeeType fee = feeType.get("Registration Fee");
        assertTrue(fee.isRefundable());
        assertEquals("ja", fee.getLanguage());
        assertEquals("P5D", fee.getGracePeriod());
        assertEquals(AppliedType.immediate, fee.getApplied());
        assertEquals("30.00", fee.getFee().toPlainString());

    }

    @Test
    public void shouldNotGetFeeExtension() throws Exception {
        final String domainName = "xn--xha91b83h.com";
        final DomainCreateResponse response = new DomainCreateResponse();
        final DomainCreateFeeResponseExtension feeResponseExtension = new DomainCreateFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml(domainName, false));

        response.fromXML(doc);
        assertEquals(domainName, response.getName());
        assertFalse("Fee should not have been initialised", feeResponseExtension.isInitialised());
    }

    private static String getCreateResponseExpectedXml(final String domainName, boolean feeExtension) {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"");
        result.append(    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        result.append(    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">");
        result.append(    "<response>");
        result.append(        "<result code=\"1000\">");
        result.append(            "<msg>Command completed successfully</msg>");
        result.append(        "</result>");
        result.append(        "<resData>");
        result.append(            "<domain:creData xmlns:domain=\"urn:ietf:params:xml:ns:domain-1.0\"");
        result.append(                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">");
        result.append(                "<domain:name>" + domainName + "</domain:name>");
        result.append(                "<domain:crDate>1999-04-03T22:00:00.0Z</domain:crDate>");
        result.append(                "<domain:exDate>2001-04-03T22:00:00.0Z</domain:exDate>");
        result.append(            "</domain:creData>");
        result.append(        "</resData>");

        if (feeExtension) {
            result.append("<extension>");
            result.append("<").append(FEEV10.getName()).append(":creData xmlns:")
                              .append(FEEV10.getName()).append("=\"urn:ietf:params:xml:ns:epp:fee-1.0\">");
            result.append("<").append(FEEV10.getName()).append(":currency>" + "USD" + "</")
                              .append(FEEV10.getName()).append(":currency>");
            result.append("<").append(FEEV10.getName()).append(":fee>" + "30.00" + "</")
                              .append(FEEV10.getName()).append(":fee>");
            result.append("</").append(FEEV10.getName()).append(":creData>");
            result.append("</extension>");
        }

        result.append(        "<trID>");
        result.append(            "<clTRID>ABC-12345</clTRID>");
        result.append(            "<svTRID>54321-XYZ</svTRID>");
        result.append(        "</trID>");
        result.append(    "</response>");
        result.append("</epp>");
        return result.toString();
    }
    private static String getCreateResponseFullFeeExpectedXml(final String domainName, boolean feeExtension) {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"");
        result.append(    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        result.append(    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">");
        result.append(    "<response>");
        result.append(        "<result code=\"1000\">");
        result.append(            "<msg>Command completed successfully</msg>");
        result.append(        "</result>");
        result.append(        "<resData>");
        result.append(            "<domain:creData xmlns:domain=\"urn:ietf:params:xml:ns:domain-1.0\"");
        result.append(                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">");
        result.append(                "<domain:name>" + domainName + "</domain:name>");
        result.append(                "<domain:crDate>1999-04-03T22:00:00.0Z</domain:crDate>");
        result.append(                "<domain:exDate>2001-04-03T22:00:00.0Z</domain:exDate>");
        result.append(            "</domain:creData>");
        result.append(        "</resData>");

        if (feeExtension) {
            result.append("<extension>");
            result.append("<").append(FEEV10.getName()).append(":creData xmlns:")
                              .append(FEEV10.getName()).append("=\"urn:ietf:params:xml:ns:epp:fee-1.0\">");
            result.append("<").append(FEEV10.getName()).append(":currency>" + "USD" + "</")
                              .append(FEEV10.getName()).append(":currency>");
            result.append("<").append(FEEV10.getName()).append(":fee")
                              .append(" description=\"Registration Fee\"")
                              .append(" lang=\"ja\"")
                              .append(" refundable=\"1\"")
                              .append(" applied=\"immediate\"")
                              .append(" grace-period=\"P5D\"")
                              .append(">30.00</")
                              .append(FEEV10.getName()).append(":fee>");
            result.append("</").append(FEEV10.getName()).append(":creData>");
            result.append("</extension>");
        }

        result.append(        "<trID>");
        result.append(            "<clTRID>ABC-12345</clTRID>");
        result.append(            "<svTRID>54321-XYZ</svTRID>");
        result.append(        "</trID>");
        result.append(    "</response>");
        result.append("</epp>");
        return result.toString();
    }
}
