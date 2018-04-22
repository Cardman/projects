package cards.gui.dialogs.events;
import cards.gui.dialogs.DialogTarot;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerHandfulName extends ListSelection {

    private DialogTarot dialog;

    public ListenerHandfulName(DialogTarot _dialog) {
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
