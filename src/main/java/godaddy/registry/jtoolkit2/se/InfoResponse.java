package godaddy.registry.jtoolkit2.se;

import java.util.GregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import godaddy.registry.jtoolkit2.EPPDateFormatter;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

/**
 * Representation of the EPP info response, as defined in RFC5730.  Subclasses
 * of this must specify the object to which the command is mapped.  Instances
 * of this class provide an interface to access info response data for the
 * object identified in a {@link godaddy.registry.jtoolkit2.se.InfoCommand}.
 * This relies on the instance first being initialised by a suitable EPP info
 * response using the method fromXML.  For flexibility, this implementation
 * extracts the data from the response using XPath queries, the expressions for
 * which are defined statically.
 *
 * @see godaddy.registry.jtoolkit2.se.InfoCommand
 */
public abstract class InfoResponse extends DataResponse {

    protected static final String INF_DATA_EXPR = RESPONSE_EXPR + "/e:resData/OBJ:infData";
    protected static final String ROID_EXPR = INF_DATA_EXPR + "/OBJ:roid/text()";
    protected static final String CR_ID_EXPR = INF_DATA_EXPR + "/OBJ:crID/text()";
    protected static final String UP_ID_EXPR = INF_DATA_EXPR + "/OBJ:upID/text()";
    protected static final String CL_ID_EXPR = INF_DATA_EXPR + "/OBJ:clID/text()";
    protected static final String CR_DATE_EXPR = INF_DATA_EXPR + "/OBJ:crDate/text()";
    protected static final String UP_DATE_EXPR = INF_DATA_EXPR + "/OBJ:upDate/text()";
    protected static final String TR_DATE_EXPR = INF_DATA_EXPR + "/OBJ:trDate/text()";
    protected static final String STATUS_COUNT_EXPR = "count(" + INF_DATA_EXPR + "/OBJ:status)";
    protected static final String STATUS_EXPR = INF_DATA_EXPR + "/OBJ:status[IDX]";

    private static final long serialVersionUID = 1473168990371776574L;
    private static final String STATUS_S_EXPR = "/@s";
    private static final String STATUS_REASON_EXPR = "/text()";
    private static final String STATUS_LANG_EXPR = "/@lang";

    private String roid;
    private String clID;
    private String crID;
    private String upID;
    private GregorianCalendar crDate;
    private GregorianCalendar upDate;
    private GregorianCalendar trDate;
    private Status[] statuses;

    public InfoResponse(ObjectType objectType) {
        super(StandardCommandType.INFO, objectType);
    }

    public String getROID() {
        return roid;
    }

    public GregorianCalendar getCreateDate() {
        return crDate;
    }

    public GregorianCalendar getUpdateDate() {
        return upDate;
    }

    public GregorianCalendar getTransferDate() {
        return trDate;
    }

    public String getCreateClient() {
        return crID;
    }

    public String getUpdateClient() {
        return upID;
    }

    public String getSponsorClient() {
        return clID;
    }

    public Status[] getStatuses() {
        return statuses;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            roid = xmlDoc.getNodeValue(roidExpr());

            crID = xmlDoc.getNodeValue(crIDExpr());
            upID = xmlDoc.getNodeValue(upIDExpr());
            clID = xmlDoc.getNodeValue(clIDExpr());

            String crDateStr = xmlDoc.getNodeValue(crDateExpr());
            String upDateStr = xmlDoc.getNodeValue(upDateExpr());
            String trDateStr = xmlDoc.getNodeValue(trDateExpr());
            crDate = EPPDateFormatter.fromXSDateTime(crDateStr);
            upDate = EPPDateFormatter.fromXSDateTime(upDateStr);
            trDate = EPPDateFormatter.fromXSDateTime(trDateStr);

            String statusCountExpr = statusCountExpr();
            if (statusCountExpr != null) {
                int statusCount = xmlDoc.getNodeCount(statusCountExpr);
                statuses = new Status[statusCount];
                for (int i = 0; i < statuses.length; i++) {
                    String qry = ReceiveSE.replaceIndex(statusExpr(), i + 1);
                    String reason = xmlDoc.getNodeValue(qry + STATUS_REASON_EXPR);
                    String s = xmlDoc.getNodeValue(qry + STATUS_S_EXPR);
                    String lang = xmlDoc.getNodeValue(qry + STATUS_LANG_EXPR);
                    statuses[i] = new Status(s, reason, lang);
                }
            }
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }

    protected abstract String roidExpr();
    protected abstract String crIDExpr();
    protected abstract String upIDExpr();
    protected abstract String clIDExpr();
    protected abstract String crDateExpr();
    protected abstract String upDateExpr();
    protected abstract String trDateExpr();
    protected abstract String statusExpr();
    protected abstract String statusCountExpr();

    @Override
    public String toString() {
        String retval = super.toString();
        retval += "(roid = " + roid
            + ")(statuses = " + arrayToString(statuses, ",") + ")";
        return retval;
    }
}

