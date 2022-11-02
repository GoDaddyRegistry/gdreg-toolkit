package godaddy.registry.jtoolkit2.se.fee10;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import godaddy.registry.jtoolkit2.se.Period;

public class FeeCheckCommand implements Serializable {

    private final String name;
    private final String phase;
    private final String subphase;
    private Period period;

    private final Map<String, FeeType> fees = new HashMap<>();
    private final Map<String, CreditType> credits = new HashMap<>();
    private ReasonType reason;

    public FeeCheckCommand(String name) {
        this(name, null, null);
    }

    public FeeCheckCommand(String name, String phase) {
        this(name, phase, null);
    }

    public FeeCheckCommand(String name, String phase, String subphase) {
        this.name = name;
        this.phase = phase;
        this.subphase = subphase;
    }

    public Map<String, FeeType> getFees() {
        return fees;
    }

    public void addFee(FeeType fee) {
        this.fees.put(fee.getDescription(), fee);
    }

    public Map<String, CreditType> getCredits() {
        return credits;
    }

    public void addCredit(CreditType credit) {
        this.credits.put(credit.getDescription(), credit);
    }

    public String getName() {
        return name;
    }

    public String getPhase() {
        return phase;
    }

    public String getSubphase() {
        return subphase;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setReason(ReasonType reason) {
        this.reason = reason;
    }

    public ReasonType getReason() {
        return reason;
    }

}