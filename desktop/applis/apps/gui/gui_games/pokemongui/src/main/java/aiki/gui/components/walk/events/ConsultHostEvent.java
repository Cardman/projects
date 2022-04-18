package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.ConsultHosts;
import code.gui.events.AbsActionListener;

public class ConsultHostEvent implements AbsActionListener {

    private WindowAiki window;

    private FacadeGame facade;

    public ConsultHostEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void action() {
        ConsultHosts.setConsultHosts(window, facade);
    }
}
