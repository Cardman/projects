package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatNodeContainer;
import code.bean.nat.*;
import code.util.CustList;
import code.util.LongTreeMap;

public final class NatFetchedObjs {
    private final NaSt obj;
    private final CustList<LongTreeMap<NatNodeContainer>> stack;
    public NatFetchedObjs(NaSt _obj, CustList<LongTreeMap<NatNodeContainer>> _stack) {
        obj = _obj;
        this.stack = _stack;
    }

    public NaSt getObj() {
        return obj;
    }

    public CustList<LongTreeMap<NatNodeContainer>> getStack() {
        return stack;
    }
}
