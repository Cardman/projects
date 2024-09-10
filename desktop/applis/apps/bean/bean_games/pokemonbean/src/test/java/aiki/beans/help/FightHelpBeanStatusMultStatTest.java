package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanStatusMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.statusMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void moveTeamMultNormal1() {
        assertFalse(callFightHelpBeanStatusMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormal2() {
        assertTrue(callFightHelpBeanStatusMultNormal(bean(db(Statistic.ATTACK)),0));
    }
    @Test
    public void moveTeamMultNormal3() {
        assertTrue(callFightHelpBeanStatusMultNormal(bean(db(Statistic.DEFENSE)),0));
    }
    @Test
    public void moveTeamMultNormal4() {
        assertTrue(callFightHelpBeanStatusMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void moveTeamMultNormal5() {
        assertTrue(callFightHelpBeanStatusMultNormal(bean(db(Statistic.SPECIAL_DEFENSE)),0));
    }
    @Test
    public void moveTeamMultNormal6() {
        assertFalse(callFightHelpBeanStatusMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormal7() {
        assertFalse(callFightHelpBeanStatusMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultNormalAny1() {
        assertFalse(callFightHelpBeanStatusMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveTeamMultNormalAny2() {
        assertTrue(callFightHelpBeanStatusMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void moveTeamMultSpeed1() {
        assertFalse(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeed2() {
        assertTrue(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed3() {
        assertTrue(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed4() {
        assertTrue(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultSpeed5() {
        assertFalse(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeed6() {
        assertFalse(callFightHelpBeanStatusMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void moveTeamMultSpeedAny1() {
        assertFalse(callFightHelpBeanStatusMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void moveTeamMultSpeedAny2() {
        assertTrue(callFightHelpBeanStatusMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultEvasiness1() {
        assertFalse(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasiness2() {
        assertTrue(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness3() {
        assertTrue(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness4() {
        assertTrue(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.EVASINESS)),0));
    }
    @Test
    public void moveTeamMultEvasiness5() {
        assertFalse(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasiness6() {
        assertFalse(callFightHelpBeanStatusMultEvasiness(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultEvasinessAny1() {
        assertFalse(callFightHelpBeanStatusMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultEvasinessAny2() {
        assertTrue(callFightHelpBeanStatusMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void moveTeamMultAccuracy1() {
        assertFalse(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracy2() {
        assertTrue(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy3() {
        assertTrue(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy4() {
        assertTrue(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void moveTeamMultAccuracy5() {
        assertFalse(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracy6() {
        assertFalse(callFightHelpBeanStatusMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void moveTeamMultAccuracyAny1() {
        assertFalse(callFightHelpBeanStatusMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void moveTeamMultAccuracyAny2() {
        assertTrue(callFightHelpBeanStatusMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanStatusMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrStatusMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        return toStr(callFightHelpBeanClickStatusMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickStatusMultStat(b_,0);
        return getValStatusId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.INDIVIDUEL);
        t_.getMultStat().addEntry(_st, Rate.one());
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple e_ = Instances.newStatusBeginRoundSimple();
        e_.setStatusType(StatusType.INDIVIDUEL);
        f_.getData().completeMembers(M_STA, e_);
        StatusSimple si_ = Instances.newStatusSimple();
        si_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_DAM_VAR, si_);
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
