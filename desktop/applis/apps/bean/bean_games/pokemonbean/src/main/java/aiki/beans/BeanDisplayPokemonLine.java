package aiki.beans;

import aiki.beans.facade.dto.*;
import code.util.core.*;

public final class BeanDisplayPokemonLine implements BeanDisplayEltGrid<PokemonLine> {
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getName()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getKey());
        _rend.formatMessageDirCts(StringUtil.join(_info.getTypes()," - "));
        _rend.formatMessageDirCts(Long.toString(_info.getEvolutions()));
        return 4;
    }
}
