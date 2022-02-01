package godaddy.registry.jtoolkit2.se.cnnic;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * The Class ContactUpdateCNNICExtension.
 */
public class ContactUpdateCNNICExtension implements CommandExtension {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3982521830455586062L;

    /** The contact id. */
    private String contactId;

    /** The type. */
    private String type;

    /** The contact type id. */
    private String contactTypeId;

    /** The contact type name. */
    private String contactTypeName;

    /** The mobile. */
    private String mobile;

    /**
     * Instantiates a new cn nic contact extension.
     */
    public ContactUpdateCNNICExtension() {
        // intentionally do nothing
    }

    /**
     * Gets the contact id.
     *
     * @return the contact id
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Sets the contact id.
     *
     * @param contactId the new contact id
     */
    public void setContactId(final String contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the contact type id.
     *
     * @return the contact type id
     */
    public String getContactTypeId() {
        return contactTypeId;
    }

    /**
     * Sets the contact type id.
     *
     * @param contactTypeId the new contact type id
     */
    public void setContactTypeId(final String contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    /**
     * Gets the contact type name.
     *
     * @return the contact type name
     */
    public String getContactTypeName() {
        return contactTypeName;
    }

    /**
     * Sets the contact type name.
     *
     * @param contactTypeName the new contact type name
     */
    public void setContactTypeName(final String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    /**
     * Gets the mobile.
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile.
     *
     * @param mobile the new mobile
     */
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    /**
     * Adds the to command.
     *
     * @param command the command
     */
    @Override
    public void addToCommand(final Command command) {

        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();

        final Element updateElement = xmlWriter.appendChild(extensionElement,
            "update", ExtendedObjectType.CNNIC_CONTACT.getURI());
        updateElement.setPrefix(ExtendedObjectType.CNNIC_CONTACT.getName());

        final Element updateElementChg = xmlWriter.appendChild(updateElement, "chg");

        xmlWriter.appendChild(updateElementChg, "type").setTextContent(type);

        final Element contactElement = xmlWriter.appendChild(updateElementChg, "contact");
        contactElement.setAttribute("type", contactTypeId);
        contactElement.setTextContent(contactTypeName);
        xmlWriter.appendChild(updateElementChg, "mobile").setTextContent(mobile);
    }

}
