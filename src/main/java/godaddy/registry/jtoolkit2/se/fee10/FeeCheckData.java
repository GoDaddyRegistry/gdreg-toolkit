package godaddy.registry.jtoolkit2.se.fee10;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FeeCheckData implements Serializable {

    private String currency;
    private final Map<String, FeeCD> cds = new HashMap<>();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, FeeCD> getCDs() {
        return cds;
    }

    public void addCD(FeeCD cd) {
        this.cds.put(cd.getObjId(), cd);
    }

}
