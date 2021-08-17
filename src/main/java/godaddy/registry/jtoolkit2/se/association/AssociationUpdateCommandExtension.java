package godaddy.registry.jtoolkit2.se.association;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.ASSOCIATION;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.xml.XMLWriter;


/**
 * <p>Extension for the EPP Domain Update command, representing the Update Association aspect of the
 * Domain Name Association extension.</p>
 *
 * <p>Use this to mark the ID of a domain name Association to update as part of an EPP Domain Update command
 * compliant with RFC5730 and RFC5731. The response expected from a server should be
 * handled by a generic Response object.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainUpdateCommand
 * @see godaddy.registry.jtoolkit2.se.Response
 */
public class AssociationUpdateCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 5202343696850193788L;
    private final AssociationAdd add;
    private final AssociationRem rem;

    public AssociationUpdateCommandExtension(AssociationAdd add, AssociationRem rem) {
        super();
        this.add = add;
        this.rem = rem;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element updateElement = xmlWriter.appendChild(extensionElement, "update", ASSOCIATION.getURI());

        if (add != null) {
            add.appendToElement(xmlWriter, updateElement);
        }

        if (rem != null) {
            rem.appendToElement(xmlWriter, updateElement);
        }

    }

}
