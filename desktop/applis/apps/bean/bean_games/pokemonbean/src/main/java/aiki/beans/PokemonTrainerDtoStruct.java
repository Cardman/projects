package aiki.beans;

import aiki.beans.facade.simulation.dto.PokemonTrainerDto;
import code.bean.nat.NaNuSt;

public final class PokemonTrainerDtoStruct extends NaNuSt {
    private final PokemonTrainerDto inst;
    public PokemonTrainerDtoStruct(PokemonTrainerDto _instance) {
        inst=(_instance);
    }
    public PokemonTrainerDto getPokemonTrainerDto() {
        return inst;
    }
}
