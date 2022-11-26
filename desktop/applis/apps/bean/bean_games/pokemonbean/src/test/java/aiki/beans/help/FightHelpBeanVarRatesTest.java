package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanVarRatesTest extends InitDbFightHelp {
    @Test
    public void init() {
        NatStringTreeMap<String> b_ = FightHelpBean.varRatesInit(db().getData(),EN);
        assertEq(1,b_.size());
        assertEq(Fight.TEMPS_TOUR,b_.getKey(0));
        assertEq(TIME,b_.getValue(0));
    }
    @Test
    public void ini() {
        assertSizeEq(1,callFightHelpBeanVarRatesGet(bean(db())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getRates().addEntry(DifficultyWinPointsFight.FACILE,DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        return f_;
    }
}
