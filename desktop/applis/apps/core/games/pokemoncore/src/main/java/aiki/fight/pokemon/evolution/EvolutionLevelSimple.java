package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;

public final class EvolutionLevelSimple extends EvolutionLevel {

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        validateEvolutionLevel(_dataBase);
    }
}
