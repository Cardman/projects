package aiki.db;

import code.util.*;

public final class ChangeStringFieldMatchShortsContains implements ChangeShortFieldMatch {
    private final Ints changeStringField;

    public ChangeStringFieldMatchShortsContains(Ints _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(int _v) {
        return changeStringField.contains(_v);
    }
}
