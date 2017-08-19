package code.formathtml.util;
import code.expressionlanguage.opers.util.Struct;

public final class VariableInformation {

    private Struct element;

    private Class<?> classRef;

    public String getClassName() {
        return classRef.getName();
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

    public Class<?> getClassRef() {
        return classRef;
    }

    public void setClassRef(Class<?> _classRef) {
        classRef = _classRef;
    }

}
