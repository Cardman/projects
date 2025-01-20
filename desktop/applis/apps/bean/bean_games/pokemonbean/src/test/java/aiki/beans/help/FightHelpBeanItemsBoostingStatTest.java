package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.ItemForBattle;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.StatisticPokemon;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanItemsBoostingStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.itemsBoostingStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitItTest() {
        StringList ls_ = FightHelpBean.itemsBoostingStatInit(dbSpec(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitBerryTest() {
        StringList ls_ = FightHelpBean.itemsBoostingStatInit(dbBerry(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void itemBoostNormal1() {
        assertFalse(callFightHelpBeanItemBoostNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostNormal2() {
        assertTrue(callFightHelpBeanItemBoostNormal(bean(dbSpec(Statistic.ATTACK)),0));
    }
    @Test
    public void itemBoostNormal3() {
        assertTrue(callFightHelpBeanItemBoostNormal(bean(dbBerry(Statistic.DEFENSE)),0));
    }
    @Test
    public void itemBoostNormal4() {
        assertTrue(callFightHelpBeanItemBoostNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void itemBoostNormal5() {
        assertTrue(callFightHelpBeanItemBoostNormal(bean(dbBerry(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void itemBoostNormal6() {
        assertFalse(callFightHelpBeanItemBoostNormal(bean(dbBerry(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostNormal7() {
        assertFalse(callFightHelpBeanItemBoostNormal(bean(dbSpec(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostNormalAny1() {
        assertFalse(callFightHelpBeanItemBoostNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void itemBoostNormalAny2() {
        assertTrue(callFightHelpBeanItemBoostNormalAny(bean(dbSpec(Statistic.ATTACK))));
    }
    @Test
    public void itemBoostSpeed1() {
        assertFalse(callFightHelpBeanItemBoostSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostSpeed2() {
        assertTrue(callFightHelpBeanItemBoostSpeed(bean(dbSpec(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostSpeed3() {
        assertTrue(callFightHelpBeanItemBoostSpeed(bean(dbBerry(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostSpeed4() {
        assertTrue(callFightHelpBeanItemBoostSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostSpeed5() {
        assertFalse(callFightHelpBeanItemBoostSpeed(bean(dbBerry(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostSpeed6() {
        assertFalse(callFightHelpBeanItemBoostSpeed(bean(dbSpec(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostSpeedAny1() {
        assertFalse(callFightHelpBeanItemBoostSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void itemBoostSpeedAny2() {
        assertTrue(callFightHelpBeanItemBoostSpeedAny(bean(dbSpec(Statistic.SPEED))));
    }
    @Test
    public void itemBoostCh1() {
        assertFalse(callFightHelpBeanItemBoostCh(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostCh2() {
        assertTrue(callFightHelpBeanItemBoostCh(bean(dbSpec(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostCh3() {
        assertTrue(callFightHelpBeanItemBoostCh(bean(dbBerry(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostCh4() {
        assertTrue(callFightHelpBeanItemBoostCh(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemBoostCh5() {
        assertFalse(callFightHelpBeanItemBoostCh(bean(dbBerry(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostCh6() {
        assertFalse(callFightHelpBeanItemBoostCh(bean(dbSpec(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostChAny1() {
        assertFalse(callFightHelpBeanItemBoostChAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemBoostChAny2() {
        assertTrue(callFightHelpBeanItemBoostChAny(bean(dbSpec(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void itemBoostEvasiness1() {
        assertFalse(callFightHelpBeanItemBoostEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostEvasiness2() {
        assertTrue(callFightHelpBeanItemBoostEvasiness(bean(dbSpec(Statistic.EVASINESS)),0));
    }
    @Test
    public void itemBoostEvasiness3() {
        assertTrue(callFightHelpBeanItemBoostEvasiness(bean(dbBerry(Statistic.EVASINESS)),0));
    }
    @Test
    public void itemBoostEvasiness4() {
        assertTrue(callFightHelpBeanItemBoostEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void itemBoostEvasiness5() {
        assertFalse(callFightHelpBeanItemBoostEvasiness(bean(dbBerry(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostEvasiness6() {
        assertFalse(callFightHelpBeanItemBoostEvasiness(bean(dbSpec(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostEvasinessAny1() {
        assertFalse(callFightHelpBeanItemBoostEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemBoostEvasinessAny2() {
        assertTrue(callFightHelpBeanItemBoostEvasinessAny(bean(dbSpec(Statistic.EVASINESS))));
    }
    @Test
    public void itemBoostAccuracy1() {
        assertFalse(callFightHelpBeanItemBoostAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostAccuracy2() {
        assertTrue(callFightHelpBeanItemBoostAccuracy(bean(dbSpec(Statistic.ACCURACY)),0));
    }
    @Test
    public void itemBoostAccuracy3() {
        assertTrue(callFightHelpBeanItemBoostAccuracy(bean(dbBerry(Statistic.ACCURACY)),0));
    }
    @Test
    public void itemBoostAccuracy4() {
        assertTrue(callFightHelpBeanItemBoostAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void itemBoostAccuracy5() {
        assertFalse(callFightHelpBeanItemBoostAccuracy(bean(dbBerry(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostAccuracy6() {
        assertFalse(callFightHelpBeanItemBoostAccuracy(bean(dbSpec(Statistic.SPEED)),0));
    }
    @Test
    public void itemBoostAccuracyAny1() {
        assertFalse(callFightHelpBeanItemBoostAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemBoostAccuracyAny2() {
        assertTrue(callFightHelpBeanItemBoostAccuracyAny(bean(dbSpec(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanItemsBoostingStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrItemsBoostingStat(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        return toStr(callFightHelpBeanClickItemsBoostingStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickItemsBoostingStat(b_,0);
        return getValItemId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        ItemForBattle t_ = Instances.newItemForBattle();
        t_.getMultStatRank().addEntry(_st,1L);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newItemForBattle());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbSpec(Statistic _st) {
        FacadeGame f_ = facade();
        ItemForBattle t_ = Instances.newItemForBattle();
        t_.getMultStatPokemonRank().addEntry(new StatisticPokemon(_st,NULL_REF),1L);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newItemForBattle());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbBerry(Statistic _st) {
        FacadeGame f_ = facade();
        Berry t_ = Instances.newBerry();
        t_.getMultStat().addEntry(_st,new BoostHpRate(1, Rate.one()));
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newBerry());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
