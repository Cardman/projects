package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.SelectEgg;
import code.gui.events.AbsActionListener;

public class ConsultEggEvent implements AbsActionListener {

    private WindowAiki window;

    private FacadeGame facade;

    public ConsultEggEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void action() {
        int lineBack_ = facade.getLineEgg();
        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg());
        SelectEgg.setVisible(window.getSelectEgg());
        facade.setLineEggs(lineBack_);
        facade.clearSortingEgg();
    }
}
