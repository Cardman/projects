package aiki.util;

import code.util.*;

public abstract class Coordss<T> extends AbsBasicMap<Coords,T> {
    protected Coordss() {
    }
    protected Coordss(CollCapacity _cap) {
        super(_cap);
    }
    protected Coordss(AbsBasicMap<Coords,T> _cap) {
        super(new CollCapacity(_cap.size()));
        addAllEntries(_cap);
    }
    @Override
    protected boolean matchKeys(Coords _one, Coords _two) {
        return _one.eq(_two);
    }
}
