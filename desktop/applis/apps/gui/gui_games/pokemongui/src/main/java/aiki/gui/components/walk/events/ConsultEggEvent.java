package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.dialogs.SelectEgg;

public class ConsultEggEvent extends MouseAdapter {

    private MainWindow window;

    private FacadeGame facade;

    public ConsultEggEvent(MainWindow _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        int lineBack_ = facade.getLineEgg();
        SelectEgg.setSelectEgg(window, facade);
        SelectEgg.setVisible();
        facade.setLineEggs(lineBack_);
        facade.clearSortingEgg();
    }
}
