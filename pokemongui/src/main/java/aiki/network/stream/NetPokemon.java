package aiki.network.stream;
import aiki.map.pokemon.PokemonPlayer;
import code.util.*;


public final class NetPokemon {

    private ByteTreeMap<PokemonPlayer> tradablePokemon;

    public ByteTreeMap< PokemonPlayer> getTradablePokemon() {
        return tradablePokemon;
    }

    public void setTradablePokemon(ByteTreeMap< PokemonPlayer> _tradablePokemon) {
        tradablePokemon = _tradablePokemon;
    }
}
