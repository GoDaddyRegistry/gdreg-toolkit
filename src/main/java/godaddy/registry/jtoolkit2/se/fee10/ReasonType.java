package godaddy.registry.jtoolkit2.se.fee10;

public class ReasonType {

    private final String lang;
    private final String reason;

    public ReasonType(String lang, String reason) {
        this.lang = lang;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getLang() {
        return lang;
    }
}
