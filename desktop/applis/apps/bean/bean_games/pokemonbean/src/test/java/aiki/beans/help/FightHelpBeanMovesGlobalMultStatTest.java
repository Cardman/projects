package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.util.StatisticType;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesGlobalMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesGlobalMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void moveGlobalMultNormal1() {
        assertFalse(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultNormal2() {
        assertTrue(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void moveGlobalMultNormal3() {
        assertTrue(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.DEFENSE)),0));
    }
    @Test
    public void moveGlobalMultNormal4() {
        assertTrue(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void moveGlobalMultNormal5() {
        assertTrue(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void moveGlobalMultNormal6() {
        assertFalse(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultNormal7() {
        assertFalse(callFightHelpBeanMoveGlobalMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultNormalAny1() {
        assertFalse(callFightHelpBeanMoveGlobalMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveGlobalMultNormalAny2() {
        assertTrue(callFightHelpBeanMoveGlobalMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void moveGlobalMultSpeed1() {
        assertFalse(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultSpeed2() {
        assertTrue(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultSpeed3() {
        assertTrue(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultSpeed4() {
        assertTrue(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultSpeed5() {
        assertFalse(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultSpeed6() {
        assertFalse(callFightHelpBeanMoveGlobalMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveGlobalMultSpeedAny1() {
        assertFalse(callFightHelpBeanMoveGlobalMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveGlobalMultSpeedAny2() {
        assertTrue(callFightHelpBeanMoveGlobalMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveGlobalMultEvasiness1() {
        assertFalse(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultEvasiness2() {
        assertTrue(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveGlobalMultEvasiness3() {
        assertTrue(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveGlobalMultEvasiness4() {
        assertTrue(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveGlobalMultEvasiness5() {
        assertFalse(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultEvasiness6() {
        assertFalse(callFightHelpBeanMoveGlobalMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultEvasinessAny1() {
        assertFalse(callFightHelpBeanMoveGlobalMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveGlobalMultEvasinessAny2() {
        assertTrue(callFightHelpBeanMoveGlobalMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void moveGlobalMultAccuracy1() {
        assertFalse(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultAccuracy2() {
        assertTrue(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveGlobalMultAccuracy3() {
        assertTrue(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveGlobalMultAccuracy4() {
        assertTrue(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveGlobalMultAccuracy5() {
        assertFalse(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultAccuracy6() {
        assertFalse(callFightHelpBeanMoveGlobalMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveGlobalMultAccuracyAny1() {
        assertFalse(callFightHelpBeanMoveGlobalMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveGlobalMultAccuracyAny2() {
        assertTrue(callFightHelpBeanMoveGlobalMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesGlobalMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesGlobalMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
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
        return toStr(callFightHelpBeanClickMovesGlobalMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickMovesGlobalMultStat(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getMultStatIfContainsType().addEntry(new StatisticType(_st,NULL_REF), Rate.one());
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        s_.getEffects().add(Instances.newEffectGlobal());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
