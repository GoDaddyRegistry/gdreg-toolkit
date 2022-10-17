package godaddy.registry.jtoolkit2.se.fee10;

import static godaddy.registry.jtoolkit2.se.ExtendedObjectType.FEEV10;

import java.math.BigDecimal;
import javax.xml.xpath.XPathExpressionException;

import godaddy.registry.jtoolkit2.se.ResponseExtension;
import godaddy.registry.jtoolkit2.xml.XMLDocument;

abstract class FeeTransformResultType extends ResponseExtension {

    private static final String FEE_PREFIX = FEEV10.getName();

    private static final String FEE_XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + "/" + FEE_PREFIX
            + ":RESPONSE_TYPE/" + FEE_PREFIX;
    private static final String CURRENCY_EXPR = FEE_XPATH_PREFIX + ":currency/text()";
    private static final String FEE_EXPR = FEE_XPATH_PREFIX + ":fee/text()";
    private static final long serialVersionUID = 550982830217084994L;

    private boolean initialised = false;
    private final String responseType;

    private String currency;
    private BigDecimal registrationFee;

    FeeTransformResultType(String responseType) {
        this.responseType = responseType;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {
        final String currencyQuery = replaceResponseType(CURRENCY_EXPR, responseType);
        currency = xmlDoc.getNodeValue(currencyQuery);
        final String feeQuery = replaceResponseType(FEE_EXPR, responseType);
        String feeNodeValue = xmlDoc.getNodeValue(feeQuery);
        if (feeNodeValue != null) {
            registrationFee = new BigDecimal(feeNodeValue);
        }

        initialised = registrationFee != null;
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRegistrationFee() {
        return registrationFee;
    }

}
