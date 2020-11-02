package aiki.beans.items;

public class SellingItemBean extends ItemBean {

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
    }
}