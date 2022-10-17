package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.math.BigDecimal;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Create command, representing the Create Premium Domain aspect of the
 * Domain Name Fee Extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Create
 * command compliant with RFC5730 and RFC5731. The "currency" and "registrationFee"
 * values supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Create Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCreateCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCreateResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCreateFeeCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 3982521830455586062L;

    private static final String FIELD_IDENTIFIER = "<<field>>";
    private BigDecimal registrationFee;
    private String currency;

    public DomainCreateFeeCommandExtension(BigDecimal registrationFee, String currency) {
        if (registrationFee != null) {
            this.registrationFee = registrationFee;
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "registrationFee"));
        }
        this.currency = currency;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        registrationFee = registrationFee.setScale(2);

        final Element createElement = xmlWriter.appendChild(extensionElement, "create", FEEV10.getURI());

        if (currency != null) {
            xmlWriter.appendChild(createElement, "currency").setTextContent(currency);
        }

        Element registrationFeeTag = xmlWriter.appendChild(createElement, "fee");
        registrationFeeTag.setTextContent(registrationFee.toPlainString());
    }

}
