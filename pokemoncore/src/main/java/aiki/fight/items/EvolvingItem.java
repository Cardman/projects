package aiki.fight.items;
import code.datacheck.CheckedData;

@CheckedData
public final class EvolvingItem extends Item {

    private static final String ITEM = "dbpokemon.fight.items.EvolvingItem";

    @Override
    public String getItemType() {
        return ITEM;
    }

}
