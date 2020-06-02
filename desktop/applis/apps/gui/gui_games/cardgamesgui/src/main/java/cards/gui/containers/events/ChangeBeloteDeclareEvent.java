package cards.gui.containers.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.containers.ContainerBelote;

public class ChangeBeloteDeclareEvent implements ActionListener {

    private ContainerBelote container;

    public ChangeBeloteDeclareEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.invertBeloteDeclare();
    }
}
