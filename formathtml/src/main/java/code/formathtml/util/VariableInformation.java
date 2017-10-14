package code.formathtml.util;
import code.expressionlanguage.opers.util.Struct;

public final class VariableInformation {

    private Struct element;

    private String declaringClassName;

    public String getClassName() {
        return declaringClassName;
    }

    public Struct getStruct() {
        return element;
    }
    public void setStruct(Struct _element) {
        element = _element;
        if (element == null) {
            element = new Struct();
        }
    }

    public void setElement(Object _element) {
        if (_element == null) {
            element = new Struct();
        } else {
            element = new Struct(_element);
        }
    }

    public void setClassName(String _className) {
        declaringClassName = _className;
    }

}
