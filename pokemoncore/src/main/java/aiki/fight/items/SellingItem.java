package aiki.fight.items;
import code.serialize.CheckedData;

@CheckedData
public final class SellingItem extends Item {

    private static final String ITEM = "aiki.fight.items.SellingItem";

    @Override
    public String getItemType() {
        return ITEM;
    }

}
