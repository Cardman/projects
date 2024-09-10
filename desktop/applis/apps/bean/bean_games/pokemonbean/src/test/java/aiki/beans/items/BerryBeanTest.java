package aiki.beans.items;

import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class BerryBeanTest extends InitDbBerry {
    @Test
    public void getLawForAttackFirst1() {
        assertTrue(callBerryBeanLawForAttackFirstGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getLawForAttackFirst2() {
        assertFalse(callBerryBeanLawForAttackFirstGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,false,true));
    }
    @Test
    public void getWithoutFail1() {
        assertTrue(callBerryBeanWithoutFailGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getWithoutFail2() {
        assertFalse(callBerryBeanWithoutFailGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,false));
    }
    @Test
    public void isHealingPp1() {
        assertTrue(callBerryBeanIsHealingPp(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void isHealingPp2() {
        assertFalse(callBerryBeanIsHealingPp(Rate.one(),0,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getCategoryBoosting1() {
        assertEq(NULL_REF,callBerryBeanCategoryBoostingGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getCategoryBoosting2() {
        assertEq(C_CAT1_TR,callBerryBeanCategoryBoostingGet(Rate.one(),0,Rate.one(),Rate.one(),C_CAT,true,true));
    }
    @Test
    public void getHealPp() {
        assertEq(1,callBerryBeanHealPpGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getHealHpBySuperEffMove() {
        assertEq(Rate.one(),callBerryBeanHealHpBySuperEffMoveGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getHealHp() {
        assertEq(Rate.one(),callBerryBeanHealHpGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getMaxHpHealingHp() {
        assertEq(Rate.one(),callBerryBeanMaxHpHealingHpGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getMaxHpHealingRateHp() {
        assertEq(Rate.one(),callBerryBeanMaxHpHealingHpRateGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getHealHpRate() {
        assertEq(Rate.one(),callBerryBeanHealHpRateGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getHealStatus1() {
        assertSizeEq(1,callBerryBeanHealStatusGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getHealStatus2() {
        assertEq(S_STA_SIM,elt(callBerryBeanHealStatusGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0));
    }
    @Test
    public void getTrStatus() {
        assertEq(S_STA_SIM_TR,callBerryBeanGetTrStatus(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void clickStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callBerryBeanClickStatus(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_SIM,callBerryBeanClickStatusId(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void getBoostStatis1() {
        assertSizeEq(1,callBerryBeanBoostStatisGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getBoostStatis2() {
        assertEq(1,second(elt(callBerryBeanBoostStatisGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0)));
    }
    @Test
    public void getTrBoostStat() {
        assertEq(ST_SPEED_TR,callBerryBeanGetTrBoostStat(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void getDamageRateRecoilFoe1() {
        assertSizeEq(1,callBerryBeanDamageRateRecoilFoeGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getDamageRateRecoilFoe2() {
        assertEq(C_CAT,first(elt(callBerryBeanDamageRateRecoilFoeGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0)));
    }
    @Test
    public void getDamageRateRecoilFoe3() {
        assertEq(Rate.one(),second(elt(callBerryBeanDamageRateRecoilFoeGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0)));
    }
    @Test
    public void getTrCategRecoil() {
        assertEq(C_CAT1_TR,callBerryBeanGetTrCategRecoil(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void getMultStat1() {
        assertSizeEq(1,callBerryBeanMultStatGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getMultStat2() {
        assertEq(1,callBoostHpRateGetBoost(second(elt(callBerryBeanMultStatGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0))));
    }
    @Test
    public void getMultStat3() {
        assertEq(Rate.one(),callBoostHpRateGetHpRate(second(elt(callBerryBeanMultStatGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0))));
    }
    @Test
    public void getTrMultStat() {
        assertEq(ST_SPEED_TR,callBerryBeanGetTrMultStat(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
    @Test
    public void getMultFoesDamage1() {
        assertSizeEq(1,callBerryBeanMultFoesDamageGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true));
    }
    @Test
    public void getMultFoesDamage2() {
        assertEq(T_TYPE1,first(elt(callBerryBeanMultFoesDamageGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0)));
    }
    @Test
    public void getMultFoesDamage3() {
        assertEq(Rate.one(),callEfficiencyRateGetEff(second(elt(callBerryBeanMultFoesDamageGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0))));
    }
    @Test
    public void getMultFoesDamage4() {
        assertEq(Rate.one(),callEfficiencyRateGetHpRate(second(elt(callBerryBeanMultFoesDamageGet(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true),0))));
    }
    @Test
    public void getTrMultFoesDamage() {
        assertEq(T_TYPE1_TR,callBerryBeanGetTrMultFoesDamage(Rate.one(),1,Rate.one(),Rate.one(),NULL_REF,true,true,0));
    }
}
