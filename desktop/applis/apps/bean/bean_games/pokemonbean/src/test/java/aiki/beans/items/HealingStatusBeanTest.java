package aiki.beans.items;

import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class HealingStatusBeanTest extends InitDbHealingStatus {
    @Test
    public void getHealingKo1() {
        assertTrue(callHealingStatusBeanHealingKoGet(statusDb(full(true))));
    }
    @Test
    public void getHealingKo2() {
        assertFalse(callHealingStatusBeanHealingKoGet(statusDb(full(false))));
    }
    @Test
    public void getHealedHpRate1() {
        assertEq(Rate.one(),callHealingStatusBeanHealedHpRateGet(statusDb(full(true))));
    }
    @Test
    public void getHealedHpRate2() {
        assertEq(Rate.zero(),callHealingStatusBeanHealedHpRateGet(statusDb(simple())));
    }
    @Test
    public void getStatus1() {
        assertSizeEq(1,callHealingStatusBeanStatusGet(statusDb(full(true))));
    }
    @Test
    public void getStatus2() {
        assertEq(S_STA_SIM,elt(callHealingStatusBeanStatusGet(statusDb(full(false))),0));
    }
    @Test
    public void getTrStatus() {
        assertEq(S_STA_SIM_TR,callHealingStatusBeanGetTrStatus(statusDb(full(true)),0));
    }
    @Test
    public void clickStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callHealingStatusBeanClickStatus(statusDb(full(false)),0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_SIM,callHealingStatusBeanClickStatusId(statusDb(full(false)),0));
    }
}
