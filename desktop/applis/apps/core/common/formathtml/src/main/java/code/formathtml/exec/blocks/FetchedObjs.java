package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;

public final class FetchedObjs {
    private final CustList<Struct> obj;
    private final CustList<Struct> allObj;
    private final CustList<AbstractWrapper> wrap;
    private final StringList objClasses;
    private final CustList<LongTreeMap<NodeContainer>> stack;
    private final Argument arg;
    private final boolean indexer;
    public FetchedObjs(CustList<Struct> _obj, CustList<Struct> _allObj, CustList<AbstractWrapper> _wrap, StringList _objClasses, CustList<LongTreeMap<NodeContainer>> _stack, Argument _arg, boolean _indexer) {
        this.obj = _obj;
        this.allObj = _allObj;
        this.wrap = _wrap;
        this.objClasses = _objClasses;
        this.stack = _stack;
        this.arg = _arg;
        this.indexer = _indexer;
    }

    public Argument getArg() {
        return arg;
    }

    public CustList<AbstractWrapper> getWrap() {
        return wrap;
    }

    public CustList<LongTreeMap<NodeContainer>> getStack() {
        return stack;
    }

    public CustList<Struct> getAllObj() {
        return allObj;
    }

    public CustList<Struct> getObj() {
        return obj;
    }

    public StringList getObjClasses() {
        return objClasses;
    }

    public boolean isIndexer() {
        return indexer;
    }
}
