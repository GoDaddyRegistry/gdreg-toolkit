package godaddy.registry.jtoolkit2.se.price;

import java.math.BigDecimal;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;

/**
 * <p>Extension for the EPP Domain Transfer command, representing the Transfer Premium Domain aspect of the
 * Domain Name Price extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Transfer
 * command compliant with RFC5730 and RFC5731. The "price" value is optional, but if it is
 * supplied, should match the renewal fee that is set for the domain name for the one year.
 * The response expected from a server should be handled by a Domain Transfer Response object.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainTransferRequestCommand
 * @see godaddy.registry.jtoolkit2.se.DomainTransferResponse
 * @see <a href="http://godaddyregistry.github.io/doc/price-1.1/price-1.1.html">Domain Name Price Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainTransferRequestPriceV1_1CommandExtension implements CommandExtension {

    private BigDecimal price;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element transferElement = xmlWriter.appendChild(extensionElement, "transfer",
                ExtendedObjectType.PRICEV11.getURI());
        Element ackElement = xmlWriter.appendChild(transferElement, "ack");

        if (price != null) {
            xmlWriter.appendChild(ackElement, "price").setTextContent(price.toPlainString());
        }
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
