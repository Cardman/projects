package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldTypeDamageBoost implements ChangeStringField {
    private final TypeDamageBoost typeDamageBoost;

    public ChangeStringFieldTypeDamageBoost(TypeDamageBoost _l) {
        this.typeDamageBoost = _l;
    }

    @Override
    public String value() {
        return typeDamageBoost.getType();
    }

    @Override
    public void value(String _v) {
        typeDamageBoost.setType(_v);
    }
}
