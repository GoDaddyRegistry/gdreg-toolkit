package godaddy.registry.jtoolkit2.se.block11;

public enum OnExpiryAction {
    EXPIRE("expire"),
    CUSTOM("custom");

    private final String code;

    OnExpiryAction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
