package aiki.beans;

import aiki.beans.simulation.*;
import aiki.map.pokemon.*;
import code.scripts.pages.aiki.*;

public class BeanDisplayPokemonPlayerSelect extends BeanDisplayPokemonPlayer {
    private final SimulationBean bean;
    private int increment;

    public BeanDisplayPokemonPlayerSelect(SimulationBean _b) {
        this.bean = _b;
    }

    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayer _info) {
        displayLine(_rend, _info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectPkEvosAfterValidation(bean, increment), MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_SELECT);
        _rend.feedParentsCts();
        increment++;
        return 12;
    }

}
