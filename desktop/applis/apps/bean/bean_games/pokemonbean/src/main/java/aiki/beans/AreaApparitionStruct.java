package aiki.beans;

import aiki.map.levels.AreaApparition;

public final class AreaApparitionStruct extends ParamNatStruct<AreaApparition> {
    public AreaApparitionStruct(AreaApparition _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public AreaApparition getWildPk() {
        return getInstance();
    }
}
