package godaddy.registry.jtoolkit2.se.fee10;

/**
 * <p>Extension for the EPP Domain Create response, representing the Fee
 * extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Create
 * command compliant with RFC5730 and RFC5731. The "currency" and "registrationFee"
 * values supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Create Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainCreateCommand
 * @see godaddy.registry.jtoolkit2.se.DomainCreateResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCreateFeeResponseExtension extends FeeTransformResultType {

    private static final long serialVersionUID = 7399517797201556019L;

    public DomainCreateFeeResponseExtension() {
        super(CREATE);
    }

}
