package godaddy.registry.jtoolkit2.se.idn.ietf;
import org.w3c.dom.Element;
import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Check command, representing the Check IDN Domain aspect of the
 * Internationalized Domain Names extension.</p>
 *
 * <p>Use this to set the language tag to be used for an IDN as part of an EPP Domain Check command.
 * The response expected from a server should be
 * handled by a Domain Check Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCheckCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCheckResponse
 * @see <a href="http://godaddyregistry.github.io/doc/idn-1.0/idn-1.0.html">Internationalized Domain Name Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCheckIeftIdnEPPCommandExtension implements CommandExtension {

    private final String table;

    private final String uname;

    /**
     * Instantiates a new domain check idn EPP command extension.
     * @param table The IDN language. Required.
     * @param uname the domain name in Unicode format, optional parameter.
     * @throws IllegalArgumentException if {@code table} is {@code null} or empty.
     */
    public DomainCheckIeftIdnEPPCommandExtension(String table, String uname) {
        if (table == null || table.isEmpty()) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.ar.domain.create.ietf.idn.table"));
        }
        this.table = table;
        this.uname = uname;
    }

    /*
     * (non-Javadoc)
     *
     * @see godaddy.registry.jtoolkit2.se.CommandExtension#addToCommand(godaddy.registry.jtoolkit2.se.Command)
     */
    @Override
    public void addToCommand(final Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element dataElement = xmlWriter.appendChild(extensionElement,
                "data", ExtendedObjectType.IETF_IDN.getURI());
        xmlWriter.appendChild(dataElement, "table").setTextContent(table);
        if (uname != null) {
            xmlWriter.appendChild(dataElement, "uname").setTextContent(uname);
        }
   }

}
