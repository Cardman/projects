package aiki.db;

import aiki.map.pokemon.*;
import code.util.*;

public final class ChangeStringFieldPkListWild extends ChangeStringFieldPkList {
    private final CustList<WildPk> wild;

    public ChangeStringFieldPkListWild(ChangeStringFieldVisit _v, CustList<WildPk> _ls) {
        super(_v);
        wild = _ls;
    }

    public int length(){
        return wild.size();
    }

    @Override
    public Pokemon elt(int _i) {
        return wild.get(_i);
    }
}
