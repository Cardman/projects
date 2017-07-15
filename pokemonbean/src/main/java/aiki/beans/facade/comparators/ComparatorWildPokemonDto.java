package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.solution.dto.WildPokemonDto;

public class ComparatorWildPokemonDto implements Comparator<WildPokemonDto> {

    @Override
    public int compare(WildPokemonDto _o1, WildPokemonDto _o2) {
        int res_ = _o1.getName().compareTo(_o2.getName());
        if (res_ != 0) {
            return res_;
        }
        return _o1.getGender().compareTo(_o2.getGender());
    }

}
