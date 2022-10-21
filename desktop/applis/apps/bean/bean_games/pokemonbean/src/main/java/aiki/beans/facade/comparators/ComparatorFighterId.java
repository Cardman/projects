package aiki.beans.facade.comparators;

import aiki.beans.facade.fight.FighterNameId;
import aiki.db.DataBase;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorFighterId implements Comparing<FighterNameId> {

    private final StringMap<String> trs;
    public ComparatorFighterId(DataBase _db, String _lg) {
        trs = _db.getTranslatedPokemon().getVal(_lg);
    }

    @Override
    public int compare(FighterNameId _one, FighterNameId _two) {
        int res_ = StringUtil.compareStrings(StringUtil.nullToEmpty(trs.getVal(_one.getName())), StringUtil.nullToEmpty(trs.getVal(_two.getName())));
        if (res_ != 0) {
            return res_;
        }
        return NumberUtil.compareLg(_one.getPosition(), _two.getPosition());
    }
}
