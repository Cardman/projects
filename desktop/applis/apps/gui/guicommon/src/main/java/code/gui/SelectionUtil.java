package code.gui;


public final class SelectionUtil {
    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicListable _grList, boolean _methodAction) {
        ListSelection listener_ = _grList.getListener();
        if (listener_ == null) {
            return;
        }
        int min_ = Math.min(_firstIndex, _lastIndex);
        int max_ = Math.max(_firstIndex, _lastIndex);
        SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
        listener_.valueChanged(ev_);
    }
    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicCombo _origin, ListSelection _list, boolean _methodAction) {
        if (_list != null) {
            int min_ = Math.min(_firstIndex, _lastIndex);
            int max_ = Math.max(_firstIndex, _lastIndex);
            SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
            _list.valueChanged(ev_);
        }
        _origin.update();
    }
}
