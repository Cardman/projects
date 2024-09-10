package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.util.StatisticStatus;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesImmuMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesImmuMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void abilityImmuMultNormal1() {
        assertFalse(callFightHelpBeanAbilityImmuMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityImmuMultNormal2() {
        assertTrue(callFightHelpBeanAbilityImmuMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void abilityImmuMultNormalAny1() {
        assertFalse(callFightHelpBeanAbilityImmuMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityImmuMultNormalAny2() {
        assertTrue(callFightHelpBeanAbilityImmuMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void abilityImmuMultSpeed1() {
        assertFalse(callFightHelpBeanAbilityImmuMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityImmuMultSpeed2() {
        assertTrue(callFightHelpBeanAbilityImmuMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityImmuMultSpeedAny1() {
        assertFalse(callFightHelpBeanAbilityImmuMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityImmuMultSpeedAny2() {
        assertTrue(callFightHelpBeanAbilityImmuMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityImmuMultEvasiness1() {
        assertFalse(callFightHelpBeanAbilityImmuMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityImmuMultEvasiness2() {
        assertTrue(callFightHelpBeanAbilityImmuMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityImmuMultEvasinessAny1() {
        assertFalse(callFightHelpBeanAbilityImmuMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityImmuMultEvasinessAny2() {
        assertTrue(callFightHelpBeanAbilityImmuMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void abilityImmuMultAccuracy1() {
        assertFalse(callFightHelpBeanAbilityImmuMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityImmuMultAccuracy2() {
        assertTrue(callFightHelpBeanAbilityImmuMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityImmuMultAccuracyAny1() {
        assertFalse(callFightHelpBeanAbilityImmuMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityImmuMultAccuracyAny2() {
        assertTrue(callFightHelpBeanAbilityImmuMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanAbilitiesImmuMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesImmuMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
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
        return toStr(callFightHelpBeanClickAbilitiesImmuMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickAbilitiesImmuMultStat(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getImmuLowStatIfStatus().add(new StatisticStatus(_st,NULL_REF));
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
