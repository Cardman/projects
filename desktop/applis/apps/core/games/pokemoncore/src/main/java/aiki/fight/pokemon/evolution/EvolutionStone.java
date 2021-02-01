package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.items.EvolvingStone;
import aiki.fight.pokemon.PokemonData;


public abstract class EvolutionStone extends Evolution {

    private String stone;

    protected final boolean validateEvolutionStone(DataBase _dataBase) {
        return !(_dataBase.getItem(stone) instanceof EvolvingStone);

    }

    public String getStone() {
        return stone;
    }

    public void setStone(String _stone) {
        stone = _stone;
    }
}
