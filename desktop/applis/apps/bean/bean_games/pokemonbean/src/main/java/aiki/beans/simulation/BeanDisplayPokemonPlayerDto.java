package aiki.beans.simulation;

import aiki.beans.BeanDisplayEltGrid;
import aiki.beans.CommonBean;
import aiki.beans.facade.simulation.dto.*;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class BeanDisplayPokemonPlayerDto implements BeanDisplayEltGrid<PokemonPlayerDto> {
    private final SimulationBean bean;

    protected BeanDisplayPokemonPlayerDto(SimulationBean _b) {
        this.bean = _b;
    }

    protected void common(CommonBean _rend, PokemonPlayerDto _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getPokemon().getName()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getPokemon().getName());
        _rend.formatMessageDirCts(Long.toString(_info.getPokemon().getLevel()));
        _rend.formatMessageDirCts(_info.getPokemon().getAbility());
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedGenders().getVal(_info.getPokemon().getGender())));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedItems().getVal(_info.getPokemon().getItem())));
        StringList list_ = new StringList();
        for (String m: _info.getMoves()) {
            list_.add(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedMoves().getVal(m)));
        }
        list_.sort();
        _rend.formatMessageDirCts(StringUtil.join(list_," - "));
    }

    public SimulationBean getBean() {
        return bean;
    }
}
