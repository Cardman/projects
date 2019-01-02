package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;


public abstract class EvolutionLevel extends Evolution {

    private short level;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        if (level <= 0) {
            _dataBase.setError(true);
            return;

        }
        if (level > _dataBase.getMaxLevel()) {
            _dataBase.setError(true);
            return;

        }
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }
}
