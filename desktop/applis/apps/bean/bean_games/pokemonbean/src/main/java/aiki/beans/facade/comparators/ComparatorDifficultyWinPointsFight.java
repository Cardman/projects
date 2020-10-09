package aiki.beans.facade.comparators;

import aiki.beans.PokemonStandards;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class ComparatorDifficultyWinPointsFight implements Comparing<String> {
    @Override
    public int compare(String _one, String _two) {
        return NumberUtil.compareLg(PokemonStandards.getDiffWonPtsByName(_one).ordinal(),PokemonStandards.getDiffWonPtsByName(_two).ordinal());
    }
}
