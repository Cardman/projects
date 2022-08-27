package aiki.util;

import aiki.fight.pokemon.GenderName;
import code.util.CustList;

public final class PlaceLevelsCustListGenderName extends PlaceLevels<CustList<GenderName>> {
    @Override
    protected CustList<GenderName> def() {
        return new CustList<GenderName>();
    }
}
