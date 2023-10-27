package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.*;
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
        ExecTemplates.prepare(_context, _stack, _inst, type(_context), fct(_context), args(),_context.getStandards().getCoreNames().getAliasObject());
        return true;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    protected abstract boolean excluded();

    protected abstract ExecRootBlock type(ContextEl _ctx);

    protected abstract ExecNamedFunctionBlock fct(ContextEl _ctx);

    protected abstract CustList<Argument> args();
}
