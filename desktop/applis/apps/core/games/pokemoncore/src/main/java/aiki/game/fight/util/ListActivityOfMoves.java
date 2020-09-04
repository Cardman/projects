package aiki.game.fight.util;

import aiki.game.fight.ActivityOfMove;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;

public final class ListActivityOfMoves extends CustList<ListActivityOfMove> {

    public ListActivityOfMoves() {
    }

    public ListActivityOfMoves(CollCapacity _capacity) {
        super(_capacity);
    }

    public ActivityOfMove getVal(StringList _key) {
        for (ListActivityOfMove l:this) {
            if (_key.eq(l.getList())) {
                return l.getCombo();
            }
        }
        return new ActivityOfMove();
    }

    public CustList<StringList> getKeys() {
        CustList<StringList> k_ = new CustList<StringList>();
        for (ListActivityOfMove l:this) {
            k_.add(l.getList());
        }
        return k_;
    }
}
