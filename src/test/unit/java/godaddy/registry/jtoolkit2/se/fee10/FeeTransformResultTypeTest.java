package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import godaddy.registry.jtoolkit2.se.Period;
import godaddy.registry.jtoolkit2.se.PeriodUnit;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class FeeTransformResultTypeTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldGetFeeDetails() throws Exception {
        final FeeTransformResultType feeResponseExtension = new Dummy();
        final XMLDocument doc = PARSER.parse(getCreateResponseFullFeeExpectedXml());

        feeResponseExtension.fromXML(doc);
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
        final Map<String, CreditType> creditType = feeResponseExtension.getCreditType();
        assertEquals(1, creditType.size());
        final CreditType credit = creditType.get("Credit Fee");
        assertEquals("ja", credit.getLanguage());
        assertEquals("-20.00", credit.getFee().toPlainString());
        final Period period = feeResponseExtension.getPeriod();
        assertEquals(3, period.getPeriod());
        assertEquals(PeriodUnit.YEARS, period.getUnit());
        assertEquals("30.00", feeResponseExtension.getCreditLimit().toPlainString());
        assertEquals("20.00", feeResponseExtension.getBalance().toPlainString());

    }

    private static String getCreateResponseFullFeeExpectedXml() {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"");
        result.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        result.append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">");
        result.append("<response>");
        result.append("<result code=\"1000\">");
        result.append("<msg>Command completed successfully</msg>");
        result.append("</result>");
        result.append("<extension>");
        result.append("<").append(FEEV10.getName()).append(":creData xmlns:")
                .append(FEEV10.getName()).append("=\"urn:ietf:params:xml:ns:epp:fee-1.0\">");
        result.append("<").append(FEEV10.getName()).append(":currency>" + "USD" + "</")
                .append(FEEV10.getName()).append(":currency>");
        result.append("<").append(FEEV10.getName()).append(":period unit=\"y\">3</")
                .append(FEEV10.getName()).append(":period>");
        result.append("<").append(FEEV10.getName()).append(":fee")
                .append(" description=\"Registration Fee\"")
                .append(" lang=\"ja\"")
                .append(" refundable=\"1\"")
                .append(" applied=\"immediate\"")
                .append(" grace-period=\"P5D\"")
                .append(">30.00</")
                .append(FEEV10.getName()).append(":fee>");
        result.append("<").append(FEEV10.getName()).append(":credit")
                .append(" description=\"Credit Fee\"")
                .append(" lang=\"ja\"")
                .append(">-20.00</")
                .append(FEEV10.getName()).append(":credit>");
        result.append("<").append(FEEV10.getName()).append(":balance")
                .append(">20.00</")
                .append(FEEV10.getName()).append(":balance>");
        result.append("<").append(FEEV10.getName()).append(":creditLimit")
                .append(">30.00</")
                .append(FEEV10.getName()).append(":creditLimit>");
        result.append("</").append(FEEV10.getName()).append(":creData>");
        result.append("</extension>");
        result.append("<trID>");
        result.append("<clTRID>ABC-12345</clTRID>");
        result.append("<svTRID>54321-XYZ</svTRID>");
        result.append("</trID>");
        result.append("</response>");
        result.append("</epp>");

        return result.toString();
    }

    class Dummy extends FeeTransformResultType {

        Dummy() {
            super(CREATE);
        }
    }
}

