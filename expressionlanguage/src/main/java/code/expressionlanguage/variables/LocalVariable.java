package code.expressionlanguage.variables;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class LocalVariable {

    private static final String SEP_INFO = "\n";

    private Struct element = NullStruct.NULL_VALUE;

    private String className;

    private boolean finalVariable;

    public String getInfos() {
        if (className == null) {
            return SEP_INFO;
        }
        return new StringBuilder(className).append(SEP_INFO).toString();
    }

    public void setElement(Integer _element) {
        element = new IntStruct(_element);
    }

    public void setElement(Long _element) {
        element = new LongStruct(_element);
    }

    public void setElement(String _element) {
        element = new StringStruct(_element);
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

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean _finalVariable) {
        finalVariable = _finalVariable;
    }
}
