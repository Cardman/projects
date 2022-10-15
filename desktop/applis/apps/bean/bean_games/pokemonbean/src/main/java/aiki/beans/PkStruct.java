package aiki.beans;

import aiki.map.pokemon.Pokemon;

public final class PkStruct extends ParamNatStruct<Pokemon> {
    public PkStruct(Pokemon _wildPk) {
        super(_wildPk);
    }

    public Pokemon getWildPk() {
        return getInstance();
    }
}
