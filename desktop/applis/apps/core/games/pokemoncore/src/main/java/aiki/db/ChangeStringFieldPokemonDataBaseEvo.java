package aiki.db;

import aiki.fight.pokemon.*;

public final class ChangeStringFieldPokemonDataBaseEvo implements ChangeStringField {
    private final PokemonData pokemonData;

    public ChangeStringFieldPokemonDataBaseEvo(PokemonData _l) {
        this.pokemonData = _l;
    }

    @Override
    public String value() {
        return pokemonData.getBaseEvo();
    }

    @Override
    public void value(String _v) {
        pokemonData.setBaseEvo(_v);
    }
}
