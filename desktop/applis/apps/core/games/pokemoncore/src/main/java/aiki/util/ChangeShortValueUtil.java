package aiki.util;

import aiki.db.*;

public final class ChangeShortValueUtil<K> implements ChangeShortFieldMatch {
    private final CommonMap<K,Short> collection;
    public ChangeShortValueUtil(CommonMap<K,Short> _map) {
        collection = _map;
    }

    @Override
    public boolean match(short _v) {
        for (CommonParam<K,Short> e: collection.entryList()) {
            if (e.getValue() == _v) {
                return true;
            }
        }
        return false;
    }

    public void replace(short _o, short _n) {
        for (CommonParam<K,Short> e: collection.entryList()) {
            if (e.getValue() == _o) {
                e.setValue(_n);
            }
        }
    }
}
