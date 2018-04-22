package aiki.gui.components.listeners;
import javax.swing.JTextField;

import aiki.gui.components.ComboBoxSearchingMode;
import code.gui.AutoCompleteDocument;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.pagination.SearchingMode;

public class ChangedModeEvent extends ListSelection {

    private ComboBoxSearchingMode mode;

    private JTextField field;

    public ChangedModeEvent(ComboBoxSearchingMode _mode, JTextField _field) {
        mode = _mode;
        field = _field;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        SearchingMode s_ = mode.getCurrent();
//        AutoCompleteDocument.setMode(field, s_);
//    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        SearchingMode s_ = mode.getCurrent();
        AutoCompleteDocument.setMode(field, s_);
    }
}
