package code.mock;

import aiki.facade.SexListInt;
import aiki.game.player.enums.Sex;
import code.util.CustList;

public final class MockLSexList implements SexListInt {
    @Override
    public CustList<Sex> all() {
        CustList<Sex> mock_ = new CustList<Sex>();
        mock_.add(Sex.NO);
        return mock_;
    }
}
