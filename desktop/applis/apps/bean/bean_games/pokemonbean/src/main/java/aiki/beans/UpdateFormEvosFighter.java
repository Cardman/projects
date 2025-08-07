package aiki.beans;

import aiki.beans.simulation.*;
import aiki.game.fight.*;

public final class UpdateFormEvosFighter extends IntBeanActionPartForm{
    private final Fighter fighter;
    private final SimulationFighterForm form;

    public UpdateFormEvosFighter(SimulationBean _s, SimulationFighterForm _f, Fighter _u) {
        super(_s);
        this.form = _f;
        this.fighter = _u;
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        form.evosContent(fighter, _form);
        return _form;
    }
}
