package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;

public final class BeanDisplayPokemonPlayerDtoSelectMoves extends BeanDisplayPokemonPlayerDto {

    public BeanDisplayPokemonPlayerDtoSelectMoves(SimulationBean _b) {
        super(_b);
    }
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayerDto _info) {
        common(_rend, _info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectPkMovesValidation(getBean(), _info.getIndex()), MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_SELECT);
        _rend.feedParentsCts();
        return 8;
    }
}
