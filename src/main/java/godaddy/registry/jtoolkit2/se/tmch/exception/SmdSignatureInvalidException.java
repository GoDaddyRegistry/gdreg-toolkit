package godaddy.registry.jtoolkit2.se.tmch.exception;

import godaddy.registry.jtoolkit2.ErrorPkg;

public class SmdSignatureInvalidException extends RuntimeException {
    @Override
    public String getMessage() {
        return ErrorPkg.getMessage("tmch.smd.signature.invalid");
    }
}
