package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusType;
import aiki.instances.Instances;
import code.maths.LgInt;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanBeginRoundStatusTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.beginRoundStatusInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void hasLawForAttack1() {
        assertFalse(callFightHelpBeanHasLawForAttack(bean(db()),0));
    }
    @Test
    public void hasLawForAttack2() {
        assertTrue(callFightHelpBeanHasLawForAttack(bean(dbLawAtt()),0));
    }
    @Test
    public void hasLawForAttack3() {
        assertTrue(callFightHelpBeanHasLawForAttack(bean(dbLawAttSec()),0));
    }
    @Test
    public void hasLawForAttackAny1() {
        assertFalse(callFightHelpBeanHasLawForAttackAny(bean(db())));
    }
    @Test
    public void hasLawForAttackAny2() {
        assertTrue(callFightHelpBeanHasLawForAttackAny(bean(dbLawAtt())));
    }
    @Test
    public void hasLawForHeal1() {
        assertFalse(callFightHelpBeanHasLawForHeal(bean(db()),0));
    }
    @Test
    public void hasLawForHeal2() {
        assertTrue(callFightHelpBeanHasLawForHeal(bean(dbLawHeal()),0));
    }
    @Test
    public void hasLawForHealAny1() {
        assertFalse(callFightHelpBeanHasLawForHealAny(bean(db())));
    }
    @Test
    public void hasLawForHealAny2() {
        assertTrue(callFightHelpBeanHasLawForHealAny(bean(dbLawHeal())));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.INDIVIDUEL);
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple s_ = Instances.newStatusBeginRoundSimple();
        s_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbLawAtt() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.INDIVIDUEL);
        t_.getLawForUsingAMove().addQuickEvent(BoolVal.TRUE, LgInt.one());
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple s_ = Instances.newStatusBeginRoundSimple();
        s_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbLawAttSec() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.INDIVIDUEL);
        t_.getLawForUsingAMoveIfFoe().addQuickEvent(BoolVal.TRUE, LgInt.one());
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple s_ = Instances.newStatusBeginRoundSimple();
        s_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbLawHeal() {
        FacadeGame f_ = facade();
        StatusBeginRoundSimple t_ = Instances.newStatusBeginRoundSimple();
        t_.setStatusType(StatusType.INDIVIDUEL);
        t_.getLawForFullHealIfMove().addQuickEvent(BoolVal.TRUE, LgInt.one());
        f_.getData().completeMembers(M_DAM, t_);
        StatusBeginRoundSimple s_ = Instances.newStatusBeginRoundSimple();
        s_.setStatusType(StatusType.RELATION_UNIQUE);
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusSimple());
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
