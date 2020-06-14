package code.expressionlanguage.analyze;

import code.expressionlanguage.opers.util.ClassMethodId;

public final class ImportedMethod {
    private String returnType;
    private ClassMethodId id;

    public ImportedMethod(String returnType, ClassMethodId id) {
        this.returnType = returnType;
        this.id = id;
    }

    public String getReturnType() {
        return returnType;
    }

    public ClassMethodId getId() {
        return id;
    }
}
