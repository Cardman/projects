package aiki.util;

import aiki.game.HostPokemonDuo;
import aiki.instances.Instances;
import code.util.CollCapacity;

public final class CoordssHostPokemonDuo extends Coordss<HostPokemonDuo> {
    public CoordssHostPokemonDuo() {
    }
    public CoordssHostPokemonDuo(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected HostPokemonDuo def() {
        return Instances.newHostPokemonDuo();
    }
}
