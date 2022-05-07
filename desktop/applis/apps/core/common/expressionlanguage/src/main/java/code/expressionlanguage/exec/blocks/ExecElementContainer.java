package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecElementContent;

public final class ExecElementContainer extends ExecMemberContainer {

    private final ExecElementContent elementContent;

    public ExecElementContainer(ExecElementContent _elementContent, int _trOffset) {
        super(_elementContent.getFieldNameOffest()+_trOffset,_trOffset);
        this.elementContent = _elementContent;
    }

    public ExecElementContent getElementContent() {
        return elementContent;
    }
}
