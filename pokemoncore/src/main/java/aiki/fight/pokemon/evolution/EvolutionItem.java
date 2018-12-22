package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.items.EvolvingItem;
import aiki.fight.pokemon.PokemonData;


public final class EvolutionItem extends Evolution {

    private String item;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        if (_dataBase.getItem(item) instanceof EvolvingItem) {
            return;
        }
        _dataBase.setError(true);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }
}
