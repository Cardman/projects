package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanDeletedStatusSwitchTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.deletedStatusSwitchInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitRelTest() {
        StringList ls_ = FightHelpBean.deletedStatusSwitchInit(dbRel().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanDeletedStatusSwitchGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrDeletedStatusSwitch(bean(db()),0));
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
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickDeletedStatusSwitch(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickDeletedStatusSwitch(b_,0);
        return getValStatusId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setDisabledEffIfSwitch(true);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newStatusBeginRoundSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
    private static FacadeGame dbRel() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_DAM, t_);
        StatusSimple si_ = Instances.newStatusSimple();
        si_.setStatusType(StatusType.INDIVIDUEL);
        f_.getData().completeMembers(M_DAM_VAR, si_);
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
