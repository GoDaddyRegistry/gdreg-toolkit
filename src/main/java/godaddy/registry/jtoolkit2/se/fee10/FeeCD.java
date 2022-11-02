package godaddy.registry.jtoolkit2.se.fee10;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FeeCD implements Serializable {

    private static final long serialVersionUID = 3545842467667231569L;
    private final Map<String, FeeCheckCommand> commands = new HashMap<>();
    private String feeClass;
    private String objId;
    private ReasonType reason;
    private boolean available;

    public Map<String, FeeCheckCommand> getCommands() {
        return commands;
    }

    public void addCommand(FeeCheckCommand command) {
        this.commands.put(command.getName(), command);
    }

    public String getFeeClass() {
        return feeClass;
    }

    public void setFeeClass(String feeClass) {
        this.feeClass = feeClass;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public void setReason(ReasonType reason) {
        this.reason = reason;
    }

    public ReasonType getReason() {
        return reason;
    }

    public void setAvailable(boolean avail) {
        this.available = avail;
    }

    public boolean isAvailable() {
        return available;
    }

}
