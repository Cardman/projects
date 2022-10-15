package aiki.beans;

import aiki.beans.facade.dto.ItemLine;

public final class ItLineStruct extends ParamNatStruct<ItemLine> {
    public ItLineStruct(ItemLine _wildPk) {
        super(_wildPk);
    }

    public ItemLine getWildPk() {
        return getInstance();
    }
}
