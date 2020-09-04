package aiki.fight.util;

import aiki.fight.moves.effects.EffectCombo;
import code.util.StringList;

public final class ListEffectCombo {
    private final StringList list;
    private final EffectCombo combo;

    public ListEffectCombo(StringList list, EffectCombo combo) {
        this.list = list;
        this.combo = combo;
    }

    public StringList getList() {
        return list;
    }

    public EffectCombo getCombo() {
        return combo;
    }
}
