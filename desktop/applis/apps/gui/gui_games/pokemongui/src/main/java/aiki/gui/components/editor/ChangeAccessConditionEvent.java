package aiki.gui.components.editor;

import aiki.util.*;
import code.gui.*;
import code.gui.events.*;

public final class ChangeAccessConditionEvent implements AbsActionListener {
    private final ContentComponentModelAccessCondition contentComponentModelDualFight;
    private final AbsCustCheckBox check;
    private final Coords coords;
    public ChangeAccessConditionEvent(ContentComponentModelAccessCondition _c, AbsCustCheckBox _b, Coords _o) {
        contentComponentModelDualFight = _c;
        check = _b;
        coords = _o;
    }

    @Override
    public void action() {
        contentComponentModelDualFight.changeSelectCondition(check,coords);
    }
}
