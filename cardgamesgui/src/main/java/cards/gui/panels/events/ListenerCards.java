package cards.gui.panels.events;
import cards.gui.panels.ViewablePanelTricksHands;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerCards implements ListSelection {

    private ViewablePanelTricksHands panel;

    public ListenerCards(ViewablePanelTricksHands _panel) {
        panel = _panel;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        panel.changeCard();
//    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        panel.changeCard();
    }
}
