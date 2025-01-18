package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.instances.Instances;
import aiki.util.LawNumber;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.AbsBasicTreeMap;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanLawRatesTest extends InitDbFightHelp {
    @Test
    public void init() {
        StringMap<AbsBasicTreeMap<Rate, Rate>> b_ = FightHelpBean.lawRatesInit(db().getData());
        assertEq(1,b_.size());
        assertEq(DifficultyModelLaw.UNIFORME.getModelName(),b_.getKey(0));
        assertEq(2,b_.getValue(0).size());
        assertEq(Rate.newRate("1"),b_.getValue(0).getKey(0));
        assertEq(Rate.newRate("3/4"),b_.getValue(0).getValue(0));
        assertEq(Rate.newRate("3"),b_.getValue(0).getKey(1));
        assertEq(Rate.newRate("1/4"),b_.getValue(0).getValue(1));
    }
    @Test
    public void count() {
        assertSizeEq(1,callFightHelpBeanLawsRatesGet(bean(db())));
    }
    @Test
    public void getTrLawRate() {
        assertEq("U",callFightHelpBeanGetTrLawRate(bean(db()),0));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.newRate("1"), LgInt.newLgInt("3"));
        law_.addQuickEvent(Rate.newRate("3"),LgInt.newLgInt("1"));
        f_.getData().getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(law_,1));
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedDiffModelLaw().addEntry(EN,new IdMap<DifficultyModelLaw,String>());
        f_.getData().getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.UNIFORME,"U");
        return f_;
    }
}
