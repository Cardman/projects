package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;

public final class EvolutionLevelSimple extends EvolutionLevel {

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        return validateEvolutionLevel(_dataBase);
    }
}
