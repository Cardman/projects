package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;

public final class DefFetchedObjs extends FetchedObjs {
    private final StringList objClasses;
    private final CustList<Struct> allObj;

    private final CustList<AbstractWrapper> wrap;
    private final CustList<LongTreeMap<DefNodeContainer>> stack;
    private final boolean indexer;
    public DefFetchedObjs(CustList<Struct> _obj, CustList<Struct> _allObj, CustList<AbstractWrapper> _wrap, StringList _objClasses, CustList<LongTreeMap<DefNodeContainer>> _stack, Argument _arg, boolean _indexer) {
        super(_obj, _arg);
        allObj = _allObj;
        objClasses = _objClasses;
        wrap = _wrap;
        stack = _stack;
        indexer = _indexer;
    }

    public CustList<Struct> getAllObj() {
        return allObj;
    }
    public StringList getObjClasses() {
        return objClasses;
    }
    public CustList<AbstractWrapper> getWrap() {
        return wrap;
    }

    public CustList<LongTreeMap<DefNodeContainer>> getStack() {
        return stack;
    }

    public boolean isIndexer() {
        return indexer;
    }
}
