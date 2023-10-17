package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.structs.Struct;

public final class EditRowGraphicListState implements CallingState {
    private final GraphicListStruct instance;
    private final int index;
    private final Struct info;

    public EditRowGraphicListState(GraphicListStruct _g, int _i, Struct _f) {
        this.instance = _g;
        this.index = _i;
        this.info = _f;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return new EditRowGraphicListPageEl(instance,index,info);
    }
}
