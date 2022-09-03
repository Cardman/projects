package aiki.game.fight;

import code.util.AbsBasicTreeMap;
import code.util.core.BoolVal;
import code.util.ints.Comparing;

public final class EvolutionChoiceMap extends AbsBasicTreeMap<String,BoolVal> {
    private final Comparing<String> evoComparer;
    public EvolutionChoiceMap(Comparing<String> _cmp) {
        evoComparer = _cmp;
    }

    @Override
    protected int compare(String _one, String _two) {
        return evoComparer.compare(_one,_two);
    }
}
