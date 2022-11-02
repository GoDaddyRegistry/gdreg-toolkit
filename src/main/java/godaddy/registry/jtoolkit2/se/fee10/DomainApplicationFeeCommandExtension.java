package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Create command, representing the Create Premium Domain aspect of the
 * Domain Name Fee Extension during a launch phase to create an application.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Create
 * command compliant with RFC5730 and RFC5731. The "currency", "applicationFee", "allocationFee" and "registrationFee"
 * values supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Create Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCreateCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCreateResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 *  Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainApplicationFeeCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 3982521830455586062L;

    private static final String FIELD_IDENTIFIER = "<<field>>";
    private final BigDecimal applicationFee;
    private final BigDecimal allocationFee;
    private final BigDecimal registrationFee;
    private final String currency;
    private final String applicationFeeDescription;
    private final String allocationFeeDescription;
    private final String registrationFeeDescription;

    public DomainApplicationFeeCommandExtension(BigDecimal applicationFee, BigDecimal allocationFee,
                                                BigDecimal registrationFee, String currency) {
        this(applicationFee, allocationFee, registrationFee, currency, "Application Fee",
                "Allocation Fee", "Registration Fee");
    }

    public DomainApplicationFeeCommandExtension(BigDecimal applicationFee, BigDecimal allocationFee,
                                                BigDecimal registrationFee, String currency,
                                                String applicationFeeDescription,
                                                String allocationFeeDescription,
                                                String registrationFeeDescription) {
        if (applicationFee != null) {
            this.applicationFee = applicationFee.setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "applicationFee"));
        }
        if (allocationFee != null) {
            this.allocationFee = allocationFee.setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "allocationFee"));
        }
        if (registrationFee != null) {
            this.registrationFee = registrationFee.setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "registrationFee"));
        }

        if (applicationFeeDescription != null) {
            this.applicationFeeDescription = applicationFeeDescription;
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "applicationFeeDescription"));
        }
        if (allocationFeeDescription != null) {
            this.allocationFeeDescription = allocationFeeDescription;
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "allocationFeeDescription"));
        }
        if (registrationFeeDescription != null) {
            this.registrationFeeDescription = registrationFeeDescription;
        } else {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                    "registrationFeeDescription"));
        }

        this.currency = currency;

    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();

        final Element createElement = xmlWriter.appendChild(extensionElement, "create", FEEV10.getURI());
        xmlWriter.appendChild(createElement, "currency").setTextContent(currency);

        Element applicationFeeTag = xmlWriter.appendChild(createElement, "fee");
        applicationFeeTag.setTextContent(applicationFee.toPlainString());
        applicationFeeTag.setAttribute("description", applicationFeeDescription);

        Element allocationFeeTag = xmlWriter.appendChild(createElement, "fee");
        allocationFeeTag.setTextContent(allocationFee.toPlainString());
        allocationFeeTag.setAttribute("description", allocationFeeDescription);

        Element registrationFeeTag = xmlWriter.appendChild(createElement, "fee");
        registrationFeeTag.setTextContent(registrationFee.toPlainString());
        registrationFeeTag.setAttribute("description", registrationFeeDescription);
    }

}
