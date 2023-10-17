package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.GraphicListStruct;

public final class AfterRepaintGraphicListInfoState implements CallingState {
    private final GraphicListStruct instance;

    public AfterRepaintGraphicListInfoState(GraphicListStruct _g) {
        this.instance = _g;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new AfterRepaintGraphicListInfoPageEl(instance);
    }
}
