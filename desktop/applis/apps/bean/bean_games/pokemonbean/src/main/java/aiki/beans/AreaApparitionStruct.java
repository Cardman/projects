package aiki.beans;

import aiki.map.levels.AreaApparition;

public final class AreaApparitionStruct extends ParamNatStruct<AreaApparition> {
    public AreaApparitionStruct(AreaApparition _wildPk) {
        super(_wildPk);
    }

    public AreaApparition getWildPk() {
        return getInstance();
    }
}
