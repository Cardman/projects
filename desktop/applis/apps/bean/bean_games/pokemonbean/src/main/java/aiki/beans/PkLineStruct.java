package aiki.beans;

import aiki.beans.facade.dto.PokemonLine;

public final class PkLineStruct extends ParamNatStruct<PokemonLine> {
    public PkLineStruct(PokemonLine _wildPk) {
        super(_wildPk);
    }

    public PokemonLine getWildPk() {
        return getInstance();
    }
}
