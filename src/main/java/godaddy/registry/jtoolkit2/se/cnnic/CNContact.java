package godaddy.registry.jtoolkit2.se.cnnic;

import java.io.Serializable;

/**
 * The Class CNContact.
 */
public class CNContact implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The type. */
    private String type;

    /** The contact type id. */
    private String contactTypeId;

    /** The contact type name. */
    private String contactTypeName;

    /** The mobile. */
    private String mobile;

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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contactTypeId == null) ? 0 : contactTypeId.hashCode());
        result = prime * result + ((contactTypeName == null) ? 0 : contactTypeName.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CNContact other = (CNContact) obj;
        if (contactTypeId == null) {
            if (other.contactTypeId != null) {
                return false;
            }
        } else if (!contactTypeId.equals(other.contactTypeId)) {
            return false;
        }
        if (contactTypeName == null) {
            if (other.contactTypeName != null) {
                return false;
            }
        } else if (!contactTypeName.equals(other.contactTypeName)) {
            return false;
        }
        if (mobile == null) {
            if (other.mobile != null) {
                return false;
            }
        } else if (!mobile.equals(other.mobile)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CNContactDTO [type=" + type + ", contactTypeId="
            + contactTypeId + ", contactTypeName=" + contactTypeName + ", mobile=" + mobile + "]";
    }
}
