package aiki.beans;

import aiki.beans.facade.dto.PokemonLine;
import code.bean.nat.NaNuSt;

public final class PkLineStruct extends NaNuSt {
    private final PokemonLine inst;
    public PkLineStruct(PokemonLine _wildPk) {
        inst=(_wildPk);
    }

    public PokemonLine getWildPk() {
        return inst;
    }
}
