package code.gui;



public final class EnumListModel<T extends Enum<T>> extends AbListModel<T> {

    public EnumListModel() {
    }

    @Override
    public int indexOfObj(T _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            if (getObj(i) == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
}
