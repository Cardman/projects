package cards.gui.containers.events;
import cards.gui.containers.ContainerMulti;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ChangePlaceEvent implements ListSelection {

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
    public void valueChanged(SelectionInfo _e) {
        container.changePlace();
    }

}
