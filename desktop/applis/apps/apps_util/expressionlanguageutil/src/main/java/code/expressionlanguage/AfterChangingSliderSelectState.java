package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.SliderStruct;

public final class AfterChangingSliderSelectState implements CallingState {
    private final SliderStruct instance;

    public AfterChangingSliderSelectState(SliderStruct _g) {
        this.instance = _g;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new AfterChangingSliderSelectPageEl(instance);
    }
}
