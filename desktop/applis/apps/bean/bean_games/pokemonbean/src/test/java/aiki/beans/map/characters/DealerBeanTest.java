package aiki.beans.map.characters;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class DealerBeanTest extends InitDbCharacters {
    @Test
    public void getAllTmDealer0() {
        assertSizeEq(1,callDealerBeanGetAllTm());
    }
    @Test
    public void getAllTmDealer1() {
        assertEq(M_POK_02,elt(callDealerBeanGetAllTm(),0));
    }
    @Test
    public void getTmDealer() {
        assertEq(M_POK_02_TR,callDealerBeanGetTm());
    }
    @Test
    public void clickTm1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callDealerBeanClickTm());
    }
    @Test
    public void clickTm2() {
        assertEq(M_POK_02,callDealerBeanClickTmId());
    }
    @Test
    public void getItemsDealer0() {
        assertSizeEq(1,callDealerBeanGetItems());
    }
    @Test
    public void getItemsDealer1() {
        assertEq(I_BALL,elt(callDealerBeanGetItems(),0));
    }
    @Test
    public void getItem() {
        assertEq(I_BALL_TR,callDealerBeanGetItem());
    }
    @Test
    public void clickItem1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,callDealerBeanClickItem());
    }
    @Test
    public void clickItem2() {
        assertEq(I_BALL,callDealerBeanClickItemId());
    }
}
