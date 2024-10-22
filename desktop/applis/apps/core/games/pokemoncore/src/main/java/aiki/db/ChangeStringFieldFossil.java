package aiki.db;

import aiki.fight.items.*;

public final class ChangeStringFieldFossil implements ChangeStringField {
    private final Fossil fossil;

    public ChangeStringFieldFossil(Fossil _l) {
        this.fossil = _l;
    }

    @Override
    public String value() {
        return fossil.getPokemon();
    }

    @Override
    public void value(String _v) {
        fossil.setPokemon(_v);
    }
}
