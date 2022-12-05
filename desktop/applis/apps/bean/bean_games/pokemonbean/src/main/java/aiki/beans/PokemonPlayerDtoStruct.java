package aiki.beans;

import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import code.bean.nat.NaNuSt;

public final class PokemonPlayerDtoStruct extends NaNuSt {
    private final PokemonPlayerDto inst;
    public PokemonPlayerDtoStruct(PokemonPlayerDto _instance) {
        inst=(_instance);
    }
    public PokemonPlayerDto getPokemonPlayerDto() {
        return inst;
    }
}
