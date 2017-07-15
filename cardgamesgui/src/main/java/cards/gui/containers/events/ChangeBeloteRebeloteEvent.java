package cards.gui.containers.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.containers.ContainerBelote;

public class ChangeBeloteRebeloteEvent implements ActionListener {

    private ContainerBelote container;

    public ChangeBeloteRebeloteEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.invertBeloteRebelote();
    }
}
