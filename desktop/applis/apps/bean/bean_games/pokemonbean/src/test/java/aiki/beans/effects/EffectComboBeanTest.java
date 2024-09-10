package aiki.beans.effects;

import aiki.db.MessagesDataBaseConstants;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectComboBeanTest extends InitDbMoveEffectCombo {
    @Test
    public void getCombosKey1() {
        assertSizeEq(6,callCombosBeanGetCombosKey());
    }
    @Test
    public void getCombosKey2() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),0));
    }
    @Test
    public void getCombosKey3() {
        assertEq(M_STA_00,elt(elt(callCombosBeanGetCombosKey(),0),0));
    }
    @Test
    public void getCombosKey4() {
        assertEq(M_STA_01,elt(elt(callCombosBeanGetCombosKey(),0),1));
    }
    @Test
    public void getCombosKey5() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),1));
    }
    @Test
    public void getCombosKey6() {
        assertEq(M_STA_00,elt(elt(callCombosBeanGetCombosKey(),1),0));
    }
    @Test
    public void getCombosKey7() {
        assertEq(M_STA_02,elt(elt(callCombosBeanGetCombosKey(),1),1));
    }
    @Test
    public void getCombosKey8() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),2));
    }
    @Test
    public void getCombosKey9() {
        assertEq(M_STA_00,elt(elt(callCombosBeanGetCombosKey(),2),0));
    }
    @Test
    public void getCombosKey10() {
        assertEq(M_STA_03,elt(elt(callCombosBeanGetCombosKey(),2),1));
    }
    @Test
    public void getCombosKey11() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),3));
    }
    @Test
    public void getCombosKey12() {
        assertEq(M_STA_01,elt(elt(callCombosBeanGetCombosKey(),3),0));
    }
    @Test
    public void getCombosKey13() {
        assertEq(M_STA_02,elt(elt(callCombosBeanGetCombosKey(),3),1));
    }
    @Test
    public void getCombosKey14() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),4));
    }
    @Test
    public void getCombosKey15() {
        assertEq(M_STA_01,elt(elt(callCombosBeanGetCombosKey(),4),0));
    }
    @Test
    public void getCombosKey16() {
        assertEq(M_STA_03,elt(elt(callCombosBeanGetCombosKey(),4),1));
    }
    @Test
    public void getCombosKey17() {
        assertSizeEq(2,elt(callCombosBeanGetCombosKey(),5));
    }
    @Test
    public void getCombosKey18() {
        assertEq(M_STA_02,elt(elt(callCombosBeanGetCombosKey(),5),0));
    }
    @Test
    public void getCombosKey19() {
        assertEq(M_STA_03,elt(elt(callCombosBeanGetCombosKey(),5),1));
    }
    @Test
    public void getEndRound1() {
        assertFalse(callEffectComboBeanEndRoundGet(0));
    }
    @Test
    public void getEndRound2() {
        assertTrue(callEffectComboBeanEndRoundGet(1));
    }
    @Test
    public void getEndRoundRank1() {
        assertEq(0,callEffectComboBeanEndRoundRankGet(0));
    }
    @Test
    public void getEndRoundRank2() {
        assertEq(1,callEffectComboBeanEndRoundRankGet(1));
    }
    @Test
    public void getRankIncrementNbRound() {
        assertEq(3,callEffectComboBeanRankIncrementNbRoundGet(0));
    }
    @Test
    public void getMultEvtRateSecEff() {
        assertEq(Rate.one(),callEffectComboBeanMultEvtRateSecEffGet(0));
    }
    @Test
    public void getMoves1() {
        assertSizeEq(2,callEffectComboBeanMovesGet(0));
    }
    @Test
    public void getMoves2() {
        assertEq(M_STA_00_TR,elt(callEffectComboBeanMovesGet(0),0));
    }
    @Test
    public void getMoves3() {
        assertEq(M_STA_01_TR,elt(callEffectComboBeanMovesGet(0),1));
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callEffectComboBeanMapVarsFailEndRoundGet(1));
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectComboBeanMapVarsFailEndRoundGet(1),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callEffectComboBeanMapVarsFailEndRoundGet(1),0)));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callEffectComboBeanReasonsEndRoundGet(1));
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectComboBeanReasonsEndRoundGet(1),0));
    }
    @Test
    public void getMultStatisticFoe1() {
        assertSizeEq(1,callEffectComboBeanMultStatisticFoeGet(0));
    }
    @Test
    public void getTrStatistic() {
        assertEq(EV_TR,callEffectComboBeanGetTrStatistic(0,0));
    }
    @Test
    public void getMultStatisticFoe2() {
        assertEq(Rate.one(),second(elt(callEffectComboBeanMultStatisticFoeGet(0),0)));
    }
    @Test
    public void getRepeatedRoundsLaw1() {
        assertSizeEq(2,callEffectComboBeanRepeatedRoundsLawGet(2));
    }
    @Test
    public void getRepeatedRoundsLaw2() {
        assertEq(LgInt.newLgInt("5"),first(elt(callEffectComboBeanRepeatedRoundsLawGet(2),0)));
    }
    @Test
    public void getRepeatedRoundsLaw3() {
        assertEq(Rate.newRate("15/28"),second(elt(callEffectComboBeanRepeatedRoundsLawGet(2),0)));
    }
    @Test
    public void getRepeatedRoundsLaw4() {
        assertEq(LgInt.newLgInt("7"),first(elt(callEffectComboBeanRepeatedRoundsLawGet(2),1)));
    }
    @Test
    public void getRepeatedRoundsLaw5() {
        assertEq(Rate.newRate("13/28"),second(elt(callEffectComboBeanRepeatedRoundsLawGet(2),1)));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectComboBeanClickMove(0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectComboBeanClickMove(0,1));
    }
    @Test
    public void clickMove3() {
        assertEq(M_STA_00,callEffectComboBeanClickMoveId(0,0));
    }
    @Test
    public void clickMove4() {
        assertEq(M_STA_01,callEffectComboBeanClickMoveId(0,1));
    }
}
