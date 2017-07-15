package aiki.fight.items;
import code.datacheck.CheckedData;

@CheckedData
public final class EvolvingStone extends Item {

    private static final String ITEM = "dbpokemon.fight.items.EvolvingStone";

    @Override
    public String getItemType() {
        return ITEM;
    }

}
