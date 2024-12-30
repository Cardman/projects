package aiki.gui.components.editor;

import code.gui.events.*;

public final class ValidateAccessConditionEvent implements AbsActionListener {
    private final ContentComponentModelAccessCondition contentComponentModelDualFight;
    public ValidateAccessConditionEvent(ContentComponentModelAccessCondition _c){
        contentComponentModelDualFight = _c;
    }

    @Override
    public void action() {
        contentComponentModelDualFight.validateAccess();
    }
}
