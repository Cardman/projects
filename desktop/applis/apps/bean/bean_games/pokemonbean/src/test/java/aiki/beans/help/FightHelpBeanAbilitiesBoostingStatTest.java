package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesBoostingStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesBoostingStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void abilityBoostNormal1() {
        assertFalse(callFightHelpBeanAbilityBoostNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityBoostNormal2() {
        assertTrue(callFightHelpBeanAbilityBoostNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void abilityBoostNormalAny1() {
        assertFalse(callFightHelpBeanAbilityBoostNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityBoostNormalAny2() {
        assertTrue(callFightHelpBeanAbilityBoostNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void abilityBoostSpeed1() {
        assertFalse(callFightHelpBeanAbilityBoostSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityBoostSpeed2() {
        assertTrue(callFightHelpBeanAbilityBoostSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityBoostSpeedAny1() {
        assertFalse(callFightHelpBeanAbilityBoostSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityBoostSpeedAny2() {
        assertTrue(callFightHelpBeanAbilityBoostSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityBoostCh1() {
        assertFalse(callFightHelpBeanAbilityBoostCh(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityBoostCh2() {
        assertTrue(callFightHelpBeanAbilityBoostCh(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityBoostChAny1() {
        assertFalse(callFightHelpBeanAbilityBoostChAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityBoostChAny2() {
        assertTrue(callFightHelpBeanAbilityBoostChAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityBoostEvasiness1() {
        assertFalse(callFightHelpBeanAbilityBoostEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityBoostEvasiness2() {
        assertTrue(callFightHelpBeanAbilityBoostEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityBoostEvasinessAny1() {
        assertFalse(callFightHelpBeanAbilityBoostEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityBoostEvasinessAny2() {
        assertTrue(callFightHelpBeanAbilityBoostEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void abilityBoostAccuracy1() {
        assertFalse(callFightHelpBeanAbilityBoostAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityBoostAccuracy2() {
        assertTrue(callFightHelpBeanAbilityBoostAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityBoostAccuracyAny1() {
        assertFalse(callFightHelpBeanAbilityBoostAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityBoostAccuracyAny2() {
        assertTrue(callFightHelpBeanAbilityBoostAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getBonusStatRank().addEntry(_st,(byte)1);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
