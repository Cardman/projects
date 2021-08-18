package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.SelectEgg;

public class ConsultEggEvent extends MouseAdapter {

    private WindowAiki window;

    private FacadeGame facade;

    public ConsultEggEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        int lineBack_ = facade.getLineEgg();
        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg());
        SelectEgg.setVisible(window.getSelectEgg());
        facade.setLineEggs(lineBack_);
        facade.clearSortingEgg();
    }
}
