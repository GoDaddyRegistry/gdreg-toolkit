package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.se.TransferOp;

/**
 * Use this to approve the transfer of a MZB object currently pending
 * transfer.  The MZB object must be sponsored by the client attempting to
 * approve the transfer.  Instances of this class generate RFC5730 and RFC5731
 * compliant MZB transfer EPP command service elements via the toXML method
 * with the transfer operation set to "approve".
 *
 * @see MzbTransferResponse
 */
public class MzbTransferApproveCommand extends MzbTransferCommand {
    private static final long serialVersionUID = 5057086047385703151L;

    /**
     * Create a MZB transfer command for the identified MZB, specifying
     * the designated password and the 'approve' transfer operation.
     *
     * @param roid The roid of the MZB to approve transfer of.
     *
     * @param pw The identified MZB's password.
     */
    public MzbTransferApproveCommand(String roid, String pw) {
        super(TransferOp.APPROVE, roid, pw);
    }

}

