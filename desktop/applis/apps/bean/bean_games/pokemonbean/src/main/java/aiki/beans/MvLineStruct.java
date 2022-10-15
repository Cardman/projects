package aiki.beans;

import aiki.beans.facade.dto.MoveLine;

public final class MvLineStruct extends ParamNatStruct<MoveLine> {
    public MvLineStruct(MoveLine _wildPk) {
        super(_wildPk);
    }

    public MoveLine getWildPk() {
        return getInstance();
    }
}
