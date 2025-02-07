package aiki.beans.abilities;

import aiki.beans.*;

public final class TranslatedKeyPair {
    private final TranslatedKey first;
    private final TranslatedKey second;

    public TranslatedKeyPair(TranslatedKey _m, TranslatedKey _t) {
        this.first = _m;
        this.second = _t;
    }

    public TranslatedKey getFirst() {
        return first;
    }

    public TranslatedKey getSecond() {
        return second;
    }
}
