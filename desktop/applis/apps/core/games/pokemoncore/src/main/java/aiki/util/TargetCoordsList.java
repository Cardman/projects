package aiki.util;

import aiki.game.fight.TargetCoords;
import code.util.AbEqList;
import code.util.CollCapacity;

public final class TargetCoordsList extends AbEqList<TargetCoords> {
    public TargetCoordsList() {
    }

    public TargetCoordsList(CollCapacity _cap) {
        super(_cap);
    }
    public static TargetCoordsList newList(TargetCoords... _args) {
        TargetCoordsList tp_ = new TargetCoordsList(new CollCapacity(_args.length));
        for (TargetCoords t: _args) {
            tp_.add(t);
        }
        return tp_;
    }
    @Override
    public boolean match(TargetCoords _one, TargetCoords _two) {
        return _one.eq(_two);
    }
}
