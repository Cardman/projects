package aiki.db;

import aiki.map.pokemon.Pokemon;

public final class ChangeStringFieldVisitItem implements ChangeStringFieldVisit {
    @Override
    public ChangeStringField visit(Pokemon _pk) {
        return new ChangeStringFieldPokemonItem(_pk);
    }
}
