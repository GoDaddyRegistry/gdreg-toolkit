package godaddy.registry.jtoolkit2.se.fee10;

import java.math.BigDecimal;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class FeeType {

    private String language = "en";
    private boolean refundable;
    private String gracePeriod;
    private AppliedType applied;
    private BigDecimal fee;
    private String description;

    public FeeType(Node node) {
        final NamedNodeMap attributes = node.getAttributes();
        Node descriptionNode = attributes.getNamedItem("description");
        description = "Default";
        if (descriptionNode != null) {
            description = descriptionNode.getNodeValue();
        }
        final Node languageNode = attributes.getNamedItem("lang");
        if (languageNode != null) {
            language = languageNode.getNodeValue();
        }
        final Node refundableNode = attributes.getNamedItem("refundable");
        if (refundableNode != null) {
            refundable = refundableNode.getNodeValue().equals("1");
        }
        final Node gracePeriodNode = attributes.getNamedItem("grace-period");
        if (gracePeriodNode != null) {
            gracePeriod = gracePeriodNode.getNodeValue();
        }
        final Node appliedNode = attributes.getNamedItem("applied");
        if (appliedNode != null) {
            applied = AppliedType.valueOf(appliedNode.getNodeValue());
        }

        fee = new BigDecimal(node.getFirstChild().getNodeValue());
    }

    public FeeType() {
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public String getGracePeriod() {
        return gracePeriod;
    }

    public AppliedType getApplied() {
        return applied;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
