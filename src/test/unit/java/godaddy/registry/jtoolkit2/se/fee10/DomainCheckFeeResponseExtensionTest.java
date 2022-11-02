package godaddy.registry.jtoolkit2.se.fee10;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;

import godaddy.registry.jtoolkit2.se.DomainCheckResponse;
import godaddy.registry.jtoolkit2.xml.XMLDocument;
import godaddy.registry.jtoolkit2.xml.XMLParser;

public class DomainCheckFeeResponseExtensionTest {
    private static final XMLParser PARSER = new XMLParser();
    private static final String DOMAIN_NAME = "jtkutest.com.au";

    @Test
    public void shouldGetDomainCheckFeeExtension() throws Exception {

        final DomainCheckResponse response = new DomainCheckResponse();
        final DomainCheckFeeResponseExtension feeCheckExtension =  new DomainCheckFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml());
        response.registerExtension(feeCheckExtension);
        response.fromXML(doc);

        final FeeCheckData feeCheckData = feeCheckExtension.getFeeCheckData();
        final Map<String, FeeCD> cDs = feeCheckData.getCDs();
        assertThat(cDs.size(), is(2));

        FeeCD fee = cDs.get(DOMAIN_NAME);
        assertThat(feeCheckData.getCurrency(), is("USD"));
        assertThat(fee.getFeeClass(), is("premium-tier1"));
        assertThat(fee.getObjId(), is(DOMAIN_NAME));

        final FeeCheckCommand command = fee.getCommands().get("create");
        assertThat(command.getName(), is("create"));
        assertThat(command.getPhase(), is("sunrise"));
        assertThat(command.getPeriod().getPeriod(), is(1));
        assertThat(command.getPeriod().getUnit().toString(), is("y"));
        assertThat(command.getFees().size(), is(2));
        assertThat(command.getFees().get("Application Fee").getDescription(), is("Application Fee"));
        assertThat(command.getFees().get("Application Fee").getFee(), is(new BigDecimal("5.00")));
        assertThat(command.getFees().get("Application Fee").isRefundable(), is(false));
        assertThat(command.getFees().get("Registration Fee").getDescription(), is("Registration Fee"));
        assertThat(command.getFees().get("Registration Fee").getFee(), is(new BigDecimal("5.00")));
        assertThat(command.getFees().get("Registration Fee").isRefundable(), is(true));

    }

    @Test
    public void shouldGetDomainCheckFeeExtensionWithCredits() throws Exception {

        final DomainCheckResponse response = new DomainCheckResponse();
        final DomainCheckFeeResponseExtension feeCheckExtension =  new DomainCheckFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseXmlWithCredits());
        response.registerExtension(feeCheckExtension);
        response.fromXML(doc);

        final FeeCheckData feeCheckData = feeCheckExtension.getFeeCheckData();
        final Map<String, FeeCD> cDs = feeCheckData.getCDs();
        assertThat(cDs.size(), is(2));

        FeeCD fee = cDs.get(DOMAIN_NAME);
        assertThat(feeCheckData.getCurrency(), is("USD"));
        assertThat(fee.getFeeClass(), is("premium-tier1"));
        assertThat(fee.getObjId(), is(DOMAIN_NAME));

        final FeeCheckCommand command = fee.getCommands().get("create");
        assertThat(command.getName(), is("create"));
        assertThat(command.getPhase(), is("sunrise"));
        assertThat(command.getPeriod().getPeriod(), is(1));
        assertThat(command.getPeriod().getUnit().toString(), is("y"));
        assertThat(command.getFees().size(), is(2));
        assertThat(command.getCredits().size(), is(1));
        assertThat(command.getFees().get("Application Fee").getDescription(), is("Application Fee"));
        assertThat(command.getFees().get("Application Fee").getFee(), is(new BigDecimal("5.00")));
        assertThat(command.getFees().get("Application Fee").isRefundable(), is(false));
        assertThat(command.getFees().get("Registration Fee").getDescription(), is("Registration Fee"));
        assertThat(command.getFees().get("Registration Fee").getFee(), is(new BigDecimal("5.00")));
        assertThat(command.getFees().get("Registration Fee").isRefundable(), is(true));
        assertThat(command.getCredits().get("Registration Fee").getFee(), is(new BigDecimal("-3.00")));

    }

    @Test
    public void shouldAcceptDomainCheckFeeExtensionWithoutDescription() throws Exception {

        final DomainCheckResponse response = new DomainCheckResponse();
        final DomainCheckFeeResponseExtension feeCheckExtension =  new DomainCheckFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXmlWithoutDescription());
        response.registerExtension(feeCheckExtension);
        response.fromXML(doc);

        FeeCheckData feeCheckData = feeCheckExtension.getFeeCheckData();
        final Map<String, FeeCD> cDs = feeCheckData.getCDs();
        assertThat(cDs.size(), is(2));

        FeeCD fee = cDs.get(DOMAIN_NAME);
        assertThat(feeCheckData.getCurrency(), is("USD"));
        assertThat(fee.getFeeClass(), is("premium-tier1"));
        assertThat(fee.getObjId(), is(DOMAIN_NAME));

        final FeeCheckCommand command = fee.getCommands().get("create");
        assertThat(command.getName(), is("create"));
        assertThat(command.getPhase(), is("sunrise"));
        assertThat(command.getPeriod().getPeriod(), is(1));
        assertThat(command.getPeriod().getUnit().toString(), is("y"));
        assertThat(command.getFees().size(), is(1));
        assertThat(command.getFees().get("Default").getDescription(), is("Default"));
        assertThat(command.getFees().get("Default").getFee(), is(new BigDecimal("5.00")));
        assertThat(command.getFees().get("Default").isRefundable(), is(true));

    }

    @Test
    public void shouldGetDomainCheckFeeExtensionWhenFeeIsNotAvailable() throws Exception {

        final DomainCheckResponse response = new DomainCheckResponse();
        final DomainCheckFeeResponseExtension feeCheckExtension =  new DomainCheckFeeResponseExtension();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXmlWithoutFee());
        response.registerExtension(feeCheckExtension);
        response.fromXML(doc);

        FeeCheckData feeCheckData = feeCheckExtension.getFeeCheckData();
        assertThat(feeCheckData.getCDs().size(), is(2));
        final FeeCD feeCD = feeCheckData.getCDs().get(DOMAIN_NAME);
        final Map<String, FeeCheckCommand> commands = feeCD.getCommands();
        assertThat(commands.size(), is(1));
        assertThat(commands.get("create").getFees().size(), is(0));
    }

    private String generateExpectedXml(String fee) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\">" +
                "<response>" +
                "<result code=\"1000\">" +
                "<msg>Command completed successfully</msg>" +
                "</result>" +
                "<resData>" +
                "<chkData xmlns=\"urn:ietf:params:xml:ns:domain-1.0\">" +
                "<cd>" +
                "<name avail=\"1\">" + DOMAIN_NAME + "</name>" +
                "</cd>" +
                "</chkData>" +
                "</resData>" +
                "<extension>" +
                "<chkData xmlns=\"urn:ietf:params:xml:ns:epp:fee-1.0\">" +
                "<currency>USD</currency>" +
                "<cd avail=\"1\">" +
                "<objID>" + DOMAIN_NAME + "</objID>" +
                "<class>premium-tier1</class>" +
                "<command name=\"create\" phase=\"sunrise\">" +
                "<period unit=\"y\">1</period>" +
                fee +
                "</command>" +
                "</cd>" +
                "</chkData>" +
                "</extension>" +
                "<trID>" +
                "<clTRID>ABC-12345</clTRID>" +
                "<svTRID>54322-XYZ</svTRID>" +
                "</trID>" +
                "</response>" +
                "</epp>";
    }

    private String getCreateResponseExpectedXml() {
        String fee = "<fee description=\"Application Fee\" refundable=\"0\">5.00</fee>" +
                "<fee description=\"Registration Fee\" refundable=\"1\">5.00</fee>";
        return generateExpectedXml(fee);
    }

    private String getCreateResponseXmlWithCredits() {
        String fee = "<fee description=\"Application Fee\" refundable=\"0\">5.00</fee>" +
                "<fee description=\"Registration Fee\" refundable=\"1\">5.00</fee>" +
                "<credit description=\"Registration Fee\">-3.00</credit>";
        return generateExpectedXml(fee);
    }

    private String getCreateResponseExpectedXmlWithoutDescription() {
        String fee = "<fee refundable=\"1\">5.00</fee>";
        return generateExpectedXml(fee);
    }

    private String getCreateResponseExpectedXmlWithoutFee() {
        return generateExpectedXml("");
    }
}