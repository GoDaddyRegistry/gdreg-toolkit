package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.NodeList;

import godaddy.registry.jtoolkit2.se.Period;
import godaddy.registry.jtoolkit2.se.PeriodUnit;
import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

abstract class FeeTransformResultType extends ResponseExtension {

    private static final String FEE_PREFIX = FEEV10.getName();

    private static final String FEE_XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + "/" + FEE_PREFIX
            + ":RESPONSE_TYPE/" + FEE_PREFIX;
    private static final String CURRENCY_EXPR = FEE_XPATH_PREFIX + ":currency/text()";
    private static final String FEE_EXPR = FEE_XPATH_PREFIX + ":fee";
    private static final String CREDIT_EXPR = FEE_XPATH_PREFIX + ":credit";
    private static final String BALANCE_EXPR = FEE_XPATH_PREFIX + ":balance/text()";
    private static final String CREDIT_LIMIT_EXPR = FEE_XPATH_PREFIX + ":creditLimit/text()";
    private static final String PERIOD_EXPR = FEE_XPATH_PREFIX + ":period/text()";
    private static final String PERIOD_UNIT_EXPR = FEE_XPATH_PREFIX + ":period/@unit";
    private static final long serialVersionUID = 550982830217084994L;

    private boolean initialised = false;
    private final String responseType;

    private String currency;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private Period period;
    private final Map<String, FeeType> fee = new HashMap<>();
    private final Map<String, CreditType> credit = new HashMap<>();

    FeeTransformResultType(String responseType) {
        this.responseType = responseType;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        currency = xmlDoc.getNodeValue(replaceResponseType(CURRENCY_EXPR, responseType));
        initialised = currency != null;
        final String balanceValue = xmlDoc.getNodeValue(replaceResponseType(BALANCE_EXPR, responseType));
        if (balanceValue != null) {
            initialised = true;
            balance = new BigDecimal(balanceValue);
        }
        final String creditLimitValue = xmlDoc.getNodeValue(replaceResponseType(CREDIT_LIMIT_EXPR, responseType));
        if (creditLimitValue != null) {
            initialised = true;
            creditLimit = new BigDecimal(creditLimitValue);
        }
        initialised = parseFeeType(xmlDoc) || initialised;
        initialised = parseCreditType(xmlDoc) || initialised;
        initialised = parsePeriodType(xmlDoc) || initialised;

    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public String getCurrency() {
        return currency;
    }

    public Map<String, FeeType> getFeeType() {
        return fee;
    }

    public Map<String, CreditType> getCreditType() {
        return credit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public Period getPeriod() {
        return period;
    }

    private boolean parseFeeType(XMLDocument xmlDoc) throws XPathExpressionException {
        boolean initial = false;
        NodeList feeNodeList = xmlDoc.getElements(replaceResponseType(FEE_EXPR, responseType));
        if (feeNodeList != null) {
            int nodeLength = feeNodeList.getLength();
            for (int i = 0; i < nodeLength; i++) {
                final FeeType feeType = new FeeType(feeNodeList.item(i));
                if (feeType.getFee() != null) {
                    initial = true;
                }
                fee.put(feeType.getDescription(), feeType);
            }
        }
        return initial;
    }

    private boolean parseCreditType(XMLDocument xmlDoc) throws XPathExpressionException {
        boolean initial = false;
        NodeList feeNodeList = xmlDoc.getElements(replaceResponseType(CREDIT_EXPR, responseType));
        if (feeNodeList != null) {
            int nodeLength = feeNodeList.getLength();
            for (int i = 0; i < nodeLength; i++) {
                final CreditType creditType = new CreditType(feeNodeList.item(i));
                if (creditType.getFee() != null) {
                    initial = true;
                }
                credit.put(creditType.getDescription(), creditType);
            }
        }
        return initial;
    }

    private boolean parsePeriodType(XMLDocument xmlDoc) throws XPathExpressionException {
        final String periodValue = xmlDoc.getNodeValue(replaceResponseType(PERIOD_EXPR, responseType));
        final String unitValue = xmlDoc.getNodeValue(replaceResponseType(PERIOD_UNIT_EXPR, responseType));
        boolean initial = periodValue != null;
        period = initial
                ? new Period(PeriodUnit.value(unitValue), Integer.parseInt(periodValue))
                : null;

        return initial;
    }
}
