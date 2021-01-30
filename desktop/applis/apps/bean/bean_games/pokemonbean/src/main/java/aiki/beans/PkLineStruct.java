package aiki.beans;

import aiki.beans.facade.dto.PokemonLine;

public final class PkLineStruct extends ParamNatStruct<PokemonLine> {
    public PkLineStruct(PokemonLine _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public PokemonLine getWildPk() {
        return getInstance();
    }
}
