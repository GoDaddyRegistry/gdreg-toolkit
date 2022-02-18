package godaddy.registry.jtoolkit2.se.cnnic;

import java.util.Vector;

import javax.xml.xpath.XPathExpressionException;

import godaddy.registry.jtoolkit2.se.ExtendedObjectType;
import godaddy.registry.jtoolkit2.se.ReceiveSE;
import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * The Class DomainInfoCDNResponseExtension.
 */
public final class DomainInfoCDNResponseExtension extends ResponseExtension {

    /** The Constant EXTN_PREFIX. */
    private static final String EXTN_PREFIX = ExtendedObjectType.CDN.getName();

    /** The Constant NEULEVEL_DATA_EXPRESSION. */
    private static final String NEULEVEL_DATA_EXPRESSION = EXTENSION_EXPR + "/" + EXTN_PREFIX + ":infData/";

    /** The Constant CHKDATA_COUNT_EXPR. */
    private static final String CHKDATA_COUNT_EXPR = "count(" + NEULEVEL_DATA_EXPRESSION + "*)";

    /** The Constant CHKDATA_OCDN_EXPR. */
    private static final String CHKDATA_OCDN_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":OCDNPunycode";

    /** The Constant CHKDATA_SCDN_EXPR. */
    private static final String CHKDATA_SCDN_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":SCDN";

    /** The Constant CHKDATA_SCDN_PUNYCODE_EXPR. */
    private static final String CHKDATA_SCDN_PUNYCODE_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX
        + ":SCDNPunycode";

    /** The Constant CHKDATA_TCDN_EXPR. */
    private static final String CHKDATA_TCDN_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX + ":TCDN";

    /** The Constant CHKDATA_TCDNPUNYCODE_EXPR. */
    private static final String CHKDATA_TCDNPUNYCODE_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX
        + ":TCDNPunycode";

    /** The Constant CHKDATA_VCDNLIST_EXPR. */
    private static final String CHKDATA_VCDNLIST_EXPR = NEULEVEL_DATA_EXPRESSION + EXTN_PREFIX
        + ":VCDNList";

    /** The Constant CHKDATA_VCDN_EXPR. */
    private static final String CHKDATA_VCDN_EXPR = CHKDATA_VCDNLIST_EXPR + "/" + EXTN_PREFIX
        + ":VCDN[IDX]";

    /** The Constant CHKDATA_VCDN_EXPR_COUNT. */
    private static final String CHKDATA_VCDN_EXPR_COUNT =
        "count(" + CHKDATA_VCDNLIST_EXPR + "/" + EXTN_PREFIX + ":VCDN" + ")";

    /** The Constant CHKDATA_VCDNPUNYCODE_EXPR. */
    private static final String CHKDATA_VCDNPUNYCODE_EXPR = CHKDATA_VCDNLIST_EXPR + "/" + EXTN_PREFIX
        + ":VCDNPunycode[IDX]";

    /** The Constant CHKDATA_VCDNPUNYCODE_EXPRCOUNT. */
    private static final String CHKDATA_VCDNPUNYCODE_EXPR_COUNT =
        "count(" + CHKDATA_VCDNLIST_EXPR + "/" + EXTN_PREFIX + ":VCDNPunycode" + ")";

    /** The initialised. */
    private boolean initialised;

    /** The cdn. */
    private CDN cdn;

    /**
     * Instantiates a new contact info CNNIC response extension.
     */
    public DomainInfoCDNResponseExtension() {
        // intentionally do nothing
    }

    /**
     * Instantiates a new CNNIC cdn extension.
     *
     * @param cdn the cdn
     */
    public DomainInfoCDNResponseExtension(CDN cdn) {
        this.cdn = cdn;
    }

    @Override
    public void fromXML(final XMLDocument xmlDoc) throws XPathExpressionException {

        int elementCount = xmlDoc.getNodeCount(CHKDATA_COUNT_EXPR);
        int vcdnCount = xmlDoc.getNodeCount(CHKDATA_VCDN_EXPR_COUNT);
        int vcdnPunyCodeCount = xmlDoc.getNodeCount(CHKDATA_VCDNPUNYCODE_EXPR_COUNT);

        if (elementCount > 0) {
            cdn = new CDN();
            cdn.setOcdnPunycode(xmlDoc.getNodeValue(CHKDATA_OCDN_EXPR));
            cdn.setScdn(xmlDoc.getNodeValue(CHKDATA_SCDN_EXPR));
            cdn.setScdnPunycode(xmlDoc.getNodeValue(CHKDATA_SCDN_PUNYCODE_EXPR));
            cdn.setTcdn(xmlDoc.getNodeValue(CHKDATA_TCDN_EXPR));
            cdn.setTcdnPunycode(xmlDoc.getNodeValue(CHKDATA_TCDNPUNYCODE_EXPR));
            xmlDoc.getNodeValue(CHKDATA_VCDNLIST_EXPR);

            Vector<String> vcdnList = new Vector<String>();
            for (int i = 0; i < vcdnCount; i++) {
                vcdnList.add(xmlDoc.getNodeValue(ReceiveSE.replaceIndex(CHKDATA_VCDN_EXPR, i + 1)));
            }
            cdn.setVcdn(vcdnList);

            Vector<String> vcdnPunycodeList = new Vector<String>();
            for (int i = 0; i < vcdnPunyCodeCount; i++) {
                vcdnPunycodeList.add(xmlDoc.getNodeValue(ReceiveSE.replaceIndex(CHKDATA_VCDNPUNYCODE_EXPR, i + 1)));
            }
            cdn.setVcdnPunycode(vcdnPunycodeList);

            initialised = true;
        }
    }

    /**
     * Gets the cdn.
     *
     * @return the cdn
     */
    public CDN getCDN() {
        return cdn;
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

}
