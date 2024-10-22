package aiki.db;

import aiki.fight.pokemon.evolution.*;

public final class ChangeStringFieldEvolutionItem implements ChangeStringField {
    private final EvolutionItem evolutionItem;

    public ChangeStringFieldEvolutionItem(EvolutionItem _l) {
        this.evolutionItem = _l;
    }

    @Override
    public String value() {
        return evolutionItem.getItem();
    }

    @Override
    public void value(String _v) {
        evolutionItem.setItem(_v);
    }
}
