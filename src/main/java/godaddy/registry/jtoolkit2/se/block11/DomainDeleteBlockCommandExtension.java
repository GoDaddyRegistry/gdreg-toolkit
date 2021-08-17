package godaddy.registry.jtoolkit2.se.block11;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.BLOCKV11;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain delete command, representing the Delete Block aspect of the Block extension.</p>
 *
 * <p>Use this to mark the ID of a Block to delete as part of an EPP Domain Delete command
 * compliant with RFC5730 and RFC5731. The response expected from a server should be
 * handled by a generic Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainDeleteCommand
 * @see godaddy.registry.jtoolkit2.se.Response
 * @see <a href="http://neustarregistry.github.io/doc/block-1.1/block-1.1.html">Block Extension Mapping for the
 * Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainDeleteBlockCommandExtension implements CommandExtension {
    private static final long serialVersionUID = -3106443818428865374L;

    private String id;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element infoElement = xmlWriter.appendChild(extensionElement, "delete", BLOCKV11.getURI());

        xmlWriter.appendChild(infoElement, "id", BLOCKV11.getURI()).setTextContent(id);
    }

    public void setId(String id) {
        this.id = id;
    }
}
