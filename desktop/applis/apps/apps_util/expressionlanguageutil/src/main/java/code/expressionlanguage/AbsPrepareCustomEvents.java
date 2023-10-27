package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class AbsPrepareCustomEvents {
    private int eventIndex;

    public boolean call(ContextEl _context, StackCall _stack, Struct _inst, int _i) {
        if (excluded()) {
            return false;
        }
        if (_i < eventIndex) {
            return false;
        }
        eventIndex = _i + 1;
        ExecTemplates.prepare(_context, _stack, _inst, pair(_context), args());
        return true;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    protected abstract boolean excluded();

    protected abstract ExecTypeFunction pair(ContextEl _ctx);

    protected abstract CustList<Argument> args();
}
