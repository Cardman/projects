package aiki.beans;

import aiki.beans.simulation.*;

public final class UpdateFormEvosChoices extends IntBeanActionPartForm{

    public UpdateFormEvosChoices(SimulationBean _s) {
        super(_s);
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        getSimu().evosChoicesContent(_form);
        return _form;
    }
}
