package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.util.ListEffectCombo;
import aiki.instances.Instances;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanComboMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        CustList<StringList> ls_ = FightHelpBean.comboMultStatInit(db(Statistic.CRITICAL_HIT).getData(),EN);
        assertEq(2,ls_.size());
    }
    @Test
    public void init() {
        assertSizeEq(2,callFightHelpBeanComboMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void effectTeam1() {
        assertEq(0,FightHelpBean.effectTeam(db(Statistic.CRITICAL_HIT).getData(),new StringList(M_STA,M_DAM_VAR),EN).getMultStatisticFoe().size());
    }
    @Test
    public void effectTeam2() {
        assertEq(0,FightHelpBean.effectTeam(db(Statistic.CRITICAL_HIT).getData(),new StringList(M_DAM_VAR,M_STA),EN).getMultStatisticFoe().size());
    }
    @Test
    public void effectTeam3() {
        assertEq(1,FightHelpBean.effectTeam(db(Statistic.CRITICAL_HIT).getData(),new StringList(M_DAM,M_STA),EN).getMultStatisticFoe().size());
    }
    @Test
    public void comboMultNormal1() {
        assertFalse(callFightHelpBeanComboMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void comboMultNormal2() {
        assertTrue(callFightHelpBeanComboMultNormal(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void comboMultNormalAny1() {
        assertFalse(callFightHelpBeanComboMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void comboMultNormalAny2() {
        assertTrue(callFightHelpBeanComboMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void comboMultEvasiness1() {
        assertFalse(callFightHelpBeanComboMultEvasiness(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void comboMultEvasiness2() {
        assertTrue(callFightHelpBeanComboMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void comboMultEvasinessAny1() {
        assertFalse(callFightHelpBeanComboMultEvasinessAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void comboMultEvasinessAny2() {
        assertTrue(callFightHelpBeanComboMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void comboMultSpeed1() {
        assertFalse(callFightHelpBeanComboMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void comboMultSpeed2() {
        assertTrue(callFightHelpBeanComboMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void comboMultSpeedAny1() {
        assertFalse(callFightHelpBeanComboMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void comboMultSpeedAny2() {
        assertTrue(callFightHelpBeanComboMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void comboMultAccuracy1() {
        assertFalse(callFightHelpBeanComboMultAccuracy(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void comboMultAccuracy2() {
        assertTrue(callFightHelpBeanComboMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void comboMultAccuracyAny1() {
        assertFalse(callFightHelpBeanComboMultAccuracyAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void comboMultAccuracyAny2() {
        assertTrue(callFightHelpBeanComboMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR+" - "+M_DAM_VAR_TR,callFightHelpBeanGetTrComboMultStat(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void cl() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML,clickDest());
    }
    private static String clickDest() {
        return toStr(callFightHelpBeanClickComboMultStat(bean(db(Statistic.ATTACK)),0));
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        f_.getData().completeMembers(M_DAM, Instances.newDamagingMoveData());
        f_.getData().completeMembers(M_STA, Instances.newStatusMoveData());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusMoveData());
        f_.getData().setCombos(Instances.newCombos());
        EffectCombo c_ = Instances.newEffectCombo();
        EffectTeam t_ = Instances.newEffectTeam();
        t_.getMultStatisticFoe().addEntry(_st,Rate.one());
        c_.getTeamMove().add(t_);
        EffectCombo c2_ = Instances.newEffectCombo();
        EffectTeam t2_ = Instances.newEffectTeam();
        t2_.getMultStatisticFoe().addEntry(_st,Rate.one());
        c2_.getTeamMove().add(t2_);
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_DAM,M_STA), c_));
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_DAM,M_DAM_VAR),c2_));
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_STA,M_DAM_VAR),Instances.newEffectCombo()));
        f_.getData().completeMembersCombos();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
