package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatNodeContainer;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.FetchedObjs;
import code.util.CustList;
import code.util.LongTreeMap;

public final class NatFetchedObjs extends FetchedObjs {
    private final CustList<LongTreeMap<NatNodeContainer>> stack;
    public NatFetchedObjs(CustList<Struct> _obj, CustList<LongTreeMap<NatNodeContainer>> _stack, Argument _arg) {
        super(_obj, _arg);
        this.stack = _stack;
    }

    public CustList<LongTreeMap<NatNodeContainer>> getStack() {
        return stack;
    }
}
