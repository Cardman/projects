package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.PageElContent;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class SimplePageEl {
    private final PageElContent content = new PageElContent();
    public Struct getGlobalStruct() {
        return content.getGlobalStruct();
    }
    public Argument getGlobalArgument() {
        return content.getGlobalArgument();
    }
    public void setGlobalArgumentStruct(Struct _obj) {
        content.setGlobalArgumentStruct(_obj);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return content.getRefParams();
    }

    public void putValueVar(String _key, LocalVariable _var) {
        content.getRefParams().put(_key, new VariableWrapper(_var));
    }
    public StringMap<LoopVariable> getVars() {
        return content.getVars();
    }

    public void removeRefVar(String _key) {
        content.removeRefVar(_key);
    }

    public void removeLocalVar(String _key) {
        content.removeLocalVar(_key);
    }

    public Cache getCache() {
        return content.getCache();
    }

}
