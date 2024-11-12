package aiki.util;

import aiki.db.*;
import code.util.*;

public final class ChangeShortValueUtil<K> implements ChangeShortFieldMatch {
    private final AbsBasicMap<K,Short> collection;
    public ChangeShortValueUtil(AbsBasicMap<K,Short> _map) {
        collection = _map;
    }

    @Override
    public boolean match(short _v) {
        for (EntryCust<K,Short> e: collection.entryList()) {
            if (e.getValue() == _v) {
                return true;
            }
        }
        return false;
    }

    public void replace(short _o, short _n) {
        for (EntryCust<K,Short> e: collection.entryList()) {
            if (e.getValue() == _o) {
                e.setValue(_n);
            }
        }
    }
}
