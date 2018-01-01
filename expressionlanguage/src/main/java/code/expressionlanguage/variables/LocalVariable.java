package code.expressionlanguage.variables;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;

public final class LocalVariable {

    private static final String SEP_INFO = "\n";

    private Struct element = NullStruct.NULL_VALUE;

    private String className;

    public String getInfos() {
        if (className == null) {
            return SEP_INFO;
        }
        return new StringBuilder(className).append(SEP_INFO).toString();
    }

    public Object getElement() {
        return element.getInstance();
    }

    public void setElement(Object _element) {
        element = StdStruct.wrapStd(_element);
    }

    public Struct getStruct() {
        return element;
    }

    public void setStruct(Struct _element) {
        element = _element;
        if (element == null) {
            element = NullStruct.NULL_VALUE;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }
}
