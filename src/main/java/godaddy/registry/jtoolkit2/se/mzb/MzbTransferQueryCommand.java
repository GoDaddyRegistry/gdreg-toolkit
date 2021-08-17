package godaddy.registry.jtoolkit2.se.mzb;

import godaddy.registry.jtoolkit2.se.TransferOp;

/**
 * Use this to query the transfer state of a MZB object.  Instances of this
 * class generate RFC5730 and RFC5731 compliant MZB transfer EPP command
 * service elements via the toXML method with the transfer operation set to
 * "query".
 *
 * @see MzbTransferResponse
 */
public class MzbTransferQueryCommand extends MzbTransferCommand {
    private static final long serialVersionUID = 4925536271075701036L;

    /**
     * Create a MZB transfer command for the identified MZB, specifying
     * the designated password and the 'query' transfer operation.
     *
     * @param roid The roid of the MZB to query the transfer state of.
     *
     * @param pw The identified MZB's password.
     */
    public MzbTransferQueryCommand(String roid, String pw) {
        super(TransferOp.QUERY, roid, pw);
    }

}

