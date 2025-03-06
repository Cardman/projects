package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayPokemonPlayerDtoCrud extends BeanDisplayPokemonPlayerDto {

    public BeanDisplayPokemonPlayerDtoCrud(SimulationBean _b) {
        super(_b);
    }
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayerDto _info) {
        common(_rend, _info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectPkValidation(getBean(), TeamCrud.EDIT.getTeamCrudString(),_info.getIndex()), MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_EDIT);
        _rend.formatMessageAnc(new SimulationBeanSelectPkValidation(getBean(), TeamCrud.REMOVE.getTeamCrudString(),_info.getIndex()),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMOVE);
        _rend.feedParentsCts();
        return 8;
    }
}
