package cards.gui.dialogs.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.dialogs.DialogTarot;

public class ListenerDealing implements ActionListener {

    private DialogTarot dialog;

    public ListenerDealing(DialogTarot _dialog) {
        dialog = _dialog;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        dialog.validateDealingRules();
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        dialog.validateDealingRules();
//    }

}
