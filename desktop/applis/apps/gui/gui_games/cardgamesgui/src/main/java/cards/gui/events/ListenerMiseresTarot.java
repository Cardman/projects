package cards.gui.events;

import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Miseres;
import code.gui.AbsCustCheckBox;
import code.gui.events.AbsActionListener;

public class ListenerMiseresTarot implements AbsActionListener {

    private ContainerTarot container;
    private AbsCustCheckBox check;
    private Miseres miseres;
    public ListenerMiseresTarot(ContainerTarot _container, AbsCustCheckBox _check, Miseres _m) {
        container = _container;
        check = _check;
        miseres = _m;
    }

    @Override
    public void action() {
        container.getSelectedMiseres().put(miseres, check.isSelected());
    }
}
