package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.util.*;
import code.gui.*;

public final class DefBeanChgMoveTarget extends BeanChgMoveTarget {

    private final GeneComponentModelElt<MoveTarget> name;
    public DefBeanChgMoveTarget(GeneComponentModelElt<MoveTarget> _n) {
        name = _n;
    }

    @Override
    public MoveTarget valueMt() {
        return name.tryRet();
    }

    @Override
    public void valueMt(MoveTarget _v) {
        name.setupValue(_v);
    }

}
