package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.util.core.StringUtil;


public final class EvolutionTeam extends Evolution {

    private String pokemon;

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        PokemonData pk_ = _dataBase.getPokemon(pokemon);
        if (pk_ == null) {
            return true;
        }
        boolean res_ = !StringUtil.quickEq(pk_.getBaseEvo(), pokemon);
        if (pk_.getGenderRep() == GenderRepartition.LEGENDARY) {
            res_ = true;
        }
        return res_;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _pokemon) {
        pokemon = _pokemon;
    }

}
