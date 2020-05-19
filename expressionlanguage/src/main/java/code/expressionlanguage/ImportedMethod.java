package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassMethodId;

public final class ImportedMethod {
    private int imported;
    private String returnType;
    private ClassMethodId id;

    public ImportedMethod(int imported, String returnType, ClassMethodId id) {
        this.imported = imported;
        this.returnType = returnType;
        this.id = id;
    }

    public int getImported() {
        return imported;
    }

    public String getReturnType() {
        return returnType;
    }

    public ClassMethodId getId() {
        return id;
    }
}
