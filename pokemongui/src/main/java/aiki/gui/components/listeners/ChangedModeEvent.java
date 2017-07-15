package aiki.gui.components.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import code.gui.AutoCompleteDocument;
import code.util.pagination.SearchingMode;
import aiki.gui.components.ComboBoxSearchingMode;

public class ChangedModeEvent implements ActionListener {

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
    public void actionPerformed(ActionEvent _e) {
        SearchingMode s_ = mode.getCurrent();
        AutoCompleteDocument.setMode(field, s_);
    }
}
