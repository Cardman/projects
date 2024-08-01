package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanVarRatesTest extends InitDbFightHelp {
    @Test
    public void init() {
        NatStringTreeMap<String> b_ = FightHelpBean.varRatesInit(db().getData(),EN);
        assertEq(1,b_.size());
        assertEq(DataBase.DEF_TEMPS_TOUR,b_.getKey(0));
        assertEq(TIME,b_.getValue(0));
    }
    @Test
    public void ini() {
        assertSizeEq(1,callFightHelpBeanVarRatesGet(bean(db())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getRates().addEntry(DifficultyWinPointsFight.FACILE,VAR_PREFIX+ DataBase.DEF_TEMPS_TOUR);
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(DataBase.DEF_TEMPS_TOUR, TAB+ DataBase.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
}
