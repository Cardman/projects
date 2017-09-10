package aiki.fight.items;
import code.serialize.CheckedData;

@CheckedData
public final class EvolvingItem extends Item {

    private static final String ITEM = "aiki.fight.items.EvolvingItem";

    @Override
    public String getItemType() {
        return ITEM;
    }

}
