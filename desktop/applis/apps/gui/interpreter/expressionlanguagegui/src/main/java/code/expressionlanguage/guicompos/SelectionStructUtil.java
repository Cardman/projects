package code.expressionlanguage.guicompos;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class SelectionStructUtil {
    private SelectionStructUtil(){
    }

    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicComboStruct _origin, ListSelection _list, boolean _methodAction) {
        if (_list != null) {
            int min_ = Math.min(_firstIndex, _lastIndex);
            int max_ = Math.max(_firstIndex, _lastIndex);
            SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
            _list.valueChanged(ev_);
        }
        _origin.update();
    }
}
