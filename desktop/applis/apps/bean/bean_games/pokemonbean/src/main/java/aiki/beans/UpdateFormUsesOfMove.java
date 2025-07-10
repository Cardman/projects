package aiki.beans;

import aiki.beans.simulation.*;
import aiki.game.*;
import code.util.*;

public final class UpdateFormUsesOfMove extends IntBeanActionPartForm{
    private final StringMap<UsesOfMove> uses;

    public UpdateFormUsesOfMove(SimulationBean _s, StringMap<UsesOfMove> _u) {
        super(_s);
        this.uses = _u;
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        getSimu().useFighterContent(uses, _form);
        return _form;
    }
}
