package aiki.db;

import aiki.map.pokemon.*;

public final class ChangeStringFieldPokemonItem implements ChangeStringField {
    private final Pokemon pokemon;

    public ChangeStringFieldPokemonItem(Pokemon _l) {
        this.pokemon = _l;
    }

    @Override
    public String value() {
        return pokemon.getItem();
    }

    @Override
    public void value(String _v) {
        pokemon.setItem(_v);
    }
}
