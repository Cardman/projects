package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.simulation.dto.*;
import code.util.StringList;
import code.util.core.*;

public class BeanDisplayPokemonTrainerDto implements BeanDisplayEltGrid<PokemonTrainerDto> {
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonTrainerDto _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getPkTrainer().getName()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getPkTrainer().getName());
        _rend.formatMessageDirCts(Long.toString(_info.getPkTrainer().getLevel()));
        _rend.formatMessageDirCts(_info.getPkTrainer().getAbility());
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedGenders().getVal(_info.getPkTrainer().getGender())));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedItems().getVal(_info.getPkTrainer().getItem())));
        StringList list_ = new StringList();
        for (String m: _info.getPkTrainer().getMoves()) {
            list_.add(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedMoves().getVal(m)));
        }
        list_.sort();
        _rend.formatMessageDirCts(StringUtil.join(list_," - "));
        return 7;
    }
}
