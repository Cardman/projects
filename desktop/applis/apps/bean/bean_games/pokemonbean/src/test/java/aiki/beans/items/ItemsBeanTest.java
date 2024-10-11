package aiki.beans.items;

import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ItemsBeanTest extends InitDbItems {
    @Test
    public void itemsBegin() {
        assertSizeEq(0,callItemsBeanItemsGet(dispAllItems(feedDb())));
    }
    @Test
    public void typedName() {
        assertEq("",callItemsBeanTypedNameGet(dispAllItems(feedDb())));
    }
    @Test
    public void typedClass() {
        assertEq("",callItemsBeanTypedClassGet(dispAllItems(feedDb())));
    }
    @Test
    public void typedPrice() {
        assertEq("",callItemsBeanTypedPriceGet(dispAllItems(feedDb())));
    }
    @Test
    public void typedNameSet() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,C_CAT);
        assertEq(C_CAT,callItemsBeanTypedNameGet(bean_));
    }
    @Test
    public void typedClassSet() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedClassSet(bean_,C_CAT);
        assertEq(C_CAT,callItemsBeanTypedClassGet(bean_));
    }
    @Test
    public void typedPriceSet() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedPriceSet(bean_,C_CAT);
        assertEq(C_CAT,callItemsBeanTypedPriceGet(bean_));
    }
    @Test
    public void search1() {
        NaSt bean_ = dispAllItems(feedDb());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(14,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertTrue(StringUtil.contains(keys_,I_BOOST));
        assertTrue(StringUtil.contains(keys_,I_BERRY));
        assertTrue(StringUtil.contains(keys_,I_EVO_ITEM));
        assertTrue(StringUtil.contains(keys_,I_EVO_STONE));
        assertTrue(StringUtil.contains(keys_,I_FOSSIL));
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
        assertTrue(StringUtil.contains(keys_,I_HEAL));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_PP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP_STATUS));
        assertTrue(StringUtil.contains(keys_,I_HEAL_STATUS));
        assertTrue(StringUtil.contains(keys_,I_REPEL));
        assertTrue(StringUtil.contains(keys_,I_SELLING));
    }
    @Test
    public void search2() {
        NaSt bean_ = dispAllItems(feedDb());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(14,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertTrue(StringUtil.contains(keys_,I_BOOST));
        assertTrue(StringUtil.contains(keys_,I_BERRY));
        assertTrue(StringUtil.contains(keys_,I_EVO_ITEM));
        assertTrue(StringUtil.contains(keys_,I_EVO_STONE));
        assertTrue(StringUtil.contains(keys_,I_FOSSIL));
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
        assertTrue(StringUtil.contains(keys_,I_HEAL));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_PP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP_STATUS));
        assertTrue(StringUtil.contains(keys_,I_HEAL_STATUS));
        assertTrue(StringUtil.contains(keys_,I_REPEL));
        assertTrue(StringUtil.contains(keys_,I_SELLING));
    }
    @Test
    public void search3() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,"*HEAL*");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_PP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP_STATUS));
        assertTrue(StringUtil.contains(keys_,I_HEAL_STATUS));
    }
    @Test
    public void search4() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedClassSet(bean_,"*HEAL*");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_PP));
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP_STATUS));
        assertTrue(StringUtil.contains(keys_,I_HEAL_STATUS));
    }
    @Test
    public void search5() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedPriceSet(bean_,"1");
        callItemsBeanTypedNameSet(bean_,"*B*");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(3,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertTrue(StringUtil.contains(keys_,I_BERRY));
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
    }
    @Test
    public void search6() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedPriceSet(bean_,"1");
        callItemsBeanTypedClassSet(bean_,"*B*L*");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
    }
    @Test
    public void search7() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedPriceSet(bean_,"1");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(3,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertTrue(StringUtil.contains(keys_,I_BERRY));
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
    }
    @Test
    public void search8() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_BALL);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search9() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_BALL_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BALL));
        assertEq(I_BALL,getValItemId(bean_));
    }
    @Test
    public void search10() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_BERRY_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BERRY));
        assertEq(I_BERRY,getValItemId(bean_));
    }
    @Test
    public void search11() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_BOOST_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BOOST_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_BOOST));
        assertEq(I_BOOST,getValItemId(bean_));
    }
    @Test
    public void search12() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_EVO_ITEM_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_EVO_ITEM));
        assertEq(I_EVO_ITEM,getValItemId(bean_));
    }
    @Test
    public void search13() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_EVO_STONE_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_EVO_STONE));
        assertEq(I_EVO_STONE,getValItemId(bean_));
    }
    @Test
    public void search14() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_FOSSIL_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_FOSSIL));
        assertEq(I_FOSSIL,getValItemId(bean_));
    }
    @Test
    public void search15() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_HEAL_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL));
        assertEq(I_HEAL,getValItemId(bean_));
    }
    @Test
    public void search16() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_HEAL_HP_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHP_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP));
        assertEq(I_HEAL_HP,getValItemId(bean_));
    }
    @Test
    public void search17() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_HEAL_PP_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL_PP));
        assertEq(I_HEAL_PP,getValItemId(bean_));
    }
    @Test
    public void search18() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_HEAL_HP_STATUS_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL_HP_STATUS));
        assertEq(I_HEAL_HP_STATUS,getValItemId(bean_));
    }
    @Test
    public void search19() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_HEAL_STATUS_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_HEAL_STATUS));
        assertEq(I_HEAL_STATUS,getValItemId(bean_));
    }
    @Test
    public void search20() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_ITEMBATTLE_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_ITEMBATTLE));
        assertEq(I_ITEMBATTLE,getValItemId(bean_));
    }
    @Test
    public void search21() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_REPEL_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_REPEL_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_REPEL));
        assertEq(I_REPEL,getValItemId(bean_));
    }
    @Test
    public void search22() {
        NaSt bean_ = dispAllItems(feedDb());
        callItemsBeanTypedNameSet(bean_,I_SELLING_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML, navigateItemsSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,I_SELLING));
        assertEq(I_SELLING,getValItemId(bean_));
    }
    @Test
    public void search23() {
        NaSt bean_ = dispAllItems(feedDb());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML, navigateItemsSearch(bean_));
        beforeDisplaying(bean_);
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        CustList<String> keys_ = forms(bean_).getValItemData(CST_ITEMS_SET).getKeys();
        assertEq(14,keys_.size());
        assertEq(I_BALL_TR,callItemLineDisplayNameGet(elt(callItemsBeanItemsGet(bean_),0)));
        assertEq(CI_BALL_TR,callItemLineDescriptionClassGet(elt(callItemsBeanItemsGet(bean_),0)));
        assertEq(1,callItemLinePriceGet(elt(callItemsBeanItemsGet(bean_),0)));
    }
    @Test
    public void clickLink() {
        NaSt bean_ = dispAllItems(feedDb());
        navigateItemsSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,callItemsBeanClickLink(bean_,0));
        assertEq(I_BALL,getValItemId(bean_));
    }
    @Test
    public void getMiniImage() {
        NaSt bean_ = dispAllItems(feedDb());
        navigateItemsSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(one(IMG_MAX_RAI),callItemsBeanGetMiniImage(bean_,0));
    }
}
