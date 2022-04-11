package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.LongTreeMap;

public final class DefFetchedObjs extends FetchedObjs {
    private final CustList<Struct> allObj;

    private final AbstractWrapper input;
    private final CustList<LongTreeMap<DefNodeContainer>> stack;

    public DefFetchedObjs(AbstractWrapper _input, CustList<Struct> _allObj, CustList<LongTreeMap<DefNodeContainer>> _stack, Argument _arg) {
        super(_arg);
        input = _input;
        allObj = _allObj;
        stack = _stack;
    }

    public CustList<Struct> getAllObj() {
        return allObj;
    }

    public AbstractWrapper getInput() {
        return input;
    }

    public CustList<LongTreeMap<DefNodeContainer>> getStack() {
        return stack;
    }

}
