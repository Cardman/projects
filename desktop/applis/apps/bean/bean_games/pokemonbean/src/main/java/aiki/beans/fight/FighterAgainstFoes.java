package aiki.beans.fight;

import code.util.*;

public final class FighterAgainstFoes {
    private final String name;
    private final IntMap<String> foes;

    public FighterAgainstFoes(String _n, IntMap<String> _f) {
        this.name = _n;
        this.foes = _f;
    }

    public String getName() {
        return name;
    }

    public IntMap<String> getFoes() {
        return foes;
    }
}
