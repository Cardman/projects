package aiki.beans;

import aiki.beans.simulation.*;

public final class UpdateFormAllyChoices extends IntBeanActionPartForm{

    public UpdateFormAllyChoices(SimulationBean _s) {
        super(_s);
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        getSimu().getFightForm().allyChoicesContent(_form);
        return _form;
    }
}
