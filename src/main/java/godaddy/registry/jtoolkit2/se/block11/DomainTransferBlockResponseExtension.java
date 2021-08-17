package godaddy.registry.jtoolkit2.se.block11;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.BLOCKV11;

import javax.xml.xpath.XPathExpressionException;

import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Create response, representing the Block Transfer Block extension.</p>
 *
 * <p>Use this to access create data for a domain block as provided in an EPP Domain Transfer response compliant
 * with RFC5730 and RFC5731. Such a service element is sent by a compliant EPP server in response to a valid
 * Domain Transfer command with the Block extension.</p>
 *
 * <p>For flexibility, this implementation extracts the data from the response using XPath queries, the expressions
 * for which are defined statically.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainTransferCommand
 * @see DomainTransferBlockCommandExtension
 * @see <a href="http://godaddyregistry.github.io/doc/block-1.1/block-1.1.html">Block Extension Mapping for the
 * Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainTransferBlockResponseExtension extends ResponseExtension {
    private static final long serialVersionUID = -4403413192868009866L;

    private static final String BLO_PREFIX = BLOCKV11.getName();
    private static final String XPATH_SEP = "/";
    private static final String BLO_XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + XPATH_SEP + BLO_PREFIX
            + ":RESPONSE_TYPE/" + BLO_PREFIX;
    private static final String ID = BLO_XPATH_PREFIX + ":id/text()";
    private static final String RESPONSE_TYPE = ResponseExtension.TRANSFER;

    private String blockId;
    private boolean initialised;

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        blockId = xmlDoc.getNodeValue(replaceResponseType(ID, RESPONSE_TYPE));
        initialised = blockId != null;
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public String getBlockId() {
        return blockId;
    }
}
