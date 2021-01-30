package aiki.beans;

import aiki.beans.facade.dto.ItemLine;

public final class ItLineStruct extends ParamNatStruct<ItemLine> {
    public ItLineStruct(ItemLine _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public ItemLine getWildPk() {
        return getInstance();
    }
}
