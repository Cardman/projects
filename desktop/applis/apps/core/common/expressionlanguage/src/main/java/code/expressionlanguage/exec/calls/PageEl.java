package code.expressionlanguage.exec.calls;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.StringMap;

public abstract class PageEl {

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

    public void setGlobalArgument(Argument _globalArgument) {
        content.setGlobalArgument(_globalArgument);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return content.getRefParams();
    }
    public StringMap<LocalVariable> getValueVars() {
        return content.getValueVars();
    }

    public void putValueVar(String _key, LocalVariable _var) {
        content.putValueVar(_key, _var);
    }
    public StringMap<LoopVariable> getVars() {
        return content.getVars();
    }

    public void removeLocalVar(String _key) {
        content.removeLocalVar(_key);
    }

    public Cache getCache() {
        return content.getCache();
    }

    public void setCache(Cache _cache) {
        this.content.setCache(_cache);
    }
}
