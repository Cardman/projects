package aiki.beans;

import aiki.beans.simulation.*;
import aiki.game.*;
import code.util.*;

public final class UpdateFormUsesOfMove extends IntBeanActionPartForm{
    private final StringMap<UsesOfMove> uses;
    private final String key;
    private final SimulationFighterForm form;

    public UpdateFormUsesOfMove(SimulationBean _s, SimulationFighterForm _f, StringMap<UsesOfMove> _u, String _k) {
        super(_s);
        this.uses = _u;
        this.key = _k;
        this.form = _f;
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        form.useFighterContent(uses, _form, key);
        return _form;
    }
}
