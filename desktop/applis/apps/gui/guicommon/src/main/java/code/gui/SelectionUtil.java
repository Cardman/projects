package code.gui;


public final class SelectionUtil {
    private SelectionUtil(){
    }
    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicComboGrInt _origin, ListSelection _list, boolean _methodAction, int _old) {
        if (_list != null && _old != _firstIndex) {
            int min_ = Math.min(_firstIndex, _lastIndex);
            int max_ = Math.max(_firstIndex, _lastIndex);
            SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
            _list.valueChanged(ev_);
        }
        tryUp(_origin);
    }

    public static void tryUp(GraphicComboGrInt _combo) {
        if (_combo instanceof GraphicComboGrIntBase) {
            ((GraphicComboGrIntBase) _combo).update();
        }
    }
}
