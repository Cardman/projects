package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.items.EvolvingItem;
import aiki.fight.pokemon.PokemonData;


public final class EvolutionItem extends Evolution {

    private String item;

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        return !(_dataBase.getItem(item) instanceof EvolvingItem);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }
}
