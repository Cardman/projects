package aiki.db;

import aiki.fight.abilities.*;

public final class ChangeStringFieldTypeForMoves implements ChangeStringField {
    private final AbilityData abilityData;

    public ChangeStringFieldTypeForMoves(AbilityData _l) {
        this.abilityData = _l;
    }

    @Override
    public String value() {
        return abilityData.getTypeForMoves();
    }

    @Override
    public void value(String _v) {
        abilityData.setTypeForMoves(_v);
    }
}
