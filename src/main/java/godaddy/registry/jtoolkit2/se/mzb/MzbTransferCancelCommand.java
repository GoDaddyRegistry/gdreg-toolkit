package godaddy.registry.jtoolkit2.se.mzb;

import godaddy.registry.jtoolkit2.se.TransferOp;

/**
 * Use this to cancel the transfer of a MZB object currently pending
 * transfer.  The transfer must have been initiated via a transfer request by
 * the client attempting to cancel the transfer.  Instances of this class
 * generate RFC5730 and RFC5731 compliant MZB transfer EPP command service
 * elements via the toXML method with the transfer operation set to "cancel".
 *
 * @see MzbTransferResponse
 */
public class MzbTransferCancelCommand extends MzbTransferCommand {
    private static final long serialVersionUID = 4459609896155243761L;

    /**
     * Create a MZB transfer command for the identified MZB, specifying
     * the designated password and the 'cancel' transfer operation.
     *
     * @param roid The roid of the MZB to cancel transfer of.
     *
     * @param pw The identified MZB's password.
     */
    public MzbTransferCancelCommand(String roid, String pw) {
        super(TransferOp.CANCEL, roid, pw);
    }

}

