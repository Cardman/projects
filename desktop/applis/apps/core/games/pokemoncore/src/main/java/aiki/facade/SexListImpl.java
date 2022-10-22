package aiki.facade;

import aiki.game.player.enums.Sex;
import code.util.CustList;

public final class SexListImpl implements SexListInt {
    @Override
    public CustList<Sex> all() {
        return Sex.all();
    }
}
