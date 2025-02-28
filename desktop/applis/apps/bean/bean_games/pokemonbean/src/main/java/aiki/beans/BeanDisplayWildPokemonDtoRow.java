package aiki.beans;

import aiki.beans.facade.solution.dto.*;

public final class BeanDisplayWildPokemonDtoRow implements BeanDisplayEltGrid<WildPokemonDto> {
    @Override
    public int displayEltGrid(CommonBean _rend, WildPokemonDto _info) {
        _rend.initLine();
        _rend.addImg(_info.getImage());
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getName());
        _rend.formatMessageDirCts(_info.getGender());
        return 3;
    }

}
