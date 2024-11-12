package aiki.util;

import aiki.map.Condition;
import code.util.*;

public final class CoordssCondition extends Coordss<Condition> {
    public CoordssCondition() {
    }
    public CoordssCondition(AbsBasicMap<Coords,Condition> _cap) {
        super(_cap);
    }
    @Override
    protected Condition def() {
        return new Condition();
    }
}
