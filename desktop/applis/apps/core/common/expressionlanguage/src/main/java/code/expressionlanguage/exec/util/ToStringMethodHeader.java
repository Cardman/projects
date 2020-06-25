package code.expressionlanguage.exec.util;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public final class ToStringMethodHeader {
    private final String name;
    private final String importedReturnType;
    private final boolean finalMethod;
    private final boolean abstractMethod;

    public ToStringMethodHeader(String _name, String _importedReturnType, boolean _finalMethod, boolean _abstractMethod) {
        name = _name;
        importedReturnType = _importedReturnType;
        finalMethod = _finalMethod;
        abstractMethod = _abstractMethod;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public MethodId getId() {
        return new MethodId(MethodAccessKind.INSTANCE,name, new StringList());
    }
}
