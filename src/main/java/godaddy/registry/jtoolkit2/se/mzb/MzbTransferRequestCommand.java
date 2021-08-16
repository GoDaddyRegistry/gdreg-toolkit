package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.se.TransferOp;

/**
 * Use this to request the transfer of a MZB object from another client.
 * The MZB object MUST NOT be sponsored by the client attempting to request
 * the transfer.  Instances of this class generate RFC5730 and RFC5731
 * compliant MZB transfer EPP command service elements via the toXML method
 * with the transfer operation set to "request".
 *
 * @see MzbTransferResponse
 */
public class MzbTransferRequestCommand extends MzbTransferCommand {
    private static final long serialVersionUID = 4786406676232060462L;

    /**
     * Create a domain transfer command for the identified domain, specifying
     * the designated password and the 'request' transfer operation.
     *
     * @param roid The roid of the MZB to request transfer of.
     *
     * @param pw The identified domain's password.
     */
    public MzbTransferRequestCommand(String roid, String pw) {
        super(TransferOp.REQUEST, roid, pw);
    }

}

