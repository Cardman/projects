package aiki.beans.facade.comparators;

import aiki.beans.PokemonStandards;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorDifficultyWinPointsFight implements Comparing<String> {
    @Override
    public int compare(String _one, String _two) {
        return Numbers.compareLg(PokemonStandards.getDiffWonPtsByName(_one).ordinal(),PokemonStandards.getDiffWonPtsByName(_two).ordinal());
    }
}
