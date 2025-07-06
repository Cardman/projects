package aiki.beans;

import aiki.game.fight.*;

public class BeanChgTargetCoords implements IntBeanChgTargetCoords {

    private TargetCoords value = TargetCoords.def();

    @Override
    public TargetCoords valueTc() {
        return value;
    }

    @Override
    public void valueTc(TargetCoords _v) {
        value = _v;
    }
}
