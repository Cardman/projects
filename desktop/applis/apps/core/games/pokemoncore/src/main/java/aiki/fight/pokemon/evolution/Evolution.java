package aiki.fight.pokemon.evolution;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;

public abstract class Evolution {

    public abstract boolean validate(DataBase _dataBase,PokemonData _fPk);
}
