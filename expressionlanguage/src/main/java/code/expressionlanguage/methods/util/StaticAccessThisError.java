package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class StaticAccessThisError extends FoundErrorInterpret {

    private static final String CLASS_NAME = "current instance in static context";

    private String className;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,CLASS_NAME,className);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }
}
