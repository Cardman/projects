package aiki.db;

import aiki.fight.pokemon.evolution.*;

public final class ChangeStringFieldEvolutionStone implements ChangeStringField {
    private final EvolutionStone evolutionStone;

    public ChangeStringFieldEvolutionStone(EvolutionStone _l) {
        this.evolutionStone = _l;
    }

    @Override
    public String value() {
        return evolutionStone.getStone();
    }

    @Override
    public void value(String _v) {
        evolutionStone.setStone(_v);
    }
}
