package godaddy.registry.jtoolkit2.se.cnnic;

import javax.xml.xpath.XPathExpressionException;

import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * The Class ContactInfoCNNICResponseExtension.
 */
@SuppressWarnings("serial")
public final class ContactInfoCNNICResponseExtension extends ResponseExtension {

    /** The Constant EXTN_PREFIX. */
    private static final String EXTN_PREFIX = ExtendedObjectType.CNNIC_CONTACT.getName();

    /** The Constant NEULEVEL_DATA_EXPRESSION. */
    private static final String NEULEVEL_DATA_EXPRESSION = EXTENSION_EXPR + "/" + EXTN_PREFIX + ":infData/";

    /** The Constant CHKDATA_COUNT_EXPR. */
    private static final String CHKDATA_COUNT_EXPR = "count(" + NEULEVEL_DATA_EXPRESSION + "*)";

    /** The Constant CHKDATA_TYPE_DETAILS_EXPR. */
    private static final String CHKDATA_TYPE_DETAILS_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":type";

    /** The Constant CHKDATA_CONTACT_DETAILS_EXPR. */
    private static final String CHKDATA_CONTACT_DETAILS_EXPR =
        NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":contact";

    /** The Constant CHKDATA_CONTACT_MOBILE_DETAILS_EXPR. */
    private static final String CHKDATA_CONTACT_MOBILE_DETAILS_EXPR =
        NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":mobile";

    /** The Constant CHKDATA_CONTACT_TYPE_DETAILS_EXPR. */
    private static final String CHKDATA_CONTACT_TYPE_DETAILS_EXPR =
        NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":contact/@type";
    /** The initialised. */
    private boolean initialised;

    /** The cn contact DTO. */
    private CNContact cnContactDTO;

    /*
     * (non-Javadoc)
     *
     * @see godaddy.registry.jtoolkit2.se.ResponseExtension#fromXML(godaddy.registry.jtoolkit2.xml.XMLDocument)
     */
    @Override
    public void fromXML(final XMLDocument xmlDoc) throws XPathExpressionException {

        int elementCount = xmlDoc.getNodeCount(CHKDATA_COUNT_EXPR);

        if (elementCount > 0) {
            cnContactDTO = new CNContact();
            cnContactDTO.setType(xmlDoc.getNodeValue(CHKDATA_TYPE_DETAILS_EXPR));
            cnContactDTO.setContactTypeName(xmlDoc.getNodeValue(CHKDATA_CONTACT_DETAILS_EXPR));
            cnContactDTO.setMobile(xmlDoc.getNodeValue(CHKDATA_CONTACT_MOBILE_DETAILS_EXPR));
            cnContactDTO.setContactTypeId(xmlDoc.getNodeValue(CHKDATA_CONTACT_TYPE_DETAILS_EXPR));

            initialised = true;
        }

    }

    /**
     * Checks if is initialised.
     *
     * @return true, if is initialised
     */
    @Override
    public boolean isInitialised() {
        return initialised;
    }

    /**
     * Gets the CN contact DTO.
     *
     * @return the CN contact DTO
     */
    public CNContact getCNContactDTO() {
        return cnContactDTO;
    }
}
