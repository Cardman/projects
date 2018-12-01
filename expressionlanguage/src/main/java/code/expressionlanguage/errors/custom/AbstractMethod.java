package code.expressionlanguage.errors.custom;

import code.util.StringList;


public final class AbstractMethod extends FoundErrorInterpret {

    private static final String SEP_CLASS_METHOD = ".";

    private static final String CLASS_NAME = "abstract method";

    private String className;

    private String sgn;

    @Override
    public String display() {
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,className,SEP_CLASS_METHOD,sgn,SEP_INFO);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public String getSgn() {
        return sgn;
    }

    public void setSgn(String _sgn) {
        sgn = _sgn;
    }
}
