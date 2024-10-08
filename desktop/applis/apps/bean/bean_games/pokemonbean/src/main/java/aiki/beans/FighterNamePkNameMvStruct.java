package aiki.beans;

import aiki.beans.fight.FighterImgPkNameMv;
import code.bean.nat.NaNuSt;

public final class FighterNamePkNameMvStruct extends NaNuSt {
    private final FighterImgPkNameMv key;

    public FighterNamePkNameMvStruct(FighterImgPkNameMv _k) {
        this.key = _k;
    }

    public FighterImgPkNameMv getKey() {
        return key;
    }
}
