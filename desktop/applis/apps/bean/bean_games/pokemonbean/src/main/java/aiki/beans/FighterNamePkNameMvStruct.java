package aiki.beans;

import aiki.game.fight.FighterNamePkNameMv;
import code.bean.nat.NaNuSt;

public final class FighterNamePkNameMvStruct extends NaNuSt {
    private final FighterNamePkNameMv key;

    public FighterNamePkNameMvStruct(FighterNamePkNameMv _k) {
        this.key = _k;
    }

    public FighterNamePkNameMv getKey() {
        return key;
    }
}
