package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatNodeContainer;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.FetchedObjs;
import code.util.CustList;
import code.util.LongTreeMap;

public final class NatFetchedObjs extends FetchedObjs {
    private final CustList<Struct> obj;
    private final CustList<LongTreeMap<NatNodeContainer>> stack;
    public NatFetchedObjs(CustList<Struct> _obj, CustList<LongTreeMap<NatNodeContainer>> _stack, Argument _arg) {
        super(_arg);
        obj = _obj;
        this.stack = _stack;
    }

    public CustList<Struct> getObj() {
        return obj;
    }

    public CustList<LongTreeMap<NatNodeContainer>> getStack() {
        return stack;
    }
}
