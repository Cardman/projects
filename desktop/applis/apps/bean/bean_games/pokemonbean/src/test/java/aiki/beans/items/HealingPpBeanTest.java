package aiki.beans.items;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class HealingPpBeanTest extends InitDbHealingPp {
    @Test
    public void getHappiness1() {
        assertSizeEq(2,callHealingItemBeanHappinessGet(ppDb(true,true,1,1)));
    }
    @Test
    public void getHappiness2() {
        assertEq(I_BALL,first(elt(callHealingItemBeanHappinessGet(ppDb(true,true,1,1)),0)));
    }
    @Test
    public void getHappiness3() {
        assertEq(1,second(elt(callHealingItemBeanHappinessGet(ppDb(true,true,1,1)),0)));
    }
    @Test
    public void getHappiness4() {
        assertEq(I_BOOST,first(elt(callHealingItemBeanHappinessGet(ppDb(true,true,1,1)),1)));
    }
    @Test
    public void getHappiness5() {
        assertEq(2,second(elt(callHealingItemBeanHappinessGet(ppDb(true,true,1,1)),1)));
    }
    @Test
    public void isBall1() {
        assertTrue(callHealingItemBeanIsBall(ppDb(true,true,1,1),0));
    }
    @Test
    public void isBall2() {
        assertFalse(callHealingItemBeanIsBall(ppDb(true,true,1,1),1));
    }
    @Test
    public void getTrHappiness1() {
        assertEq(I_BALL_TR,callHealingItemBeanGetTrHappiness(ppDb(true,true,1,1),0));
    }
    @Test
    public void getTrHappiness2() {
        assertEq(I_BOOST_TR,callHealingItemBeanGetTrHappiness(ppDb(true,true,1,1),1));
    }
    @Test
    public void clickHappiness1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,callHealingItemBeanClickHappiness(ppDb(true,true,1,1),0));
    }
    @Test
    public void clickHappiness2() {
        assertEq(I_BALL,callHealingItemBeanClickHappinessId(ppDb(true,true,1,1),0));
    }
    @Test
    public void getHealingAllMovesPp1() {
        assertTrue(callHealingPpBeanHealingAllMovesPpGet(ppDb(true,true,1,1)));
    }
    @Test
    public void getHealingAllMovesPp2() {
        assertFalse(callHealingPpBeanHealingAllMovesPpGet(ppDb(true,false,1,1)));
    }
    @Test
    public void getHealingMoveFullpp1() {
        assertTrue(callHealingPpBeanHealingMoveFullppGet(ppDb(true,true,1,1)));
    }
    @Test
    public void getHealingMoveFullpp2() {
        assertFalse(callHealingPpBeanHealingMoveFullppGet(ppDb(false,true,1,1)));
    }
    @Test
    public void limitedPpMove1() {
        assertTrue(callHealingPpBeanLimitedPpMove(ppDb(true,true,1,1)));
    }
    @Test
    public void limitedPpMove2() {
        assertFalse(callHealingPpBeanLimitedPpMove(ppDb(true,true,1,0)));
    }
    @Test
    public void limitedPpMoves1() {
        assertTrue(callHealingPpBeanLimitedPpMoves(ppDb(true,true,1,1)));
    }
    @Test
    public void limitedPpMoves2() {
        assertFalse(callHealingPpBeanLimitedPpMoves(ppDb(true,true,0,1)));
    }
    @Test
    public void getHealedMovePp() {
        assertEq(1,callHealingPpBeanHealedMovePpGet(ppDb(true,true,0,1)));
    }
    @Test
    public void getHealingAllMovesFullpp() {
        assertEq(1,callHealingPpBeanHealingAllMovesFullppGet(ppDb(true,true,1,0)));
    }
}
