package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanStatusDamageTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.statusDamageInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanStatusDamageGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrStatusDamage(bean(db()),0));
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
        return toStr(callFightHelpBeanClickStatusDamage(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickStatusDamage(b_,0);
        return getValStatusId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        EffectPartnerStatus p_ = Instances.newEffectPartnerStatus();
        p_.setWeddingAlly(true);
        t_.setStatusType(StatusType.RELATION_UNIQUE);
        t_.getEffectsPartner().add(p_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple e_ = Instances.newStatusBeginRoundSimple();
        e_.setStatusType(StatusType.RELATION_UNIQUE);
        e_.getEffectsPartner().add(Instances.newEffectPartnerStatus());
        f_.getData().completeMembers(M_STA, e_);
        StatusBeginRoundSimple u_ = Instances.newStatusBeginRoundSimple();
        u_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_DAM_VAR, u_);
        f_.getData().completeMembers(M_DAM_BAD, Instances.newStatusBeginRoundSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        return f_;
    }
}
