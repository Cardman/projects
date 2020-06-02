package aiki.fight.pokemon.evolution;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;

public abstract class Evolution {

    public abstract void validate(DataBase _dataBase,PokemonData _fPk);
}
