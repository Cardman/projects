package aiki.game.fight.util;

import aiki.game.fight.ActivityOfMove;
import code.util.StringList;

public final class ListActivityOfMove {
    private final StringList list;
    private final ActivityOfMove combo;

    public ListActivityOfMove(StringList list, ActivityOfMove combo) {
        this.list = list;
        this.combo = combo;
    }

    public StringList getList() {
        return list;
    }

    public ActivityOfMove getCombo() {
        return combo;
    }
}
