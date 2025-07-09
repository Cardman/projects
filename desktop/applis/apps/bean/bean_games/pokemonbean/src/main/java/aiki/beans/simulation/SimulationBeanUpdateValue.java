package aiki.beans.simulation;

import aiki.beans.*;
import aiki.db.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.util.*;

public final class SimulationBeanUpdateValue implements IntBeanAction {
    private final ListActivityOfMoves list;
    private final StringList key;
    private final IntBeanChgValue<ActivityOfMove> input;
    public SimulationBeanUpdateValue(ListActivityOfMoves _ls, StringList _k, IntBeanChgValue<ActivityOfMove> _ch) {
        list = _ls;
        key = _k;
        input = _ch;
    }

    @Override
    public String actionBean() {
        list.set(list.index(key),new ListActivityOfMove(key,input.genericValue()));
        return DataBase.EMPTY_STRING;
    }
}
