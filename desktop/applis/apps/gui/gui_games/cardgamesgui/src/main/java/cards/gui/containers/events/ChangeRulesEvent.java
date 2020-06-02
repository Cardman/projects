package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMulti;

public class ChangeRulesEvent extends MouseAdapter {

    private ContainerMulti container;

    public ChangeRulesEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.changeRules();
    }
}
