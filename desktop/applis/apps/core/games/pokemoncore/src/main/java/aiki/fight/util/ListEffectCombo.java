package aiki.fight.util;

import aiki.fight.moves.effects.EffectCombo;
import code.util.StringList;

public final class ListEffectCombo {
    private final StringList list;
    private final EffectCombo combo;

    public ListEffectCombo(StringList _list, EffectCombo _combo) {
        this.list = _list;
        this.combo = _combo;
    }

    public StringList getList() {
        return list;
    }

    public EffectCombo getCombo() {
        return combo;
    }
}
