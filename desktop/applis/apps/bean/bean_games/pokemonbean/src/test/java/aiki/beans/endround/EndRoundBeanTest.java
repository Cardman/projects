package aiki.beans.endround;

import aiki.db.MessagesDataBaseConstants;
import code.maths.*;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EndRoundBeanTest extends InitDbEndRound {
    @Test
    public void evts() {
        assertSizeEq(32,dispEndRoundEvts());
    }
    @Test
    public void global1() {
        assertEq(Rate.one(),EffectEndRoundGlobalBean.global(feedDb().getData().getMove(M_END_3)).getDamageEndRound());
    }
    @Test
    public void global2() {
        assertEq(Rate.zero(),EffectEndRoundGlobalBean.global(feedDb().getData().getMove(M_END_5)).getDamageEndRound());
    }
    @Test
    public void getPuttingKo1() {
        assertTrue(callEffectEndRoundGlobalBeanPuttingKoGet(5));
    }
    @Test
    public void getPuttingKo2() {
        assertFalse(callEffectEndRoundGlobalBeanPuttingKoGet(6));
    }
    @Test
    public void getDamageEndRound() {
        assertEq(Rate.one(),callEffectEndRoundGlobalBeanDamageEndRoundGet(5));
    }
    @Test
    public void getHealingEndRoundGround() {
        assertEq(Rate.one(),callEffectEndRoundGlobalBeanHealingEndRoundGroundGet(5));
    }
    @Test
    public void getHealingEndRound() {
        assertEq(Rate.one(),callEffectEndRoundGlobalBeanHealingEndRoundGet(5));
    }
    @Test
    public void getImmuneTypes1() {
        assertSizeEq(1,callEffectEndRoundGlobalBeanImmuneTypesGet(5));
    }
    @Test
    public void getImmuneTypes2() {
        assertEq(T_TYPE_ROUND_1_TR,elt(callEffectEndRoundGlobalBeanImmuneTypesGet(5),0));
    }
    @Test
    public void getMovesSameCategory1() {
        assertSizeEq(2,callEffectEndRoundPositionTargetBeanGetMovesSameCategory(7));
    }
    @Test
    public void getMovesSameCategory2() {
        assertEq(M_END_5,elt(callEffectEndRoundPositionTargetBeanGetMovesSameCategory(7),0));
    }
    @Test
    public void getMovesSameCategory3() {
        assertEq(M_END_6,elt(callEffectEndRoundPositionTargetBeanGetMovesSameCategory(7),1));
    }
    @Test
    public void getTrTargetRelationMove1() {
        assertEq(M_END_5_TR,callEffectEndRoundPositionTargetBeanGetTrTargetRelationMove(7,0));
    }
    @Test
    public void getTrTargetRelationMove2() {
        assertEq(M_END_6_TR,callEffectEndRoundPositionTargetBeanGetTrTargetRelationMove(7,1));
    }
    @Test
    public void clickTargetRelationMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectEndRoundPositionTargetBeanClickTargetRelationMove(7,0));
    }
    @Test
    public void clickTargetRelationMove2() {
        assertEq(M_END_5,callEffectEndRoundPositionTargetBeanClickTargetRelationMoveId(7,0));
    }
    @Test
    public void clickTargetRelationMove3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectEndRoundPositionTargetBeanClickTargetRelationMove(7,1));
    }
    @Test
    public void clickTargetRelationMove4() {
        assertEq(M_END_6,callEffectEndRoundPositionTargetBeanClickTargetRelationMoveId(7,1));
    }
    @Test
    public void getDamageByStatus1() {
        assertSizeEq(1,callEffectEndRoundMultiRelationBeanDamageByStatusGet(11));
    }
    @Test
    public void getDamageByStatus2() {
        assertEq(S_STA_RELATION,first(elt(callEffectEndRoundMultiRelationBeanDamageByStatusGet(11),0)));
    }
    @Test
    public void getDamageByStatus3() {
        assertEq(Rate.one(),second(elt(callEffectEndRoundMultiRelationBeanDamageByStatusGet(11),0)));
    }
    @Test
    public void getTrDamageStatus() {
        assertEq(S_STA_RELATION_TR,callEffectEndRoundMultiRelationBeanGetTrDamageStatus(11,0));
    }
    @Test
    public void clickDamageStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectEndRoundMultiRelationBeanClickDamageStatus(11,0));
    }
    @Test
    public void clickDamageStatus2() {
        assertEq(S_STA_RELATION,callEffectEndRoundMultiRelationBeanClickDamageStatusId(11,0));
    }
    @Test
    public void getRateDamageFunctionOfNbRounds1() {
        assertSizeEq(2,callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(15));
    }
    @Test
    public void getRateDamageFunctionOfNbRounds2() {
        assertEq(1,first(elt(callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(15),0)));
    }
    @Test
    public void getRateDamageFunctionOfNbRounds3() {
        assertEq(Rate.one(),second(elt(callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(15),0)));
    }
    @Test
    public void getRateDamageFunctionOfNbRounds4() {
        assertEq(3,first(elt(callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(15),1)));
    }
    @Test
    public void getRateDamageFunctionOfNbRounds5() {
        assertEq(Rate.newRate("5"),second(elt(callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(15),1)));
    }
    @Test
    public void getLawForEnablingEffect1() {
        assertSizeEq(2,callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(15));
    }
    @Test
    public void getLawForEnablingEffect2() {
        assertEq(LgInt.newLgInt("5"),first(elt(callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(15),0)));
    }
    @Test
    public void getLawForEnablingEffect3() {
        assertEq(Rate.newRate("5/9"),second(elt(callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(15),0)));
    }
    @Test
    public void getLawForEnablingEffect4() {
        assertEq(LgInt.newLgInt("7"),first(elt(callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(15),1)));
    }
    @Test
    public void getLawForEnablingEffect5() {
        assertEq(Rate.newRate("4/9"),second(elt(callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(15),1)));
    }
    @Test
    public void getDeleteAllStatus() {
        assertEq(Rate.one(),callEffectEndRoundIndividualBeanDeleteAllStatusGet(9));
    }
    @Test
    public void getRecoilDamage() {
        assertEq(Rate.one(),callEffectEndRoundIndividualBeanRecoilDamageGet(9));
    }
    @Test
    public void getHealHp() {
        assertEq(Rate.one(),callEffectEndRoundIndividualBeanHealHpGet(9));
    }
    @Test
    public void getHealHpByOwnerTypes1() {
        assertSizeEq(2,callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(9));
    }
    @Test
    public void getHealHpByOwnerTypes2() {
        assertEq(NULL_REF,first(elt(callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(9),0)));
    }
    @Test
    public void getHealHpByOwnerTypes3() {
        assertEq(Rate.newRate("2"),second(elt(callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(9),0)));
    }
    @Test
    public void getHealHpByOwnerTypes4() {
        assertEq(T_TYPE_HEAL,first(elt(callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(9),1)));
    }
    @Test
    public void getHealHpByOwnerTypes5() {
        assertEq(Rate.one(),second(elt(callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(9),1)));
    }
    @Test
    public void isType1() {
        assertFalse(callEffectEndRoundIndividualBeanIsType(9,0));
    }
    @Test
    public void isType2() {
        assertTrue(callEffectEndRoundIndividualBeanIsType(9,1));
    }
    @Test
    public void getTrType() {
        assertEq(T_TYPE_HEAL_TR,callEffectEndRoundIndividualBeanGetTrType(9,1));
    }
    @Test
    public void getUserStatusEndRound() {
        assertEq(S_STA_END_ROUND,callEffectEndRoundIndividualBeanUserStatusEndRoundGet(9));
    }
    @Test
    public void getTrUserStatus() {
        assertEq(S_STA_END_ROUND_TR,callEffectEndRoundIndividualBeanGetTrUserStatus(9));
    }
    @Test
    public void clickUserStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectEndRoundIndividualBeanClickUserStatus(9));
    }
    @Test
    public void clickUserStatus2() {
        assertEq(S_STA_END_ROUND,callEffectEndRoundIndividualBeanClickUserStatusId(9));
    }
    @Test
    public void getMultDamageByStatus1() {
        assertSizeEq(1,callEffectEndRoundIndividualBeanMultDamageStatusGet(9));
    }
    @Test
    public void getMultDamageByStatus2() {
        assertEq(S_STA_DAM,first(elt(callEffectEndRoundIndividualBeanMultDamageStatusGet(9),0)));
    }
    @Test
    public void getMultDamageByStatus3() {
        assertEq(Rate.one(),second(elt(callEffectEndRoundIndividualBeanMultDamageStatusGet(9),0)));
    }
    @Test
    public void getTrMultDamageStatus() {
        assertEq(S_STA_DAM_TR,callEffectEndRoundIndividualBeanGetTrDamageStatus(9,0));
    }
    @Test
    public void clickMultDamageStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectEndRoundIndividualBeanClickDamageStatus(9,0));
    }
    @Test
    public void clickMultDamageStatus2() {
        assertEq(S_STA_DAM,callEffectEndRoundIndividualBeanClickDamageStatusId(9,0));
    }
    @Test
    public void getInflictedRateHpTarget() {
        assertEq(Rate.one(),callEffectEndRoundFoeBeanInflictedRateHpTargetGet(29));
    }
    @Test
    public void getThievedHpRateTargetToUser() {
        assertEq(Rate.one(),callEffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet(21));
    }
    @Test
    public void getInflictedRateHpTargetSec() {
        assertEq(Rate.one(),callEffectEndRoundStatusBeanInflictedRateHpTargetGet(24));
    }
    @Test
    public void getDeleteAllStatusSec() {
        assertEq(Rate.one(),callEffectEndRoundTeamBeanDeleteAllStatusGet(17));
    }
    @Test
    public void getDeleteAllStatusAlly() {
        assertEq(Rate.one(),callEffectEndRoundTeamBeanDeleteAllStatusAllyGet(17));
    }
    @Test
    public void getHealHpSec() {
        assertEq(Rate.one(),callEffectEndRoundPositionRelationBeanHealHpGet(13));
    }
    @Test
    public void getEndRoundRank() {
        assertEq(1,callEffectEndRoundBeanEndRoundRankGet(0));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callEffectEndRoundBeanReasonsEndRoundGet(9));
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectEndRoundBeanReasonsEndRoundGet(9),0));
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callEffectEndRoundBeanMapVarsFailEndRoundGet(9));
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectEndRoundBeanMapVarsFailEndRoundGet(9),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callEffectEndRoundBeanMapVarsFailEndRoundGet(9),0)));
    }
    @Test
    public void getAbility() {
    assertEq(A_END_14_TR,callEffectEndRoundBeanAbilityGet(16));
    }
    @Test
    public void clickAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callEffectEndRoundBeanClickAbility(16));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_END_14,callEffectEndRoundBeanClickAbilityId(16));
    }
    @Test
    public void getItem() {
        assertEq(I_END_9_TR,callEffectEndRoundBeanItemGet(11));
    }
    @Test
    public void clickItem1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,callEffectEndRoundBeanClickItem(11));
    }
    @Test
    public void clickItem2() {
        assertEq(I_END_9,callEffectEndRoundBeanClickItemId(11));
    }
    @Test
    public void getMove() {
        assertEq(M_END_7_TR,callEffectEndRoundBeanMoveGet(9));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectEndRoundBeanClickMove(9));
    }
    @Test
    public void clickMove2() {
        assertEq(M_END_7,callEffectEndRoundBeanClickMoveId(9));
    }
    @Test
    public void getMoves1() {
        assertSizeEq(2,callEffectEndRoundBeanMovesGet(29));
    }
    @Test
    public void getMoves2() {
        assertEq(M_END_27_TR,elt(callEffectEndRoundBeanMovesGet(29),0));
    }
    @Test
    public void getMoves3() {
        assertEq(M_END_28_TR,elt(callEffectEndRoundBeanMovesGet(29),1));
    }
    @Test
    public void getTrMoves1() {
        assertEq(M_END_27_TR,callEffectEndRoundBeanGetTrMoves(29,0));
    }
    @Test
    public void getTrMoves2() {
        assertEq(M_END_28_TR,callEffectEndRoundBeanGetTrMoves(29,1));
    }
    @Test
    public void clickMoves1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectEndRoundBeanClickMoves(29,0));
    }
    @Test
    public void clickMoves2() {
        assertEq(M_END_27,callEffectEndRoundBeanClickMovesId(29,0));
    }
    @Test
    public void clickMoves3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectEndRoundBeanClickMoves(29,1));
    }
    @Test
    public void clickMoves4() {
        assertEq(M_END_28,callEffectEndRoundBeanClickMovesId(29,1));
    }
    @Test
    public void getStatus() {
        assertEq(S_END_17_TR,callEffectEndRoundBeanStatusGet(19));
    }
    @Test
    public void clickStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callEffectEndRoundBeanClickStatus(19));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_END_17,callEffectEndRoundBeanClickStatusId(19));
    }
}
/**
1
1
1
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
19
20
22
23
24
26
27
28
29
30
31
32
*/