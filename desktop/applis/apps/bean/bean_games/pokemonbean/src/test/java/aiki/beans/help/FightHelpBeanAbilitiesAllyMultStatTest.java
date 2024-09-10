package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesAllyMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesAllyMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void abilityAllyMultNormal1() {
        assertFalse(callFightHelpBeanAbilityAllyMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityAllyMultNormal2() {
        assertTrue(callFightHelpBeanAbilityAllyMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void abilityAllyMultNormalAny1() {
        assertFalse(callFightHelpBeanAbilityAllyMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityAllyMultNormalAny2() {
        assertTrue(callFightHelpBeanAbilityAllyMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void abilityAllyMultSpeed1() {
        assertFalse(callFightHelpBeanAbilityAllyMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void abilityAllyMultSpeed2() {
        assertTrue(callFightHelpBeanAbilityAllyMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityAllyMultSpeedAny1() {
        assertFalse(callFightHelpBeanAbilityAllyMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void abilityAllyMultSpeedAny2() {
        assertTrue(callFightHelpBeanAbilityAllyMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityAllyMultEvasiness1() {
        assertFalse(callFightHelpBeanAbilityAllyMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityAllyMultEvasiness2() {
        assertTrue(callFightHelpBeanAbilityAllyMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void abilityAllyMultEvasinessAny1() {
        assertFalse(callFightHelpBeanAbilityAllyMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityAllyMultEvasinessAny2() {
        assertTrue(callFightHelpBeanAbilityAllyMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void abilityAllyMultAccuracy1() {
        assertFalse(callFightHelpBeanAbilityAllyMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void abilityAllyMultAccuracy2() {
        assertTrue(callFightHelpBeanAbilityAllyMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void abilityAllyMultAccuracyAny1() {
        assertFalse(callFightHelpBeanAbilityAllyMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void abilityAllyMultAccuracyAny2() {
        assertTrue(callFightHelpBeanAbilityAllyMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanAbilitiesAllyMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesAllyMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
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
        return toStr(callFightHelpBeanClickAbilitiesAllyMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickAbilitiesAllyMultStat(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getMultStatAlly().addEntry(_st, Rate.one());
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
