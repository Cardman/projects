package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectStatusBeanTest extends InitDbMoveEffectStatus {
    @Test
    public void getKoUserHealSubst1() {
        assertFalse(callEffectStatusBeanKoUserHealSubstGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(eff(false,false)),0)));
    }
    @Test
    public void getKoUserHealSubst2() {
        assertTrue(callEffectStatusBeanKoUserHealSubstGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(eff(true,false)),0)));
    }
    @Test
    public void getStatusFromUser1() {
        assertFalse(callEffectStatusBeanStatusFromUserGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(eff(false,false)),0)));
    }
    @Test
    public void getStatusFromUser2() {
        assertTrue(callEffectStatusBeanStatusFromUserGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(eff(false,true)),0)));
    }
    @Test
    public void getLawStatus1() {
        assertSizeEq(2,callEffectStatusBeanLawStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0)));
    }
    @Test
    public void getLawStatus2() {
        assertEq(S_STA_REL,first(elt(callEffectStatusBeanLawStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getLawStatus3() {
        assertEq(Rate.newRate("3/4"),second(elt(callEffectStatusBeanLawStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getLawStatus4() {
        assertEq(S_STA_SIM,first(elt(callEffectStatusBeanLawStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void getLawStatus5() {
        assertEq(Rate.newRate("1/4"),second(elt(callEffectStatusBeanLawStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void isStatus1() {
        assertFalse(callEffectStatusBeanIsStatus(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),"",LgInt.newLgInt("3"))),0),0));
    }
    @Test
    public void isStatus2() {
        assertTrue(callEffectStatusBeanIsStatus(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),"",LgInt.newLgInt("3"))),0),1));
    }
    @Test
    public void getTrLink1() {
        assertEq(NULL_REF,callEffectStatusBeanGetTrLink(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),"",LgInt.newLgInt("3"))),0),0));
    }
    @Test
    public void getTrLink2() {
        assertEq(S_STA_SIM_TR,callEffectStatusBeanGetTrLink(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),"",LgInt.newLgInt("3"))),0),1));
    }
    @Test
    public void getFail1() {
        assertEq(NULL_REF,callEffectStatusBeanGetFail(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0),0));
    }
    @Test
    public void getFail2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectStatusBeanGetFail(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatusFail(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3")),S_STA_SIM, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0),1));
    }
    @Test
    public void clickLink1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectStatusBeanClickLink(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0),0,0));
    }
    @Test
    public void clickLink2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectStatusBeanClickLink(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0),0,1));
    }
    @Test
    public void clickLink3() {
        assertEq(S_STA_REL,callEffectStatusBeanClickLinkId(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0),0,0));
    }
    @Test
    public void clickLink4() {
        assertEq(S_STA_SIM,callEffectStatusBeanClickLinkId(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3"))),0),0,1));
    }
    @Test
    public void getDeletedStatus1() {
        assertSizeEq(2,callEffectStatusBeanDeletedStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0)));
    }
    @Test
    public void getDeletedStatus2() {
        assertEq(S_STA_REL,elt(callEffectStatusBeanDeletedStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0)),0));
    }
    @Test
    public void getDeletedStatus3() {
        assertEq(S_STA_SIM,elt(callEffectStatusBeanDeletedStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0)),1));
    }
    @Test
    public void getTrLinkDeleted1() {
        assertEq(NULL_REF,callEffectStatusBeanGetTrLinkDeleted(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),"")),0),0));
    }
    @Test
    public void getTrLinkDeleted2() {
        assertEq(S_STA_SIM_TR,callEffectStatusBeanGetTrLinkDeleted(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),"")),0),1));
    }
    @Test
    public void clickLinkDeleted1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectStatusBeanClickLinkDeleted(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0),0,0));
    }
    @Test
    public void clickLinkDeleted2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectStatusBeanClickLinkDeleted(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0),0,1));
    }
    @Test
    public void clickLinkDeleted3() {
        assertEq(S_STA_REL,callEffectStatusBeanClickLinkDeletedId(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0),0,0));
    }
    @Test
    public void clickLinkDeleted4() {
        assertEq(S_STA_SIM,callEffectStatusBeanClickLinkDeletedId(dispMoveEffStatus(feedDbMoveEffDataDamComp(withDeletedStatus(withDeletedStatus(eff(false,false),S_STA_SIM),S_STA_REL)),0),0,1));
    }
    @Test
    public void getMapVarsStatus1() {
        assertSizeEq(1,callEffectStatusBeanMapVarsStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatusFail(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3")),S_STA_SIM, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0),1));
    }
    @Test
    public void getMapVarsStatus2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectStatusBeanMapVarsStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatusFail(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3")),S_STA_SIM, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0),1),0)));
    }
    @Test
    public void getMapVarsStatus3() {
        assertEq(TIME,second(elt(callEffectStatusBeanMapVarsStatusGet(dispMoveEffStatus(feedDbMoveEffDataDamComp(withLawStatusFail(withLawStatus(withLawStatus(eff(false,false),S_STA_SIM, LgInt.one()),S_STA_REL,LgInt.newLgInt("3")),S_STA_SIM, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0),1),0)));
    }
}
