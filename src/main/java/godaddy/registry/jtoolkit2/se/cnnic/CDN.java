package godaddy.registry.jtoolkit2.se.cnnic;

import java.io.Serializable;
import java.util.Vector;

/**
 * The Class CDN.
 */
public class CDN implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Original Puny code Domain. */
    private String ocdnPunycode;

    /** Simplified Chinese Domain. */
    private String scdn;

    /** Simplified Puny code Domain. */
    private String scdnPunycode;

    /** Traditional Chinese Domain. */
    private String tcdn;

    /** Traditional Puny code Domain. */
    private String tcdnPunycode;

    /** The vcdn. */
    private Vector<String> vcdn;

    /** The VCDN punycode. */
    private Vector<String> vcdnPunycode;

    /**
     * Gets the ocdn punycode.
     *
     * @return the ocdn punycode
     */
    public String getOcdnPunycode() {
        return ocdnPunycode;
    }

    /**
     * Sets the ocdn punycode.
     *
     * @param ocdnPunycode the new ocdn punycode
     */
    public void setOcdnPunycode(final String ocdnPunycode) {
        this.ocdnPunycode = ocdnPunycode;
    }

    /**
     * Gets the scdn.
     *
     * @return the scdn
     */
    public String getScdn() {
        return scdn;
    }

    /**
     * Sets the scdn.
     *
     * @param scdn the new scdn
     */
    public void setScdn(final String scdn) {
        this.scdn = scdn;
    }

    /**
     * Gets the scdn punycode.
     *
     * @return the scdn punycode
     */
    public String getScdnPunycode() {
        return scdnPunycode;
    }

    /**
     * Sets the scdn punycode.
     *
     * @param scdnPunycode the new scdn punycode
     */
    public void setScdnPunycode(final String scdnPunycode) {
        this.scdnPunycode = scdnPunycode;
    }

    /**
     * Gets the tcdn.
     *
     * @return the tcdn
     */
    public String getTcdn() {
        return tcdn;
    }

    /**
     * Sets the tcdn.
     *
     * @param tcdn the new tcdn
     */
    public void setTcdn(final String tcdn) {
        this.tcdn = tcdn;
    }

    /**
     * Gets the tcdn punycode.
     *
     * @return the tcdn punycode
     */
    public String getTcdnPunycode() {
        return tcdnPunycode;
    }

    /**
     * Sets the tcdn punycode.
     *
     * @param tcdnPunycode the new tcdn punycode
     */
    public void setTcdnPunycode(final String tcdnPunycode) {
        this.tcdnPunycode = tcdnPunycode;
    }

    /**
     * Gets the vcdn.
     *
     * @return the vcdn
     */
    public Vector<String> getVcdn() {
        return vcdn;
    }

    /**
     * Sets the vcdn.
     *
     * @param vcdn the new vcdn
     */
    public void setVcdn(final Vector<String> vcdn) {
        this.vcdn = vcdn;
    }

    /**
     * Gets the vcdn punycode.
     *
     * @return the vcdn punycode
     */
    public Vector<String> getVcdnPunycode() {
        return vcdnPunycode;
    }

    /**
     * Sets the vcdn punycode.
     *
     * @param vcdnPunycode the new vcdn punycode
     */
    public void setVcdnPunycode(final Vector<String> vcdnPunycode) {
        this.vcdnPunycode = vcdnPunycode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ocdnPunycode == null) ? 0 : ocdnPunycode.hashCode());
        result = prime * result + ((scdn == null) ? 0 : scdn.hashCode());
        result = prime * result + ((scdnPunycode == null) ? 0 : scdnPunycode.hashCode());
        result = prime * result + ((tcdn == null) ? 0 : tcdn.hashCode());
        result = prime * result + ((tcdnPunycode == null) ? 0 : tcdnPunycode.hashCode());
        result = prime * result + ((vcdn == null) ? 0 : vcdn.hashCode());
        result = prime * result + ((vcdnPunycode == null) ? 0 : vcdnPunycode.hashCode());
        return result;
    }

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
        CDN other = (CDN) obj;
        if (ocdnPunycode == null) {
            if (other.ocdnPunycode != null) {
                return false;
            }
        } else if (!ocdnPunycode.equals(other.ocdnPunycode)) {
            return false;
        }
        if (scdn == null) {
            if (other.scdn != null) {
                return false;
            }
        } else if (!scdn.equals(other.scdn)) {
            return false;
        }
        if (scdnPunycode == null) {
            if (other.scdnPunycode != null) {
                return false;
            }
        } else if (!scdnPunycode.equals(other.scdnPunycode)) {
            return false;
        }
        if (tcdn == null) {
            if (other.tcdn != null) {
                return false;
            }
        } else if (!tcdn.equals(other.tcdn)) {
            return false;
        }
        if (tcdnPunycode == null) {
            if (other.tcdnPunycode != null) {
                return false;
            }
        } else if (!tcdnPunycode.equals(other.tcdnPunycode)) {
            return false;
        }
        if (vcdn == null) {
            if (other.vcdn != null) {
                return false;
            }
        } else if (!vcdn.equals(other.vcdn)) {
            return false;
        }
        if (vcdnPunycode == null) {
            if (other.vcdnPunycode != null) {
                return false;
            }
        } else if (!vcdnPunycode.equals(other.vcdnPunycode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CDN [ocdnPunycode=" + ocdnPunycode + ", scdn=" + scdn + ", scdnPunycode=" + scdnPunycode + ", tcdn="
            + tcdn + ", tcdnPunycode=" + tcdnPunycode + ", vcdn=" + vcdn + ", vcdnPunycode=" + vcdnPunycode + "]";
    }

}
