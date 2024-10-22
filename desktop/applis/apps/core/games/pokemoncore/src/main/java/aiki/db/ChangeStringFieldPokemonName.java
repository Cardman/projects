package aiki.db;

import aiki.map.pokemon.*;

public final class ChangeStringFieldPokemonName implements ChangeStringField {
    private final Pokemon pokemon;

    public ChangeStringFieldPokemonName(Pokemon _l) {
        this.pokemon = _l;
    }

    @Override
    public String value() {
        return pokemon.getName();
    }

    @Override
    public void value(String _v) {
        pokemon.setName(_v);
    }
}
