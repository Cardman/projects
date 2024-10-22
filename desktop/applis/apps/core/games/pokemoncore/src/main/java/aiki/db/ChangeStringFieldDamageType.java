package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldDamageType implements ChangeStringField {
    private final TypesDuo typesDuo;

    public ChangeStringFieldDamageType(TypesDuo _l) {
        this.typesDuo = _l;
    }

    @Override
    public String value() {
        return typesDuo.getDamageType();
    }

    @Override
    public void value(String _v) {
        typesDuo.setDamageType(_v);
    }
}
