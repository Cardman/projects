package aiki.network.stream;
import aiki.map.pokemon.PokemonPlayer;
import code.util.*;


public final class NetPokemon {

    private IntTreeMap<PokemonPlayer> tradablePokemon;

    public IntTreeMap< PokemonPlayer> getTradablePokemon() {
        return tradablePokemon;
    }

    public void setTradablePokemon(IntTreeMap< PokemonPlayer> _tradablePokemon) {
        tradablePokemon = _tradablePokemon;
    }
}
