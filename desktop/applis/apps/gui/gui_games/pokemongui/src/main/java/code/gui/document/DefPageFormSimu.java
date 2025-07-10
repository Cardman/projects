package code.gui.document;

import aiki.beans.*;
import aiki.beans.simulation.*;
import code.gui.*;

public class DefPageFormSimu extends PageFormSimu {
    private final AbsPanel panel;
    private final BeanBuilderHelper helper;

    public DefPageFormSimu(SimulationBean _s, AbsPanel _p, BeanBuilderHelper _h) {
        super(_s);
        panel = _p;
        helper = _h;
    }

    @Override
    public SimulationBean init() {
        panel.removeAll();
        helper.getStack().clear();
        helper.getStack().add(panel);
        return super.init();
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
