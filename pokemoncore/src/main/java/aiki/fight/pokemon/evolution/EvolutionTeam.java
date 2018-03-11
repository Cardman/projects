package aiki.fight.pokemon.evolution;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.util.annot.RwXml;


@RwXml
public final class EvolutionTeam extends Evolution {

    private String pokemon;

    @Override
    public void validate(DataBase _dataBase,PokemonData _fPk) {
        if (_dataBase.getPokemon(pokemon).getGenderRep() == GenderRepartition.LEGENDARY) {
            throw new DataException();
        }
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _pokemon) {
        pokemon = _pokemon;
    }

}
