package aiki.db;

import code.util.*;

public final class ChangeStringFieldMatchMapContains<V> implements ChangeStringFieldMatch {
    private final AbsMap<String,V> changeStringField;

    public ChangeStringFieldMatchMapContains(AbsMap<String,V> _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(String _v) {
        return changeStringField.contains(_v);
    }
}
