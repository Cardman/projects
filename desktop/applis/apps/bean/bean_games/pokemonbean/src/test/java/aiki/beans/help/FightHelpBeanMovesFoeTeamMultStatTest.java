package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectTeam;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesFoeTeamMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesFoeTeamMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }    @Test
    public void moveFoeTeamMultNormal1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultNormal2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void moveFoeTeamMultNormal3() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.DEFENSE)),0));
    }
    @Test
    public void moveFoeTeamMultNormal4() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void moveFoeTeamMultNormal5() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void moveFoeTeamMultNormal6() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultNormal7() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultNormalAny1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveFoeTeamMultNormalAny2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void moveFoeTeamMultSpeed1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultSpeed2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultSpeed3() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultSpeed4() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultSpeed5() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultSpeed6() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveFoeTeamMultSpeedAny1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveFoeTeamMultSpeedAny2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveFoeTeamMultEvasiness1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultEvasiness2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveFoeTeamMultEvasiness3() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveFoeTeamMultEvasiness4() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveFoeTeamMultEvasiness5() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultEvasiness6() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultEvasinessAny1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveFoeTeamMultEvasinessAny2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void moveFoeTeamMultAccuracy1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracy2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracy3() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracy4() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracy5() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracy6() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveFoeTeamMultAccuracyAny1() {
        assertFalse(callFightHelpBeanMoveFoeTeamMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveFoeTeamMultAccuracyAny2() {
        assertTrue(callFightHelpBeanMoveFoeTeamMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesFoeTeamMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesFoeTeamMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        return toStr(callFightHelpBeanClickMovesFoeTeamMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickMovesFoeTeamMultStat(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getMultStatisticFoe().addEntry(_st, Rate.one());
        t_.getEffects().add(Instances.newEffectStatistic());
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        s_.getEffects().add(Instances.newEffectTeam());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
