package aiki.beans;

import aiki.beans.facade.dto.MoveLine;

public final class MvLineStruct extends ParamNatStruct<MoveLine> {
    public MvLineStruct(MoveLine _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public MoveLine getWildPk() {
        return getInstance();
    }
}
