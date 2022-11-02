package godaddy.registry.jtoolkit2.se.fee10;

import java.math.BigDecimal;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class CreditType {

    private String language = "en";
    private final BigDecimal fee;
    private String description;

    public CreditType(Node node) {
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

        fee = new BigDecimal(node.getFirstChild().getNodeValue());
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
