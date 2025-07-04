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
        int index_ = index(_key);
        if (index_ < 0) {
            return new ActivityOfMove();
        }
        return get(index_).getCombo();
    }
    public int index(StringList _key) {
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            if (_key.eq(get(i).getList())) {
                return i;
            }
        }
        return -1;
    }

    public CustList<StringList> getKeys() {
        CustList<StringList> k_ = new CustList<StringList>();
        for (ListActivityOfMove l:this) {
            k_.add(l.getList());
        }
        return k_;
    }
}
