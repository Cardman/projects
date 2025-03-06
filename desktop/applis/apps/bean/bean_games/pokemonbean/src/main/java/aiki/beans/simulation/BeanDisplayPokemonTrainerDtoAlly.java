package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayPokemonTrainerDtoAlly extends BeanDisplayPokemonTrainerDto {
    private final SimulationBean bean;

    public BeanDisplayPokemonTrainerDtoAlly(SimulationBean _b) {
        this.bean = _b;
    }

    @Override
    public int displayEltGrid(CommonBean _rend, PokemonTrainerDto _info) {
        int index_ = super.displayEltGrid(_rend, _info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectAllyPkValidation(bean, TeamCrud.EDIT.getTeamCrudString(),_info.getIndex()), MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EDIT);
        _rend.formatMessageAnc(new SimulationBeanSelectAllyPkValidation(bean, TeamCrud.REMOVE.getTeamCrudString(),_info.getIndex()),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMOVE);
        _rend.feedParentsCts();
        return index_ +1;
    }
}
