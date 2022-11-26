package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.instances.Instances;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanRatesTest extends InitDbFightHelp {
    @Test
    public void init() {
        StringMap<String> b_ = FightHelpBean.ratesInit(db().getData(),EN);
        assertEq(1,b_.size());
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),b_.getKey(0));
        assertEq(Fight.TEMPS_TOUR,b_.getValue(0));
    }
    @Test
    public void getTrLawRate() {
        assertEq("U",callFightHelpBeanGetTrDifficulty(bean(db()),0));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getRates().addEntry(DifficultyWinPointsFight.FACILE,DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedDiffWinPts().addEntry(EN,new IdMap<DifficultyWinPointsFight,String>());
        f_.getData().getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.FACILE,"U");
        return f_;
    }
}
