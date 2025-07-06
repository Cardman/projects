package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import code.gui.*;

public final class DefBeanChgTargetCoords extends BeanChgTargetCoords {

    private final GeneComponentModelElt<TargetCoords> targetPosition;

    public DefBeanChgTargetCoords(GeneComponentModelElt<TargetCoords> _t) {
        targetPosition = _t;
    }

    @Override
    public TargetCoords valueTc() {
        return targetPosition.tryRet();
    }

    @Override
    public void valueTc(TargetCoords _v) {
        targetPosition.setupValue(_v);
    }

}
