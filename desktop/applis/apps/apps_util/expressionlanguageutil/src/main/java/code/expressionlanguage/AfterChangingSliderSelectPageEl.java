package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.SliderStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;

public final class AfterChangingSliderSelectPageEl extends AbstractBasicReflectPageEl {
    private final SliderStruct instance;
    private ArrayStruct selections;
    private AbsPrepareCustomEvents callEvts;

    public AfterChangingSliderSelectPageEl(SliderStruct _g) {
        super(true);
        instance = _g;
        selections = new ArrayStruct(0,"");
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            selections = instance.getChange(_context);
            callEvts = new SliderPrepareCustomEvents();
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
