package aiki.beans.simulation;

import aiki.beans.BeanDisplayEltGrid;
import aiki.beans.CommonBean;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.TeamCrud;
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.StringList;
import code.util.core.StringUtil;

public final class BeanDisplayPokemonPlayerDto implements BeanDisplayEltGrid<PokemonPlayerDto> {
    private final SimulationBean bean;

    public BeanDisplayPokemonPlayerDto(SimulationBean _b) {
        this.bean = _b;
    }
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayerDto _info) {
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
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectPkValidation(bean, TeamCrud.EDIT.getTeamCrudString(),_info.getIndex()), MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_EDIT);
        _rend.formatMessageAnc(new SimulationBeanSelectPkValidation(bean, TeamCrud.REMOVE.getTeamCrudString(),_info.getIndex()),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMOVE);
        _rend.feedParentsCts();
        return 8;
    }
}
