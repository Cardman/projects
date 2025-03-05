package aiki.beans;

import aiki.beans.facade.dto.*;
import aiki.beans.simulation.*;
import code.util.core.StringUtil;

public final class BeanDisplayPokemonLineSimu implements BeanDisplayEltGrid<PokemonLine> {
    private final SelectPokemonBean select;

    public BeanDisplayPokemonLineSimu(SelectPokemonBean _s) {
        this.select = _s;
    }

    @Override
    public int displayEltGrid(CommonBean _rend, PokemonLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getName()));
        _rend.feedParentsCts();
        _rend.getBuilder().formatMessageDirCts(_info.getKey().getTranslation(),new SelectPokemonBeanClickLink(select,_info.getName()));
        _rend.formatMessageDirCts(StringUtil.join(_info.getTypes()," - "));
        _rend.formatMessageDirCts(Long.toString(_info.getEvolutions()));
        return 4;
    }
}
