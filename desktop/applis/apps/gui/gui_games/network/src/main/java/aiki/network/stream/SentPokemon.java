package aiki.network.stream;
import aiki.map.pokemon.PokemonPlayer;


public final class SentPokemon {

    private int index;

    private PokemonPlayer pokemon;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public PokemonPlayer getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonPlayer _pokemon) {
        pokemon = _pokemon;
    }
}
