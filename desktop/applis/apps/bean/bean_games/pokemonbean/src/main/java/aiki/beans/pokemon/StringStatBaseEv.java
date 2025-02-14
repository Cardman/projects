package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.fight.util.*;

public final class StringStatBaseEv {
    private final TranslatedKey name;
    private final StatBaseEv stat;

    public StringStatBaseEv(TranslatedKey _n, StatBaseEv _s) {
        this.name = _n;
        this.stat = _s;
    }

    public TranslatedKey getName() {
        return name;
    }

    public StatBaseEv getStat() {
        return stat;
    }
}
