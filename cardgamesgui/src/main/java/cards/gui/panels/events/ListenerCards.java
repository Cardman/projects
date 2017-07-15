package cards.gui.panels.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.panels.ViewablePanelTricksHands;

public class ListenerCards implements ActionListener {

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
    public void actionPerformed(ActionEvent _e) {
        panel.changeCard();
    }
}
