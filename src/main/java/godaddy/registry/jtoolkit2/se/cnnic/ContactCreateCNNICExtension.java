package godaddy.registry.jtoolkit2.se.cnnic;

import org.w3c.dom.Element;

import godaddy.registry.jtoolkit2.se.Command;
import godaddy.registry.jtoolkit2.se.CommandExtension;
import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.xml.XMLWriter;

/**
 * The Class ContactCreateCNNICExtension.
 */
public class ContactCreateCNNICExtension implements CommandExtension {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3982521830455586062L;

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
    public ContactCreateCNNICExtension() {
        // intentionally do nothing
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

        final Element createElement = xmlWriter.appendChild(extensionElement,
            "create", ExtendedObjectType.CNNIC_CONTACT.getURI());
        createElement.setPrefix(ExtendedObjectType.CNNIC_CONTACT.getName());

        xmlWriter.appendChild(createElement, "type").setTextContent(type);

        final Element contactElement = xmlWriter.appendChild(createElement, "contact");
        contactElement.setAttribute("type", contactTypeId);
        contactElement.setTextContent(contactTypeName);
        xmlWriter.appendChild(createElement, "mobile").setTextContent(mobile);
    }

}
