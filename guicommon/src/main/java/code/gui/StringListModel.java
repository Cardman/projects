package code.gui;
import code.util.StringList;



public final class StringListModel extends AbListModel<String> {

    public StringListModel() {
    }

    @Override
    public int indexOfObj(String _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Object e_ = getObj(i);
            if (e_ == null) {
                continue;
            }
            if (StringList.quickEq(_element, (String) e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
}
