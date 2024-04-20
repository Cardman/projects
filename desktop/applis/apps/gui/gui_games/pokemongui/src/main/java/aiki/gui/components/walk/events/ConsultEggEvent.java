package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.SelectEgg;
import code.gui.events.AbsActionListener;

public class ConsultEggEvent implements AbsActionListener {

    private final WindowAiki window;

    private final FacadeGame facade;

    public ConsultEggEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void action() {
        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg(), true);
    }

    public static void consult(int _lineBack, FacadeGame _facade) {
        _facade.setLineEggs(_lineBack);
        _facade.clearSortingEgg();
    }
}
