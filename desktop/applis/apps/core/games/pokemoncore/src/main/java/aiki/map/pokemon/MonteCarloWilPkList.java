package aiki.map.pokemon;

import aiki.map.levels.AreaApparition;
import code.maths.montecarlo.*;
import code.util.*;

public final class MonteCarloWilPkList extends MonteCarloList<CustList<WildPk>> {

    @Override
    protected boolean matchesEvent(CustList<WildPk> _one, CustList<WildPk> _two) {
        return AreaApparition.eqList(_one, _two);
    }
}
