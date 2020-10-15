package code.expressionlanguage.analyze;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodId;

public final class MethodHeaderInfo {
    private final MethodId id;
    private final int nameNumber;
    private final String importedReturnType;
    private final AccessEnum access;

    public MethodHeaderInfo(MethodId _id, int _nameNumber, String _importedReturnType, AccessEnum _access) {
        this.id = _id;
        this.nameNumber = _nameNumber;
        this.importedReturnType = _importedReturnType;
        this.access = _access;
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
