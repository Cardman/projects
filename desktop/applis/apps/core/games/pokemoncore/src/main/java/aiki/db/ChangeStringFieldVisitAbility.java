package aiki.db;

import aiki.map.pokemon.Pokemon;

public final class ChangeStringFieldVisitAbility implements ChangeStringFieldVisit {
    @Override
    public ChangeStringField visit(Pokemon _pk) {
        return new ChangeStringFieldPokemonAbility(_pk);
    }
}
