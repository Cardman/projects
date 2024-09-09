package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;

public final class AfterChangingTableSelectPageEl extends AbstractBasicReflectPageEl {
    private final TableStruct instance;
    private ArrayStruct selections;
    private AbsPrepareCustomEvents callEvts;
    private final Struct anchor;
    private final Struct lead;

    public AfterChangingTableSelectPageEl(TableStruct _g, Struct _a, Struct _l) {
        super(true);
        instance = _g;
        selections = new ArrayStruct(0,"");
        anchor = _a;
        lead = _l;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            selections = instance.getListSelect(_context);
            callEvts = new TabPrepareCustomEvents(anchor,lead);
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
