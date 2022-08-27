package aiki.util;

import aiki.map.Condition;

public final class CoordssCondition extends Coordss<Condition> {
    public CoordssCondition() {
    }
    public CoordssCondition(CommonMap<Coords,Condition> _cap) {
        super(_cap);
    }
    @Override
    protected Condition def() {
        return new Condition();
    }
}
