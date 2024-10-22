package aiki.db;

import aiki.fight.moves.*;

public final class ChangeStringFieldMoveCategory implements ChangeStringField {
    private final DamagingMoveData damagingMoveData;

    public ChangeStringFieldMoveCategory(DamagingMoveData _l) {
        this.damagingMoveData = _l;
    }

    @Override
    public String value() {
        return damagingMoveData.getCategory();
    }

    @Override
    public void value(String _v) {
        damagingMoveData.setCategory(_v);
    }
}
