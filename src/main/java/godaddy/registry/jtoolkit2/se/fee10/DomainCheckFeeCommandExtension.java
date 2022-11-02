package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Check command, representing the Check Domain aspect of the
 * Domain Name Fee Extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Check
 * command compliant with RFC5730 and RFC5731. The "currency", "name", "command" and "period" values
 * supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Check Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCheckCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCheckResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCheckFeeCommandExtension implements CommandExtension {
    private static final String FIELD_IDENTIFIER = "<<field>>";
    private static final long serialVersionUID = 3059515034850142693L;
    private final List<FeeCheckCommand> feeCheckDataList = new ArrayList<>();
    private final String currency;

    public DomainCheckFeeCommandExtension(String currency, List<FeeCheckCommand> feeCds) {
        this.currency = currency;
        for (FeeCheckCommand feeCheckDomain : feeCds) {
            if (feeCheckDomain.getName() == null) {
                throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.check.fee", FIELD_IDENTIFIER,
                        "name"));
            }
            feeCheckDataList.add(feeCheckDomain);
        }
    }

    @Override
    public void addToCommand(Command command) {

        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element checkElement = xmlWriter.appendChild(extensionElement, "check", FEEV10.getURI());

        writeCurrency(xmlWriter, checkElement);
        for (FeeCheckCommand feeCheckCommand : feeCheckDataList) {
            writeCommand(xmlWriter, feeCheckCommand, checkElement);
        }
    }

    private void writeCurrency(XMLWriter xmlWriter, Element domainElement) {
        if (currency != null) {
            xmlWriter.appendChild(domainElement, "currency").setTextContent(currency);
        }
    }

    private void writeCommand(XMLWriter xmlWriter, FeeCheckCommand command, Element checkElement) {
        final Element commandElement = xmlWriter.appendChild(checkElement, "command");
        commandElement.setAttribute("name", command.getName());
        if (command.getPhase() != null) {
            commandElement.setAttribute("phase", command.getPhase());
        }
        if (command.getSubphase() != null) {
            commandElement.setAttribute("subphase", command.getSubphase());
        }
        writePeriod(xmlWriter, command, commandElement);
    }

    private void writePeriod(XMLWriter xmlWriter, FeeCheckCommand command, Element commandElement) {
        if (command.getPeriod() != null) {
            Element periodElement = xmlWriter.appendChild(commandElement, "period");
            periodElement.setTextContent(String.valueOf(command.getPeriod().getPeriod()));
            periodElement.setAttribute("unit", command.getPeriod().getUnit().toString());
        }
    }

}
