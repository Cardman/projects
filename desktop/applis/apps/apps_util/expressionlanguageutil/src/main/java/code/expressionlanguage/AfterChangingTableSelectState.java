package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.structs.IntStruct;

public final class AfterChangingTableSelectState implements CallingState {
    private final TableStruct instance;
    private final int anchor;
    private final int lead;

    public AfterChangingTableSelectState(TableStruct _g, int _a, int _l) {
        this.instance = _g;
        anchor = _a;
        lead = _l;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new AfterChangingTableSelectPageEl(instance,new IntStruct(anchor),new IntStruct(lead));
    }
}
