package code.expressionlanguage.guicompos;

import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.core.NumberUtil;

public final class SelectionStructUtil {
    private SelectionStructUtil(){
    }

//    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicComboStruct _origin, ListSelection _list, boolean _methodAction) {
//        selectEvent(_firstIndex, _lastIndex, _list, _methodAction);
//        _origin.update();
//    }

    public static void selectEvent(int _firstIndex, int _lastIndex, ListSelection _list, boolean _methodAction) {
        if (_list != null) {
            int min_ = NumberUtil.min(_firstIndex, _lastIndex);
            int max_ = NumberUtil.max(_firstIndex, _lastIndex);
            SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
            _list.valueChanged(ev_);
        }
    }
}
