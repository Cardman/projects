package aiki.beans.facade.comparators;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorWildPokemonDto implements Comparing<WildPokemonDto> {

    @Override
    public int compare(WildPokemonDto _o1, WildPokemonDto _o2) {
        int res_ = StringUtil.compareStrings(_o1.getName(),_o2.getName());
        if (res_ != 0) {
            return res_;
        }
        return StringUtil.compareStrings(_o1.getGender(),_o2.getGender());
    }

}