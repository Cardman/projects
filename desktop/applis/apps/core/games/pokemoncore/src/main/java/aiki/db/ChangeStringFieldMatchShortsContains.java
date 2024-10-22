package aiki.db;

import code.util.*;

public final class ChangeStringFieldMatchShortsContains implements ChangeShortFieldMatch {
    private final Shorts changeStringField;

    public ChangeStringFieldMatchShortsContains(Shorts _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(short _v) {
        return changeStringField.contains(_v);
    }
}
