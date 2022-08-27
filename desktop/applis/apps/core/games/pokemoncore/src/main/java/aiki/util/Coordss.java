package aiki.util;

import code.util.CollCapacity;

public abstract class Coordss<T> extends CommonMap<Coords,T> {
    protected Coordss() {
    }
    protected Coordss(CollCapacity _cap) {
        super(_cap);
    }
    protected Coordss(CommonMap<Coords,T> _cap) {
        super(_cap);
    }
    @Override
    protected boolean eq(Coords _one, Coords _two) {
        return _one.eq(_two);
    }
}
