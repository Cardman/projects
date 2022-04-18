package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.events.AbsActionListener;

public class ChangeRulesEvent implements AbsActionListener {

    private ContainerMulti container;

    public ChangeRulesEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.changeRules();
    }
}
