package aiki.beans.map.characters;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class SellerBeanTest extends InitDbCharacters {
    @Test
    public void getAllTmSeller0() {
        assertSizeEq(1,callSellerBeanGetAllTm());
    }
    @Test
    public void getAllTmSeller1() {
        assertEq(M_POK_03,elt(callSellerBeanGetAllTm(),0));
    }
    @Test
    public void getTmSeller() {
        assertEq(M_POK_03_TR,callSellerBeanGetTm());
    }
    @Test
    public void clickTm1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callSellerBeanClickTm());
    }
    @Test
    public void clickTm2() {
        assertEq(M_POK_03,callSellerBeanClickTmId());
    }
    @Test
    public void getItemsSeller0() {
        assertSizeEq(1,callSellerBeanGetItems());
    }
    @Test
    public void getItemsSeller1() {
        assertEq(I_ITEMBATTLE,elt(callSellerBeanGetItems(),0));
    }
    @Test
    public void getItem() {
        assertEq(I_ITEMBATTLE_TR,callSellerBeanGetItem());
    }
    @Test
    public void clickItem1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,callSellerBeanClickItem());
    }
    @Test
    public void clickItem2() {
        assertEq(I_ITEMBATTLE,callSellerBeanClickItemId());
    }
}
