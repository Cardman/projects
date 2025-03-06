package aiki.beans;

import aiki.beans.facade.dto.*;
import aiki.beans.simulation.*;
import code.util.core.*;

public final class BeanDisplayPokemonLineSimuPlayer implements BeanDisplayEltGrid<PokemonLine> {
    private final AddPokemonBean select;

    public BeanDisplayPokemonLineSimuPlayer(AddPokemonBean _s) {
        this.select = _s;
    }

    @Override
    public int displayEltGrid(CommonBean _rend, PokemonLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getName()));
        _rend.feedParentsCts();
        _rend.getBuilder().formatMessageDirCts(_info.getKey().getTranslation(),new AddPokemonBeanClickLink(select,_info.getName()));
        _rend.formatMessageDirCts(StringUtil.join(_info.getTypes()," - "));
        _rend.formatMessageDirCts(Long.toString(_info.getEvolutions()));
        return 4;
    }
}
