package aiki.beans.help;

import aiki.beans.status.AikiBeansStatusStd;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusType;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanAutoDamageTest extends InitDbFightHelp {

    public static final String POWER_TR = "power";

    @Test
    public void movesTypesDefWeatherInitTest() {
        DataBase d_ = db().getData();
        AbsMap<String, StatusBeginRoundAutoDamage> map_ = FightHelpBean.autoDamageInit(d_, EN);
        assertEq(1,map_.size());
        assertEq(M_DAM,map_.getKey(0));
    }

    @Test
    public void movesTypesDefWeatherInit02Test() {
        DataBase d_ = db().getData();
        AbsMap<String, StatusBeginRoundAutoDamage> map_ = FightHelpBean.autoDamageInit(d_, EN);
        NatStringTreeMap<String> vars_ = FightHelpBean.mapAutoDamageInit(d_, EN,map_);
        assertEq(1,vars_.size());
        assertEq(Fight.TEMPS_TOUR,vars_.getKey(0));
        assertEq(TIME,vars_.getValue(0));
    }
    @Test
    public void getFomula() {
        assertEq(Fight.TEMPS_TOUR+"+1",callFightHelpBeanGetFomula(bean(db()),0));
    }
    @Test
    public void init1() {
        assertSizeEq(1,callFightHelpBeanAutoDamageGet(bean(db())));
    }
    @Test
    public void init2() {
        assertSizeEq(1,callFightHelpBeanMapAutoDamageGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAutoDamage(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(AikiBeansStatusStd.WEB_HTML_STATUS_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickAutoDamage(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickAutoDamage(b_,0);
        return getValStatusId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        StatusBeginRoundAutoDamage t_ = Instances.newStatusBeginRoundAutoDamage();
        t_.setPower(Rate.one());
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, simple(StatusType.RELATION_UNIQUE));
        f_.getData().setDamageFormula(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR+"+"+DataBase.VAR_PREFIX+Fight.POWER);
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.POWER, TAB+Fight.POWER+TAB+ POWER_TR);
        return f_;
    }
}
