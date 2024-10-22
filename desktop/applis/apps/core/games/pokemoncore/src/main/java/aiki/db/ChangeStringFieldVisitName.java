package aiki.db;

import aiki.map.pokemon.Pokemon;

public final class ChangeStringFieldVisitName implements ChangeStringFieldVisit {
    @Override
    public ChangeStringField visit(Pokemon _pk) {
        return new ChangeStringFieldPokemonName(_pk);
    }
}
