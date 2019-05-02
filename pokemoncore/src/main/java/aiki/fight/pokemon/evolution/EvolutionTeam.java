package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;


public final class EvolutionTeam extends Evolution {

    private String pokemon;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        PokemonData pk_ = _dataBase.getPokemon(this.pokemon);
        if (pk_ == null) {
            _dataBase.setError(true);
            return;
        }
        if (pk_.getGenderRep() == GenderRepartition.LEGENDARY) {
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
