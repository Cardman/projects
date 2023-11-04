package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.TreeStruct;
import code.expressionlanguage.structs.Struct;

public final class SelectTreeNodeState implements CallingState {
    private final TreeStruct instance;
    private final Struct node;
    public SelectTreeNodeState(TreeStruct _i, Struct _n) {
        instance = _i;
        node = _n;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new SelectTreeNodePageEl(instance,node);
    }
}
