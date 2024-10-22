package aiki.db;

import aiki.map.pokemon.*;

public final class ChangeStringFieldPokemonAbility implements ChangeStringField {
    private final Pokemon pokemon;

    public ChangeStringFieldPokemonAbility(Pokemon _l) {
        this.pokemon = _l;
    }

    @Override
    public String value() {
        return pokemon.getAbility();
    }

    @Override
    public void value(String _v) {
        pokemon.setAbility(_v);
    }
}
