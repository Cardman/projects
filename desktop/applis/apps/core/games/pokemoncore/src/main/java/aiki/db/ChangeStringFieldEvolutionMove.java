package aiki.db;

import aiki.fight.pokemon.evolution.*;

public final class ChangeStringFieldEvolutionMove implements ChangeStringField {
    private final EvolutionMove evolutionMove;

    public ChangeStringFieldEvolutionMove(EvolutionMove _l) {
        this.evolutionMove = _l;
    }

    @Override
    public String value() {
        return evolutionMove.getMove();
    }

    @Override
    public void value(String _v) {
        evolutionMove.setMove(_v);
    }
}
