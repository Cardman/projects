package aiki.beans;

import aiki.beans.facade.solution.dto.WildPokemonDto;
import code.bean.nat.NaNuSt;

public final class WildPokemonDtoStruct extends NaNuSt {
    private final WildPokemonDto inst;
    public WildPokemonDtoStruct(WildPokemonDto _instance) {
        inst=(_instance);
    }
    public WildPokemonDto getWildPokemonDto() {
        return inst;
    }
}
