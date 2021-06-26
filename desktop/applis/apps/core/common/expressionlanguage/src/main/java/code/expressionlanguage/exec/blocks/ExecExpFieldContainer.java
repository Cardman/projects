package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecFieldContent;

public final class ExecExpFieldContainer extends ExecMemberContainer {

    private final ExecFieldContent fieldContent;

    public ExecExpFieldContainer(ExecFieldContent _elementContent) {
        super(_elementContent.getValueOffset(),0);
        this.fieldContent = _elementContent;
    }

    public ExecFieldContent getFieldContent() {
        return fieldContent;
    }
}
