package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.fwd.blocks.ExecElementContent;

public final class ExecElementContainer extends ExecMemberContainer {

    private final ExecElementContent elementContent;

    public ExecElementContainer(ExecElementContent _elementContent, int _trOffset) {
        super(_elementContent.getFieldNameOffest()+_trOffset,_trOffset);
        this.elementContent = _elementContent;
    }

    @Override
    protected void firstEnter(StackCall _stack, AbstractInitPageEl _last) {
        if (_stack.getStopper().firstEnter(_last)) {
            _last.globalOffset(elementContent.getFieldNameOffest());
        } else {
            _last.globalOffset(getOffset());
        }
    }

    public ExecElementContent getElementContent() {
        return elementContent;
    }
}
