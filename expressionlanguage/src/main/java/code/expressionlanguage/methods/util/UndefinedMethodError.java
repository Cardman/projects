package code.expressionlanguage.methods.util;

import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class UndefinedMethodError extends FoundErrorInterpret {

    private static final String CLASS_NAME = "undefined method";

    private StringList className;

    private MethodId id;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,CLASS_NAME,SEP_KEY_VAL,className.display(),".",id.getSignature(),SEP_INFO);
    }

    public StringList getClassName() {
        return className;
    }

    public void setClassName(StringList _className) {
        className = _className;
    }

    public MethodId getId() {
        return id;
    }

    public void setId(MethodId _id) {
        id = _id;
    }
}
