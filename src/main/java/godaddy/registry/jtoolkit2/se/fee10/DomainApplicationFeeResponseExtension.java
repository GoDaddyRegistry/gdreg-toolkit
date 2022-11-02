package godaddy.registry.jtoolkit2.se.fee10;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Create response, representing the Fee
 * extension during a launch phase to create an application.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Create
 * command compliant with RFC5730 and RFC5731. The "currency", "applicationFee", "allocationFee" and "registrationFee"
 * values supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Create Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCreateCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCreateResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension Mapping
 * for the Extensible Provisioning Protocol (EPP)</a>
 */
public final class DomainApplicationFeeResponseExtension extends ResponseExtension {

    private static final long serialVersionUID = -6007874008986690757L;

    private static final String FEE_PREFIX = ExtendedObjectType.FEEV10.getName();

    private static final String FEE_XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + "/" + FEE_PREFIX
            + ":RESPONSE_TYPE/" + FEE_PREFIX;
    private static final String CURRENCY_EXPR = FEE_XPATH_PREFIX + ":currency/text()";
    private static final String FEE_EXPR = FEE_XPATH_PREFIX + ":fee";

    private final String responseType;

    private boolean initialised = false;

    private String currency;
    private final Map<String, BigDecimal> fee = new HashMap<>();

    public DomainApplicationFeeResponseExtension(String responseType) {
        this.responseType = responseType;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        currency = xmlDoc.getNodeValue(replaceResponseType(CURRENCY_EXPR, responseType));
        NodeList feeNodeList = xmlDoc.getElements(replaceResponseType(FEE_EXPR, responseType));
        if (feeNodeList != null) {
            int nodeLength = feeNodeList.getLength();
            for (int i = 0; i < nodeLength; i++) {
                Node node = feeNodeList.item(i);
                Node descriptionNode = node.getAttributes().getNamedItem("description");
                if (descriptionNode != null) {
                    String description = descriptionNode.getNodeValue();
                    fee.put(description, new BigDecimal(node.getTextContent()));
                }
            }
        }

        initialised = currency != null && fee.size() == 3;
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getFee(String feeDescription) {
        return fee.get(feeDescription);
    }
}
