package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.se.TransferOp;

/**
 * Use this to reject the transfer of a MZB object currently pending
 * transfer.  The MZB object must be sponsored by the client attempting to
 * reject the transfer.  Instances of this class generate RFC5730 and RFC5731
 * compliant MZB transfer EPP command service elements via the toXML method
 * with the transfer operation set to "reject".
 *
 * @see MzbTransferResponse
 */
public class MzbTransferRejectCommand extends MzbTransferCommand {
    private static final long serialVersionUID = -4558124546837429882L;

    /**
     * Create a MZB transfer command for the identified MZB, specifying
     * the designated password and the 'reject' transfer operation.
     *
     * @param name The roid of the MZB to reject transfer of.
     *
     * @param pw The identified MZB's password.
     */
    public MzbTransferRejectCommand(String name, String pw) {
        super(TransferOp.REJECT, name, pw);
    }

}

