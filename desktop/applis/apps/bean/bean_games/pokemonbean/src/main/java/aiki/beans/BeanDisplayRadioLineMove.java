package aiki.beans;

import aiki.beans.facade.simulation.dto.*;
import aiki.beans.simulation.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayRadioLineMove implements BeanDisplayEltGrid<RadioLineMove> {
    private final SimulationBean bean;

    public BeanDisplayRadioLineMove(SimulationBean _b) {
        this.bean = _b;
    }

    @Override
    public int displayEltGrid(CommonBean _rend, RadioLineMove _info) {
        _rend.formatMessageDirCts(_info.getTranslatedKey().getTranslation());
        BeanDisplayMoveLine.displayLine(_rend,_info);
        _rend.initLine();
        _rend.formatMessageAnc(new SimulationBeanSelectMovesValidation(bean, _info.getIndex()), MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_VALIDATE_MOVE_TARGET);
        _rend.feedParentsCts();
        return 10;
    }
}
