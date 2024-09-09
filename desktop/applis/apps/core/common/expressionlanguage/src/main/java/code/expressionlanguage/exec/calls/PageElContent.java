package code.expressionlanguage.exec.calls;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class PageElContent {

    private Struct globalStruct = NullStruct.NULL_VALUE;

    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private final StringMap<AbstractWrapper> refParams = new StringMap<AbstractWrapper>();
    private Cache cache;

    public Struct getGlobalStruct() {
        return globalStruct;
    }

    public void setGlobalArgumentStruct(Struct _obj) {
        globalStruct = ArgumentListCall.getNull(_obj);
    }

    public void setGlobalArgument(Struct _globalArgument) {
        globalStruct = ArgumentListCall.getNull(_globalArgument);
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
