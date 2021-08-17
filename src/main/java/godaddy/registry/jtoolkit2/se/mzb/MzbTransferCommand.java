package godaddy.registry.jtoolkit2.se.mzb;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.ErrorPkg;
import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.se.ObjectType;
import godaddy.registry.jtoolkit2.se.StandardCommandType;
import godaddy.registry.jtoolkit2.se.TransferOp;

/**
 * The superclass of all MZB transfer command classes.  Subclasses are
 * responsible for specifying the kind of transfer operation, but hiding the
 * implementation from the user.
 */
public abstract class MzbTransferCommand extends Command {

    private static final long serialVersionUID = -5730286980387444992L;

    protected MzbTransferCommand(TransferOp operation, String roid, String pw) {
        super(StandardCommandType.TRANSFER);
        cmdElement.setAttribute("op", operation.toString());

        assertParameterSupplied(roid, "se.object.missing_arg");
        assertParameterSupplied(pw, "se.transfer.pw.missing");

        ObjectType objType = ExtendedObjectType.MZB;

        Element objElement = xmlWriter.appendChild(cmdElement, getCommandType().getCommandName(), objType.getURI());
        objElement.setAttribute("xsi:schemaLocation", objType.getSchemaLocation());

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);
        xmlWriter.appendChild(xmlWriter.appendChild(objElement, "authInfo"), "pw").setTextContent(pw);
    }

    private void assertParameterSupplied(String roid, String messageKey) {
        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage(messageKey));
        }
    }

}

