package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.TreeStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;

public final class SelectTreeNodePageEl extends AbstractBasicReflectPageEl {
    private final TreeStruct instance;
    private final Struct node;
    private AbsPrepareCustomEvents callEvts;
    private ArrayStruct selections;
    public SelectTreeNodePageEl(TreeStruct _i, Struct _n) {
        super(true);
        instance = _i;
        node = _n;
        selections = new ArrayStruct(0,"");
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            selections = instance.getActions(_context);
            callEvts = new EvtTreePrepareCustomEvents(node);
        }
        int l_ = selections.getLength();
        for (int i = NumberUtil.max(0,callEvts.getEventIndex()-1); i < l_; i++) {
            Struct inst_ = selections.get(i);
            if (callEvts.call(_context,_stack,inst_,i)) {
                return false;
            }
        }
        setReturnedArgument(NullStruct.NULL_VALUE);
        return true;
    }
}
