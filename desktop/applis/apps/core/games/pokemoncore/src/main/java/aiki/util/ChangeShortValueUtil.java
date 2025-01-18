package aiki.util;

import aiki.db.*;
import code.util.*;

public final class ChangeShortValueUtil<K> implements ChangeShortFieldMatch {
    private final AbsBasicMap<K,Integer> collection;
    public ChangeShortValueUtil(AbsBasicMap<K,Integer> _map) {
        collection = _map;
    }

    @Override
    public boolean match(int _v) {
        for (EntryCust<K,Integer> e: collection.entryList()) {
            if (e.getValue() == _v) {
                return true;
            }
        }
        return false;
    }

    public void replace(int _o, int _n) {
        for (EntryCust<K,Integer> e: collection.entryList()) {
            if (e.getValue() == _o) {
                e.setValue(_n);
            }
        }
    }
}
