package aiki.beans;

import aiki.beans.facade.solution.dto.WildPokemonDto;

public final class WildPokemonDtoStruct extends ParamNatStruct<WildPokemonDto> {
    public WildPokemonDtoStruct(WildPokemonDto _instance) {
        super(_instance);
    }
    public WildPokemonDto getWildPokemonDto() {
        return getInstance();
    }
}
