package godaddy.registry.jtoolkit2.se.fee10;

import java.math.BigDecimal;

/**
 * <p>Extension for the EPP Domain Renew response, representing the Fee extension.</p>
 *
 * <p>Use this to acknowledge the price associated with this domain name as part of an EPP Domain Renew
 * command compliant with RFC5730 and RFC5731. The "currency" and "fee" values
 * supplied, should match the fees that are set for the domain name for the requested period.
 * The response expected from a server should be handled by a Domain Renew Response.</p>
 *
 * @see godaddy.registry.jtoolkit2.se.DomainRenewCommand
 * @see godaddy.registry.jtoolkit2.se.DomainRenewResponse
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc8748">Domain Name Fee Extension
 * Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public final class DomainRestoreFeeResponseExtension extends FeeTransformResultType {

    private static final long serialVersionUID = -6007874008986690757L;

    public DomainRestoreFeeResponseExtension() {
        super(RENEW);
    }

    public BigDecimal getFee(String feeDescription) {
        return getFeeType().computeIfAbsent(feeDescription, k -> new FeeType()).getFee();
    }
}
