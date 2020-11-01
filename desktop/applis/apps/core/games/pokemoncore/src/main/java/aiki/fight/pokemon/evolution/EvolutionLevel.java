package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;


public abstract class EvolutionLevel extends Evolution {

    private short level;

    protected final void validateEvolutionLevel(DataBase _dataBase) {
        if (level <= 0) {
            _dataBase.setError(true);
        }
        if (level > _dataBase.getMaxLevel()) {
            _dataBase.setError(true);
        }
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }
}
