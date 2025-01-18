package aiki.game.fight;

import code.util.core.BoolVal;

public final class ChosableTargetName {
    private BoolVal chosable = BoolVal.FALSE;
    private String name = "";
    private int key;

    public String getName() {
        return name;
    }

    public void setName(String _n) {
        this.name = _n;
    }

    public BoolVal getChosable() {
        return chosable;
    }

    public void setChosable(BoolVal _c) {
        this.chosable = _c;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int _k) {
        this.key = _k;
    }
}
