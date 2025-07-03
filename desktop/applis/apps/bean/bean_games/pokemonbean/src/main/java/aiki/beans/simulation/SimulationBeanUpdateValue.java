package aiki.beans.simulation;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;

public final class SimulationBeanUpdateValue implements IntBeanAction {
    private final ListActivityOfMoves list;
    private final int index;
    private final IntBeanChgValue<ActivityOfMove> input;
    public SimulationBeanUpdateValue(ListActivityOfMoves _ls, int _i, IntBeanChgValue<ActivityOfMove> _ch) {
        list = _ls;
        index = _i;
        input = _ch;
    }

    @Override
    public String actionBean() {
        list.set(index,new ListActivityOfMove(list.get(index).getList(),input.genericValue()));
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
