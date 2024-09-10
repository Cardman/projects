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

public final class FightHelpBeanMovesTeamMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesTeamMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void moveTeamMultNormal1() {
        assertFalse(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormal2() {
        assertTrue(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void moveTeamMultNormal3() {
        assertTrue(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.DEFENSE)),0));
    }
    @Test
    public void moveTeamMultNormal4() {
        assertTrue(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void moveTeamMultNormal5() {
        assertTrue(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void moveTeamMultNormal6() {
        assertFalse(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormal7() {
        assertFalse(callFightHelpBeanMoveTeamMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormalAny1() {
        assertFalse(callFightHelpBeanMoveTeamMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveTeamMultNormalAny2() {
        assertTrue(callFightHelpBeanMoveTeamMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void moveTeamMultSpeed1() {
        assertFalse(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeed2() {
        assertTrue(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed3() {
        assertTrue(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed4() {
        assertTrue(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed5() {
        assertFalse(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeed6() {
        assertFalse(callFightHelpBeanMoveTeamMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeedAny1() {
        assertFalse(callFightHelpBeanMoveTeamMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveTeamMultSpeedAny2() {
        assertTrue(callFightHelpBeanMoveTeamMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultEvasiness1() {
        assertFalse(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasiness2() {
        assertTrue(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness3() {
        assertTrue(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness4() {
        assertTrue(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness5() {
        assertFalse(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasiness6() {
        assertFalse(callFightHelpBeanMoveTeamMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasinessAny1() {
        assertFalse(callFightHelpBeanMoveTeamMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultEvasinessAny2() {
        assertTrue(callFightHelpBeanMoveTeamMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void moveTeamMultAccuracy1() {
        assertFalse(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracy2() {
        assertTrue(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy3() {
        assertTrue(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy4() {
        assertTrue(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy5() {
        assertFalse(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracy6() {
        assertFalse(callFightHelpBeanMoveTeamMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracyAny1() {
        assertFalse(callFightHelpBeanMoveTeamMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultAccuracyAny2() {
        assertTrue(callFightHelpBeanMoveTeamMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesTeamMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesTeamMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
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
        return toStr(callFightHelpBeanClickMovesTeamMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickMovesTeamMultStat(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getMultStatistic().addEntry(_st, Rate.one());
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
