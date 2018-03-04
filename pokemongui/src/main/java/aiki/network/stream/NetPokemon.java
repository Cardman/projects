package aiki.network.stream;
import aiki.map.pokemon.PokemonPlayer;
import code.util.NatTreeMap;
import code.util.annot.RwXml;

@RwXml
public final class NetPokemon {

    private NatTreeMap<Byte,PokemonPlayer> tradablePokemon;

    public NatTreeMap<Byte, PokemonPlayer> getTradablePokemon() {
        return tradablePokemon;
    }

    public void setTradablePokemon(NatTreeMap<Byte, PokemonPlayer> _tradablePokemon) {
        tradablePokemon = _tradablePokemon;
    }
}
