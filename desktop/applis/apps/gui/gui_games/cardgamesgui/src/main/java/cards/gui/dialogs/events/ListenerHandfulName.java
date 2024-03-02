package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogVaryingHandful;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerHandfulName implements ListSelection {

    private final DialogVaryingHandful dialog;

    public ListenerHandfulName(DialogVaryingHandful _dialog) {
        dialog = _dialog;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        dialog.validateHandful();
//    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        dialog.validateHandful();
    }

}
