package code.expressionlanguage.variables;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class LocalVariable {

    private Struct element = NullStruct.NULL_VALUE;

    private String className;

    private boolean finalVariable;

    public static LocalVariable newLocalVariable(Struct _struct, ExecutableCode _cont) {
        return newLocalVariable(_struct,_cont.getStandards().getStructClassName(_struct,_cont.getContextEl()));
    }

    public static LocalVariable newLocalVariable(Struct _struct, String _type) {
        LocalVariable loc_ = new LocalVariable();
        loc_.setStruct(_struct);
        loc_.setClassName(_type);
        return loc_;
    }
    public Struct getStruct() {
        return element;
    }

    public void setStruct(Struct _element) {
        element = _element;
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
