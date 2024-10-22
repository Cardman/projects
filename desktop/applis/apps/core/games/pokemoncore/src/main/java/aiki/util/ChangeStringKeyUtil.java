package aiki.util;

import aiki.db.ChangeStringFieldMatch;
import code.util.CustList;

public final class ChangeStringKeyUtil {
    private final CustList<ChangeStringFieldMatch> collection = new CustList<ChangeStringFieldMatch>();
    public void add(ChangeStringFieldMatch _map) {
        collection.add(_map);
    }
    public boolean contains(String _o) {
        for (ChangeStringFieldMatch e:collection) {
            if (e.match(_o)) {
                return true;
            }
        }
        return false;
    }

    public void addAllElts(CustList<ChangeStringFieldMatch> _ls) {
        collection.addAllElts(_ls);
    }
}
