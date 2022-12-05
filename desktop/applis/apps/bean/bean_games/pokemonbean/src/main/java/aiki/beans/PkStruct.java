package aiki.beans;

import aiki.map.pokemon.Pokemon;
import code.bean.nat.NaNuSt;

public final class PkStruct extends NaNuSt {
    private final Pokemon inst;
    public PkStruct(Pokemon _wildPk) {
        inst=(_wildPk);
    }

    public Pokemon getWildPk() {
        return inst;
    }
}
