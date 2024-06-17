package aiki.network.stream;
import aiki.map.pokemon.PokemonPlayer;


public final class SentPokemon {

    private byte index;

    private PokemonPlayer pokemon;

    public byte getIndex() {
        return index;
    }

    public void setIndex(byte _index) {
        index = _index;
    }

    public PokemonPlayer getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonPlayer _pokemon) {
        pokemon = _pokemon;
    }
}
