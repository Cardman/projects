package code.expressionlanguage.analyze;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodId;

public final class MethodHeaderInfo {
    private final MethodId id;
    private final String importedReturnType;
    private final AccessEnum access;

    public MethodHeaderInfo(MethodId id, String importedReturnType, AccessEnum access) {
        this.id = id;
        this.importedReturnType = importedReturnType;
        this.access = access;
    }

    public MethodId getId() {
        return id;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }

    public AccessEnum getAccess() {
        return access;
    }
}
