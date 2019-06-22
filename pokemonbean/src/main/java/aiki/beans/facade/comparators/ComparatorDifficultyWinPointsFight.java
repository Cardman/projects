package aiki.beans.facade.comparators;

import aiki.game.params.enums.DifficultyWinPointsFight;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorDifficultyWinPointsFight implements Comparing<DifficultyWinPointsFight> {
    @Override
    public int compare(DifficultyWinPointsFight _one, DifficultyWinPointsFight _two) {
        return Numbers.compareLg(_one.ordinal(),_two.ordinal());
    }
}
