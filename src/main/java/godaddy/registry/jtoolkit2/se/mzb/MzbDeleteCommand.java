package godaddy.registry.jtoolkit2.se.mzb;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.se.ObjectCommand;
import godaddy.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP mzb:delete command
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to generate an GoDaddy Registry-compliant XML document, given
 * simple input parameters.  The toXML method in Command serialises this object
 * to XML.
 *
 * @see MzbDeleteResponse
 */
public class MzbDeleteCommand extends ObjectCommand {
    private static final long serialVersionUID = -3723213074751854975L;

    /**
     * @throws IllegalArgumentException if {@code roid} is {@code null}.
     */
    public MzbDeleteCommand(String roid) {
        super(StandardCommandType.DELETE, ExtendedObjectType.MZB);

        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.delete.roid.missing_arg"));
        }

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);
    }

}

