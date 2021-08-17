package godaddy.registry.jtoolkit2.se.block11;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.BLOCKV11;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.DomainCreateCommand;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Create command, representing the Update Block aspects of the Block extension.</p>
 *
 * <p>Use this to identify the block client assigned id that this command is being submitted
 * in as part of an EPP Domain Update command compliant with RFC5730 and RFC5731. The response expected from a
 * server should be handled by a Domain Update Response.</p>
 *
 * @see DomainCreateCommand
 * @see <a href="http://godaddyregistry.github.io/doc/block-1.1/block-1.1.html">Block Extension Mapping for the
 * Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainUpdateBlockCommandExtension implements CommandExtension {
    private static final long serialVersionUID = 4324879283895987704L;

    private String id;
    private String onExpiryAction;
    private OnExpiryAction onExpiryType;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element createElement = xmlWriter.appendChild(extensionElement, "update", BLOCKV11.getURI());
        if (id != null) {
            xmlWriter.appendChild(createElement, "id", BLOCKV11.getURI()).setTextContent(id);
        }

        if (onExpiryType != null) {
            Element chg = xmlWriter.appendChild(createElement, "chg");
            final Element chgElement = xmlWriter.appendChild(chg, "onExpiry", "action", onExpiryType.getCode());
            if (onExpiryType.equals(OnExpiryAction.CUSTOM)) {
                chgElement.setTextContent(onExpiryAction);
            }
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOnExpiryAction(String onExpiryAction) {
        this.onExpiryAction = onExpiryAction;
    }

    public void setOnExpiryType(OnExpiryAction onExpiryType) {
        this.onExpiryType = onExpiryType;
    }
}
