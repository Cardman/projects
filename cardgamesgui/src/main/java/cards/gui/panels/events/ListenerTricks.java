package cards.gui.panels.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.panels.ViewablePanelTricksHands;

public class ListenerTricks implements ActionListener {

    private ViewablePanelTricksHands panel;

    public ListenerTricks(ViewablePanelTricksHands _panel) {
        panel = _panel;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        panel.changeTrick();
//    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        panel.changeTrick();
    }
}
