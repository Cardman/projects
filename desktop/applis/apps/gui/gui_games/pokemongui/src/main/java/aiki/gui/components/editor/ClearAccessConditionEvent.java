package aiki.gui.components.editor;

import code.gui.events.*;

public final class ClearAccessConditionEvent implements AbsActionListener {
    private final ContentComponentModelAccessCondition contentComponentModelDualFight;
    public ClearAccessConditionEvent(ContentComponentModelAccessCondition _c){
        contentComponentModelDualFight = _c;
    }

    @Override
    public void action() {
        contentComponentModelDualFight.clearAccess();
    }
}
