package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.GraphicComboStruct;

public final class AddRowComboState implements CallingState {
    private final GraphicComboStruct instance;
    private final String info;
    private final boolean select;

    public AddRowComboState(GraphicComboStruct _g, String _f, boolean _selecting) {
        this.instance = _g;
        this.info = _f;
        this.select = _selecting;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new AddRowComboPageEl(instance,info, select);
    }
}
