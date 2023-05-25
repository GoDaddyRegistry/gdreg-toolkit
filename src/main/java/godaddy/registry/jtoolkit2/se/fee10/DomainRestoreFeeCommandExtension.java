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
 * <p>Extension for the EPP Domain Renew command, representing the Restore/Renew Domain aspect of the
 * Domain Name Fee Extension.</p>
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Restore/Renew
 * command compliant with RFC5730 and RFC5731. The "currency" and "fee" values
 * supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Renew Response.</p>
 *
 * @see DomainRenewCommand
 * @see DomainRenewResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainRestoreFeeCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 4982521830855586062L;

    private BigDecimal renewFee;
    private BigDecimal restoreFee;
    private final String currency;
    private final String restoreDescription;
    private final String renewDescription;

    public DomainRestoreFeeCommandExtension(BigDecimal renewFee, BigDecimal restoreFee, String currency) {
        this(renewFee, restoreFee, currency, "Restore Fee", "Renewal Fee");
    }
    public DomainRestoreFeeCommandExtension(BigDecimal renewFee, BigDecimal restoreFee, String currency,
                                            String restoreDescription, String renewDescription) {
        this.renewFee = renewFee;
        this.restoreFee = restoreFee;
        this.currency = currency;
        this.restoreDescription = restoreDescription;
        this.renewDescription = renewDescription;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element createElement = xmlWriter.appendChild(extensionElement, "renew", FEEV10.getURI());
        if (currency != null) {
            xmlWriter.appendChild(createElement, "currency").setTextContent(currency);
        }

        if (restoreFee != null) {
            final Element restoreElement = xmlWriter.appendChild(createElement, "fee");
            restoreFee = restoreFee.setScale(2, RoundingMode.HALF_UP);
            restoreElement.setAttribute("description", restoreDescription);
            restoreElement.setTextContent(restoreFee.toPlainString());
        }

        if (renewFee != null) {
            final Element renewElement = xmlWriter.appendChild(createElement, "fee");
            renewFee = renewFee.setScale(2, RoundingMode.HALF_UP);
            renewElement.setAttribute("description", renewDescription);
            renewElement.setTextContent(renewFee.toPlainString());
        }

    }

}
