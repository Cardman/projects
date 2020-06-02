package cards.gui.containers.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.containers.ContainerMulti;

public class ReadyEvent implements ActionListener {

    private ContainerMulti container;

    public ReadyEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.changeReady();
    }
}
