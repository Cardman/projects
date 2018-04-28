package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.beans.facade.solution.dto.WildPokemonDto;

public final class ComparatorWildPokemonDto implements Comparing<WildPokemonDto> {

    @Override
    public int compare(WildPokemonDto _o1, WildPokemonDto _o2) {
        int res_ = _o1.getName().compareTo(_o2.getName());
        if (res_ != 0) {
            return res_;
        }
        return _o1.getGender().compareTo(_o2.getGender());
    }

}