package aiki.gui.components.listeners;

import code.gui.AutoCompleteDocument;
import code.gui.ComboBox;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import aiki.facade.enums.SearchingMode;

public class ChangedModeEvent implements ListSelection {

    private ComboBox<SearchingMode> mode;

    private AutoCompleteDocument field;

    public ChangedModeEvent(ComboBox<SearchingMode> _mode, AutoCompleteDocument _field) {
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
        field.setMode(s_ == SearchingMode.WHOLE_STRING);
    }
}
