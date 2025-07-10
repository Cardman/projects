package aiki.beans;

import aiki.beans.simulation.*;
import aiki.game.fight.*;

public final class UpdateFormEvosFighter extends IntBeanActionPartForm{
    private final Fighter fighter;

    public UpdateFormEvosFighter(SimulationBean _s, Fighter _u) {
        super(_s);
        this.fighter = _u;
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        getSimu().evosContent(fighter, _form);
        return _form;
    }
}
