package aiki.game.fight;

import code.util.AbsComparerTreeMap;
import code.util.core.BoolVal;
import code.util.ints.Comparing;

public final class EvolutionChoiceMap extends AbsComparerTreeMap<String,BoolVal> {
    public EvolutionChoiceMap(Comparing<String> _cmp) {
        super(_cmp);
    }

}
