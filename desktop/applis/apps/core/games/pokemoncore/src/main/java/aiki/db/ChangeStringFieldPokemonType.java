package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldPokemonType implements ChangeStringField {
    private final TypesDuo typesDuo;

    public ChangeStringFieldPokemonType(TypesDuo _l) {
        this.typesDuo = _l;
    }

    @Override
    public String value() {
        return typesDuo.getPokemonType();
    }

    @Override
    public void value(String _v) {
        typesDuo.setPokemonType(_v);
    }
}
