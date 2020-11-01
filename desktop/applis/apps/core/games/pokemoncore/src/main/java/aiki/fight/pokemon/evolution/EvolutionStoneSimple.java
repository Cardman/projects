package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;

public final class EvolutionStoneSimple extends EvolutionStone {

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        validateEvolutionStone(_dataBase);
    }
}
