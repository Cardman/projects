package cards.gui.containers.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.containers.ContainerMulti;

public class ChangePlaceEvent implements ActionListener {

    private ContainerMulti container;

    public ChangePlaceEvent(ContainerMulti _container) {
        container = _container;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        container.changePlace();
//    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.changePlace();
    }

}
