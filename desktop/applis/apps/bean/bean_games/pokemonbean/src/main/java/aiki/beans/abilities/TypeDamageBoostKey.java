package aiki.beans.abilities;

import aiki.beans.*;
import code.maths.*;

public final class TypeDamageBoostKey {

    private final TranslatedKey type;

    private final Rate boost;

    public TypeDamageBoostKey(TranslatedKey _type, Rate _boost) {
        type = _type;
        boost = _boost;
    }

    public TranslatedKey getType() {
        return type;
    }

    public Rate getBoost() {
        return boost;
    }
}
