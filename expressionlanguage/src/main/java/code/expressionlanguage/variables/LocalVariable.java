package code.expressionlanguage.variables;
import code.expressionlanguage.opers.util.Struct;

public final class LocalVariable {

    private static final String SEP_INFO = "\n";

    private static final String AS = " as ";

    private Struct element = new Struct();

    private String className = Object.class.getName();

//    private String expression;

    @Override
    public String toString() {
        try {
            return element+AS+className+SEP_INFO;
        } catch (Error _0) {
            return className+SEP_INFO;
        } catch (RuntimeException _0) {
            return className+SEP_INFO;
        }
    }

    public Object getElement() {
        return element.getInstance();
    }

    public void setElement(Object _element) {
        if (_element == null) {
            element = new Struct();
        } else {
            element = new Struct(_element);
        }
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

//    public String getExpression() {
//        return expression;
//    }
//
//    public void setExpression(String _expression) {
//        expression = _expression;
//    }
}
