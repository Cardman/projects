package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class PageElContent {

    private Argument globalArgument = Argument.createVoid();

    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private final StringMap<AbstractWrapper> refParams = new StringMap<AbstractWrapper>();
    private Cache cache;

    public Struct getGlobalStruct() {
        return getGlobalArgument().getStruct();
    }
    public Argument getGlobalArgument() {
        return globalArgument;
    }
    public void setGlobalArgumentStruct(Struct _obj) {
        globalArgument = new Argument(_obj);
    }

    public void setGlobalArgument(Argument _globalArgument) {
        globalArgument = Argument.getNullableValue(_globalArgument);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return refParams;
    }

    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public void removeRefVar(String _key) {
        getRefParams().removeKey(_key);
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache _cache) {
        this.cache = _cache;
    }
}
