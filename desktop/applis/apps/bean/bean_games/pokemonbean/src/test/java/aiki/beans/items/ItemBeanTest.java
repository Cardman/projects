package aiki.beans.items;

import org.junit.Test;

public final class ItemBeanTest extends InitDbItem {
    @Test
    public void getPrice() {
        assertEq(2,callItemBeanPriceGet(itemLineSample()));
    }
    @Test
    public void getName() {
        assertEq(I_BASE,callItemBeanNameGet(itemLineSample()));
    }
    @Test
    public void getDisplayName() {
        assertEq(I_BASE_TR,callItemBeanDisplayNameGet(itemLineSample()));
    }
    @Test
    public void getDescription() {
        assertEq(CI_BOOST_TR,callItemBeanDescriptionGet(itemLineSample()));
    }
    @Test
    public void callItemBeanItemImageGet() {
        assertEq(MAX_RAI,callItemBeanItemImageGet(itemLineSample()));
    }
    @Test
    public void clickItems() {
        assertEq(AikiBeansItemsStd.WEB_HTML_ITEMS_ITEMS_HTML,callItemBeanClickItems(itemLineSample()));
    }
}
