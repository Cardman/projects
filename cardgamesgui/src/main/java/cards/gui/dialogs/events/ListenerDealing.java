package cards.gui.dialogs.events;
import cards.gui.dialogs.DialogTarot;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerDealing extends ListSelection {

    private DialogTarot dialog;

    public ListenerDealing(DialogTarot _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
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
