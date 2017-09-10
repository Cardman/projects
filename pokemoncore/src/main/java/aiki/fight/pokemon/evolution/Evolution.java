package aiki.fight.pokemon.evolution;
import aiki.DataBase;
import aiki.fight.pokemon.PokemonData;
import code.serialize.CheckedData;

@CheckedData
public abstract class Evolution {

    public abstract void validate(DataBase _dataBase,PokemonData _fPk);
}
