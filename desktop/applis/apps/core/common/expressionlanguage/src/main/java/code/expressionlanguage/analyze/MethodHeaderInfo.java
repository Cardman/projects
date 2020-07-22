package code.expressionlanguage.analyze;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodId;

public final class MethodHeaderInfo {
    private final MethodId id;
    private final int nameNumber;
    private final String importedReturnType;
    private final AccessEnum access;

    public MethodHeaderInfo(MethodId id, int nameNumber, String importedReturnType, AccessEnum access) {
        this.id = id;
        this.nameNumber = nameNumber;
        this.importedReturnType = importedReturnType;
        this.access = access;
    }

    public MethodId getId() {
        return id;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }

    public AccessEnum getAccess() {
        return access;
    }
}
