package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.DomainRenewCommand;
import godaddy.registry.jtoolkit2.se.DomainRenewResponse;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Renew command, representing the Renew Premium Domain aspect of the
 * Domain Name Fee Extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Renew
 * command compliant with RFC5730 and RFC5731. The "currency" and "fee" values
 * supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Renew Response.</p>
 *
 * @see DomainRenewCommand
 * @see DomainRenewResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainRenewFeeCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 4982521830855586062L;

    private final BigDecimal fee;
    private final String currency;

    public DomainRenewFeeCommandExtension(BigDecimal fee, String currency) {
        this.fee = fee.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();

        final Element createElement = xmlWriter.appendChild(extensionElement, "renew", FEEV10.getURI());

        if (currency != null) {
            xmlWriter.appendChild(createElement, "currency").setTextContent(currency);
        }
        xmlWriter.appendChild(createElement, "fee").setTextContent(fee.toPlainString());
    }

}
