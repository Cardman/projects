package aiki.game.fight.util;

import aiki.game.fight.ActivityOfMove;
import code.util.StringList;

public final class ListActivityOfMove {
    private final StringList list;
    private final ActivityOfMove combo;

    public ListActivityOfMove(StringList _list, ActivityOfMove _combo) {
        this.list = _list;
        this.combo = _combo;
    }

    public StringList getList() {
        return list;
    }

    public ActivityOfMove getCombo() {
        return combo;
    }
}
