package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.dialogs.ConsultHosts;

public class ConsultHostEvent extends MouseAdapter {

    private MainWindow window;

    private FacadeGame facade;

    public ConsultHostEvent(MainWindow _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        facade.setSelectedHostedPokemon(true);
        ConsultHosts.setConsultHosts(window, facade);
//        ConsultHosts consult_ = new ConsultHosts(window, facade);
//        consult_.setVisible(true);
        facade.setSelectedHostedPokemon(false);
    }
}
