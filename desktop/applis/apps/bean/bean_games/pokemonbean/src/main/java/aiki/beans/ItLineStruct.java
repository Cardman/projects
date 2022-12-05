package aiki.beans;

import aiki.beans.facade.dto.ItemLine;
import code.bean.nat.NaNuSt;

public final class ItLineStruct extends NaNuSt {
    private final ItemLine inst;
    public ItLineStruct(ItemLine _wildPk) {
        inst=(_wildPk);
    }

    public ItemLine getWildPk() {
        return inst;
    }
}
