package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticCategory;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitTestCat() {
        StringList ls_ = FightHelpBean.abilitiesMultStatInit(dbCat(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void abilityMultNormal1() {
        assertFalse(callFightHelpBeanAbilityMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultNormal2() {
        assertTrue(callFightHelpBeanAbilityMultNormal(bean(dbCat(Statistic.ATTACK)),0));
    }
    @Test
    public void abilityMultNormal3() {
        assertTrue(callFightHelpBeanAbilityMultNormal(bean(dbCat(Statistic.DEFENSE)),0));
    }
    @Test
    public void abilityMultNormal4() {
        assertTrue(callFightHelpBeanAbilityMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void abilityMultNormal5() {
        assertTrue(callFightHelpBeanAbilityMultNormal(bean(dbCat(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void abilityMultNormal6() {
        assertFalse(callFightHelpBeanAbilityMultNormal(bean(dbCat(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultNormal7() {
        assertFalse(callFightHelpBeanAbilityMultNormal(bean(dbCat(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultNormalAny1() {
        assertFalse(callFightHelpBeanAbilityMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityMultNormalAny2() {
        assertTrue(callFightHelpBeanAbilityMultNormalAny(bean(dbCat(Statistic.ATTACK))));
    }
    @Test
    public void abilityMultSpeed1() {
        assertFalse(callFightHelpBeanAbilityMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultSpeed2() {
        assertTrue(callFightHelpBeanAbilityMultSpeed(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultSpeed3() {
        assertTrue(callFightHelpBeanAbilityMultSpeed(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultSpeed4() {
        assertTrue(callFightHelpBeanAbilityMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultSpeed5() {
        assertFalse(callFightHelpBeanAbilityMultSpeed(bean(dbCat(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultSpeed6() {
        assertFalse(callFightHelpBeanAbilityMultSpeed(bean(dbCat(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityMultSpeedAny1() {
        assertFalse(callFightHelpBeanAbilityMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityMultSpeedAny2() {
        assertTrue(callFightHelpBeanAbilityMultSpeedAny(bean(dbCat(Statistic.SPEED))));
    }
    @Test
    public void abilityMultEvasiness1() {
        assertFalse(callFightHelpBeanAbilityMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultEvasiness2() {
        assertTrue(callFightHelpBeanAbilityMultEvasiness(bean(dbCat(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityMultEvasiness3() {
        assertTrue(callFightHelpBeanAbilityMultEvasiness(bean(dbCat(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityMultEvasiness4() {
        assertTrue(callFightHelpBeanAbilityMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityMultEvasiness5() {
        assertFalse(callFightHelpBeanAbilityMultEvasiness(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultEvasiness6() {
        assertFalse(callFightHelpBeanAbilityMultEvasiness(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultEvasinessAny1() {
        assertFalse(callFightHelpBeanAbilityMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityMultEvasinessAny2() {
        assertTrue(callFightHelpBeanAbilityMultEvasinessAny(bean(dbCat(Statistic.EVASINESS))));
    }
    @Test
    public void abilityMultAccuracy1() {
        assertFalse(callFightHelpBeanAbilityMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultAccuracy2() {
        assertTrue(callFightHelpBeanAbilityMultAccuracy(bean(dbCat(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityMultAccuracy3() {
        assertTrue(callFightHelpBeanAbilityMultAccuracy(bean(dbCat(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityMultAccuracy4() {
        assertTrue(callFightHelpBeanAbilityMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityMultAccuracy5() {
        assertFalse(callFightHelpBeanAbilityMultAccuracy(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultAccuracy6() {
        assertFalse(callFightHelpBeanAbilityMultAccuracy(bean(dbCat(Statistic.SPEED)),0));
    }
    @Test
    public void abilityMultAccuracyAny1() {
        assertFalse(callFightHelpBeanAbilityMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityMultAccuracyAny2() {
        assertTrue(callFightHelpBeanAbilityMultAccuracyAny(bean(dbCat(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanAbilitiesMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        return toStr(callFightHelpBeanClickAbilitiesMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickAbilitiesMultStat(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getMultStat().addEntry(_st, NULL_REF);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbCat(Statistic _st) {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getMultStatIfCat().addEntry(new StatisticCategory(_st,NULL_REF), Rate.one());
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
