package code.expressionlanguage.exec.variables;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class LocalVariable {

    private Struct element = NullStruct.NULL_VALUE;

    private String className;

    public static LocalVariable newLocalVariable(Struct _struct, ContextEl _cont) {
        Struct struct_ = ArgumentListCall.getNull(_struct);
        return newLocalVariable(struct_, struct_.getClassName(_cont));
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
        element = ArgumentListCall.getNull(_element);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = StringUtil.nullToEmpty(_className);
    }

}
