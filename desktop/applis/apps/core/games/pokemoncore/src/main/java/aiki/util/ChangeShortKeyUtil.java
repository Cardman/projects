package aiki.util;

import aiki.db.*;
import code.util.*;

public final class ChangeShortKeyUtil {
    private final CustList<ChangeShortFieldMatch> collection = new CustList<ChangeShortFieldMatch>();
    public void add(ChangeShortFieldMatch _map) {
        collection.add(_map);
    }
    public boolean contains(short _o) {
        for (ChangeShortFieldMatch e:collection) {
            if (e.match(_o)) {
                return true;
            }
        }
        return false;
    }

    public void addAllElts(CustList<ChangeShortFieldMatch> _ls) {
        collection.addAllElts(_ls);
    }
}
