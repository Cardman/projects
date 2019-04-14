package aiki.game;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.map.pokemon.PokemonPlayer;


public final class HostPokemonDuo {

    private PokemonPlayer firstPokemon;

    private PokemonPlayer secondPokemon;

    private int nbSteps;

    public boolean validate(DataBase _data) {
        if (isFree()) {
            if (nbSteps != 0) {
                return false;
            }
            return true;
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
        if (Game.canStoreThesePokemonToHost(new Comment(), firstPokemon, secondPokemon, _data)) {
            return true;
        }
        return false;
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

    public int getNbSteps() {
        return nbSteps;
    }

    public void setNbSteps(int _nbSteps) {
        nbSteps = _nbSteps;
    }

}
