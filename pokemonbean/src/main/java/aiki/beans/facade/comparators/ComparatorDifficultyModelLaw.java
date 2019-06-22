package aiki.beans.facade.comparators;

import aiki.game.params.enums.DifficultyModelLaw;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorDifficultyModelLaw implements Comparing<DifficultyModelLaw> {
    @Override
    public int compare(DifficultyModelLaw _one, DifficultyModelLaw _two) {
        return Numbers.compareLg(_one.ordinal(),_two.ordinal());
    }
}
