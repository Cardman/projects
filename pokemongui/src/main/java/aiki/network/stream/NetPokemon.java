package aiki.network.stream;
import code.util.NatTreeMap;
import code.util.annot.RwXml;
import aiki.map.pokemon.PokemonPlayer;

@RwXml
public class NetPokemon {

    private NatTreeMap<Byte,PokemonPlayer> tradablePokemon;

    public NatTreeMap<Byte, PokemonPlayer> getTradablePokemon() {
        return tradablePokemon;
    }

    public void setTradablePokemon(NatTreeMap<Byte, PokemonPlayer> _tradablePokemon) {
        tradablePokemon = _tradablePokemon;
    }
}
