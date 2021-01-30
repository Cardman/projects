package aiki.beans;

import aiki.map.pokemon.Pokemon;

public final class PkStruct extends ParamNatStruct<Pokemon> {
    public PkStruct(Pokemon _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public Pokemon getWildPk() {
        return getInstance();
    }
}
