package aiki.beans.endround;

import aiki.beans.status.AikiBeansStatusStd;
import code.maths.*;
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
        assertEq(AikiBeansStatusStd.WEB_HTML_STATUS_DATA_HTML,callEffectEndRoundMultiRelationBeanClickDamageStatus(11,0));
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