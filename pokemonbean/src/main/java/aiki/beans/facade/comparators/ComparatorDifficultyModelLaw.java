package aiki.beans.facade.comparators;

import aiki.beans.PokemonStandards;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorDifficultyModelLaw implements Comparing<String> {
    @Override
    public int compare(String _one, String _two) {
        return Numbers.compareLg(PokemonStandards.getModelByName(_one).ordinal(),PokemonStandards.getModelByName(_two).ordinal());
    }
}
