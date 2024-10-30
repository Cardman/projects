package aiki.fight.util;

import aiki.util.CommonMap;
import code.maths.*;
import code.util.*;

public final class TypesDuos extends CommonMap<TypesDuo,Rate> {
    public TypesDuos() {
    }
    public TypesDuos(CollCapacity _cap) {
        super(_cap);
    }

    public static boolean contains(CustList<TypesDuo> _l, TypesDuo _e) {
        for (TypesDuo t: _l) {
            if (_e.eq(t)) {
                return true;
            }
        }
        return false;
    }

    public CustList<String> getTypes() {
        return getTypesFrom(getKeys());
    }

    public static CustList<String> getTypesFrom(CustList<TypesDuo> _keys) {
        CustList<String> l_ = new CustList<String>();
        for (TypesDuo e: _keys) {
            l_.add(e.getDamageType());
            l_.add(e.getPokemonType());
        }
        return l_;
    }

    @Override
    protected Rate def() {
        return Rate.zero();
    }

    @Override
    protected boolean eq(TypesDuo _k, TypesDuo _e) {
        return _k.eq(_e);
    }

}
