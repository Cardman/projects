package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.fwd.blocks.ExecFieldContent;

public final class ExecExpFieldContainer extends ExecMemberContainer {

    private final ExecFieldContent fieldContent;

    public ExecExpFieldContainer(ExecFieldContent _elementContent) {
        super(_elementContent.getValueOffset(),0);
        this.fieldContent = _elementContent;
    }

    @Override
    protected void firstEnter(StackCall _stack, AbstractInitPageEl _last) {
        _last.globalOffset(getOffset());
    }

    public ExecFieldContent getFieldContent() {
        return fieldContent;
    }
}
