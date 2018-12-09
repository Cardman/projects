package aiki.fight.pokemon.evolution;

import aiki.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;


public final class EvolutionTeam extends Evolution {

    private String pokemon;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        if (_dataBase.getPokemon(pokemon).getGenderRep() == GenderRepartition.LEGENDARY) {
            _dataBase.setError(true);

        }
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _pokemon) {
        pokemon = _pokemon;
    }

}
