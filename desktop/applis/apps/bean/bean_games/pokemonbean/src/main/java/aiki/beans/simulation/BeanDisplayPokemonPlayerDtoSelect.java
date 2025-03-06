package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.simulation.dto.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayPokemonPlayerDtoSelect extends BeanDisplayPokemonPlayerDto {

    public BeanDisplayPokemonPlayerDtoSelect(SimulationBean _b) {
        super(_b);
    }
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayerDto _info) {
        common(_rend, _info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectPkEvosValidation(getBean(), _info.getIndex()), MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_SELECT);
        _rend.feedParentsCts();
        return 8;
    }
}
