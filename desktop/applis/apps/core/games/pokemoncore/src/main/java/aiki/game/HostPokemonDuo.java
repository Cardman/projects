package aiki.game;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.map.pokemon.PokemonPlayer;


public final class HostPokemonDuo {

    private PokemonPlayer firstPokemon;

    private PokemonPlayer secondPokemon;

    private long nbSteps;

    public boolean validate(DataBase _data) {
        if (isFree()) {
            return nbSteps == 0;
        }
        if (!firstPokemon.validate(_data)) {
            return false;
        }
        if (!secondPokemon.validate(_data)) {
            return false;
        }
        firstPokemon.fullHeal(_data);
        secondPokemon.fullHeal(_data);
        if (nbSteps < 0) {
            return false;
        }
        return Game.canStoreThesePokemonToHost(new Comment(), firstPokemon, secondPokemon, _data);
    }
    public boolean isFree() {
        return firstPokemon.hasJustBeenCreated() && secondPokemon.hasJustBeenCreated();
    }
    public PokemonPlayer getFirstPokemon() {
        return firstPokemon;
    }

    public void setFirstPokemon(PokemonPlayer _firstPokemon) {
        firstPokemon = _firstPokemon;
    }

    public PokemonPlayer getSecondPokemon() {
        return secondPokemon;
    }

    public void setSecondPokemon(PokemonPlayer _secondPokemon) {
        secondPokemon = _secondPokemon;
    }

    public long getNbSteps() {
        return nbSteps;
    }

    public void setNbSteps(long _nbSteps) {
        nbSteps = _nbSteps;
    }

}
