package godaddy.registry.jtoolkit2.se.fee10;


import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;
import static godaddy.registry.jtoolkit2.se.ReceiveSE.replaceIndex;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.NodeList;

import godaddy.registry.jtoolkit2.se.DomainCreateCommand;
import godaddy.registry.jtoolkit2.se.DomainCreateResponse;
import godaddy.registry.jtoolkit2.se.Period;
import godaddy.registry.jtoolkit2.se.PeriodUnit;
import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Check response, representing the Fee
 * extension.</p>
 *
 * <p>Use this to check the price associated with this domain name as part of an EPP Domain Check
 * command compliant with RFC5730 and RFC5731. The "name", "command", "phase", "unit", "period", "class", "currency"
 * and "fee" values supplied, should match the fields that are requested for the domain name check for the requested
 * period. The response expected from a server should be handled by a Domain Check Response.</p>
 *
 * @see DomainCreateCommand
 * @see DomainCreateResponse
 * @see <a href="https://tools.ietf.org/html/draft-brown-epp-fees-03">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCheckFeeResponseExtension extends ResponseExtension {

    private static final String FEE_CHECK_PREFIX = FEEV10.getName();

    private static final String CHKDATA_EXPR = replaceResponseType(ResponseExtension.EXTENSION_EXPR
            + "/"  + FEE_CHECK_PREFIX + ":RESPONSE_TYPE", ResponseExtension.CHK_DATA);

    private static final String CHKDATA_CURRENCY_EXPR = CHKDATA_EXPR + "/" + FEE_CHECK_PREFIX + ":currency/text()";
    private static final String CHKDATA_COUNT_EXPR = "count(" + CHKDATA_EXPR + "/*)";
    private static final String CHKDATA_IND_EXPR = CHKDATA_EXPR + "/" + FEE_CHECK_PREFIX + ":cd[IDX]";
    private static final String CHKDATA_COMMAND_IND_EXPR = "/" + FEE_CHECK_PREFIX + ":command[IDX]";
    private static final String CHKDATA_DOMAIN_NAME_EXPR = "/" + FEE_CHECK_PREFIX + ":objID/text()";
    private static final String CHKDATA_DOMAIN_FEE_CLASS_EXPR = "/" + FEE_CHECK_PREFIX + ":class/text()";
    private static final String CHKDATA_DOMAIN_REASON_EXPR = "/" + FEE_CHECK_PREFIX + ":reason";
    private static final String CHKDATA_COMMAND_NAME_EXPR = "/@name";
    private static final String CHKDATA_COMMAND_PHASE_EXPR = "/@phase";
    private static final String CHKDATA_COMMAND_SUBPHASE_EXPR = "/@subphase";
    private static final String CHKDATA_COMMAND_REASON_EXPR = "/" + FEE_CHECK_PREFIX + ":reason";
    private static final String CHKDATA_COMMAND_PERIOD_EXPR = "/" + FEE_CHECK_PREFIX + ":period/text()";
    private static final String CHKDATA_COMMAND_PERIOD_UNIT_EXPR = "/" + FEE_CHECK_PREFIX + ":period/@unit";

    private static final String CHKDATA_FEE_NODES_EXPR =  "/" + FEE_CHECK_PREFIX + ":fee";
    private static final String CHKDATA_CREDIT_NODES_EXPR =  "/" + FEE_CHECK_PREFIX + ":credit";
    private static final long serialVersionUID = 8656976876683357088L;

    private boolean initialised;

    private FeeCheckData feeCheckData;

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        feeCheckData = new FeeCheckData();
        feeCheckData.setCurrency(parseTextValue(xmlDoc, CHKDATA_CURRENCY_EXPR));
        int checkDataCount = xmlDoc.getNodeCount(CHKDATA_COUNT_EXPR);
        for (int checkDataIndex = 0; checkDataIndex < checkDataCount; checkDataIndex++) {
            final FeeCD feeCD = new FeeCD();
            parseFeeCDNode(xmlDoc, feeCD, checkDataIndex);
            feeCheckData.addCD(feeCD);
        }
        initialised = checkDataCount > 0;
    }

    private void parseFeeCDNode(XMLDocument xmlDoc, FeeCD feeCD, int checkDataIndex) throws XPathExpressionException {
        String cdQueryPath = replaceIndex(CHKDATA_IND_EXPR, checkDataIndex + 1);

        feeCD.setObjId(parseTextValue(xmlDoc, cdQueryPath + CHKDATA_DOMAIN_NAME_EXPR));
        feeCD.setFeeClass(parseTextValue(xmlDoc, cdQueryPath + CHKDATA_DOMAIN_FEE_CLASS_EXPR));

        int commandCount = xmlDoc.getNodeCount("count(" + cdQueryPath + "/" + FEE_CHECK_PREFIX + ":command)");
        for (int i = 0; i < commandCount; i++) {
            feeCD.addCommand(parseCommandNode(xmlDoc, cdQueryPath, i));
        }
        feeCD.setReason(parseReason(xmlDoc, cdQueryPath + CHKDATA_DOMAIN_REASON_EXPR));
        if ("1".equals(parseTextValue(xmlDoc, cdQueryPath + "/@avail"))) {
            feeCD.setAvailable(true);
        }
    }

    private String parseTextValue(XMLDocument xmlDoc, String queryPath) throws XPathExpressionException {
        return xmlDoc.getNodeValue(queryPath);
    }

    private FeeCheckCommand parseCommandNode(XMLDocument xmlDoc, String cdQueryPath, int commandIndex)
            throws XPathExpressionException {
        String commandQueryPath = cdQueryPath + replaceIndex(CHKDATA_COMMAND_IND_EXPR, commandIndex + 1);

        final FeeCheckCommand command =
                new FeeCheckCommand(xmlDoc.getNodeValue(commandQueryPath + CHKDATA_COMMAND_NAME_EXPR),
                        xmlDoc.getNodeValue(commandQueryPath + CHKDATA_COMMAND_PHASE_EXPR),
                        xmlDoc.getNodeValue(commandQueryPath + CHKDATA_COMMAND_SUBPHASE_EXPR));
        command.setPeriod(parsePeriod(xmlDoc, commandQueryPath));

        parseFeeNodes(xmlDoc, command, commandQueryPath + CHKDATA_FEE_NODES_EXPR);
        parseCreditNodes(xmlDoc, command, commandQueryPath + CHKDATA_CREDIT_NODES_EXPR);
        command.setReason(parseReason(xmlDoc, commandQueryPath + CHKDATA_COMMAND_REASON_EXPR));

        return command;
    }

    private ReasonType parseReason(XMLDocument xmlDoc, String reasonQueryPath) throws XPathExpressionException {
        final String reasonValue = parseTextValue(xmlDoc, reasonQueryPath + "/text()");
        final String langValue = parseTextValue(xmlDoc, reasonQueryPath + "/@lang");

        return (reasonValue == null) ? null : new ReasonType(langValue, reasonValue);
    }

    private Period parsePeriod(XMLDocument xmlDoc, String checkDataQueryPath) throws XPathExpressionException {
        final String periodValue = parseTextValue(xmlDoc, checkDataQueryPath + CHKDATA_COMMAND_PERIOD_EXPR);
        final String unitValue = parseTextValue(xmlDoc, checkDataQueryPath + CHKDATA_COMMAND_PERIOD_UNIT_EXPR);
        return (periodValue != null)
                ? new Period(PeriodUnit.value(unitValue), Integer.parseInt(periodValue))
                : null;
    }

    private void parseFeeNodes(XMLDocument xmlDoc, FeeCheckCommand command, String queryPath)
            throws XPathExpressionException {
        NodeList nodes = xmlDoc.getElements(queryPath);
        if (nodes != null) {
            for (int nodeIndex = 0; nodeIndex < nodes.getLength(); nodeIndex++) {
                command.addFee(new FeeType(nodes.item(nodeIndex)));
            }
        }
    }

    private void parseCreditNodes(XMLDocument xmlDoc, FeeCheckCommand command, String queryPath)
            throws XPathExpressionException {
        NodeList nodes = xmlDoc.getElements(queryPath);
        if (nodes != null) {
            for (int nodeIndex = 0; nodeIndex < nodes.getLength(); nodeIndex++) {
                command.addCredit(new CreditType(nodes.item(nodeIndex)));
            }
        }
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public FeeCheckData getFeeCheckData() {
        return feeCheckData;
    }
}
