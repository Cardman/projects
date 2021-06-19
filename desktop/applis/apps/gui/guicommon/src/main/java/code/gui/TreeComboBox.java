package code.gui;
import code.util.AbsMap;

public abstract class TreeComboBox<T> extends AbsComboBox {
//implements TranslatableComponent
    // implements GraphicComboGrIntBase

//    private static final String EMPTY_STRING = "";

//    private FacadeGame facade;

//    private List<T> order = new List<T>();

//    private Class<T> enumClass;

    private final AbsMap<T,String> elements;

    protected TreeComboBox(AbsMap<T, String> _tr, GraphicComboGrInt _combo){
        super(_combo);
        elements = _tr;
    }

    protected AbsMap<T, String> getElements() {
        return elements;
    }

//    @Override
//    public void removeItem(int _anIndex) {
//        AbsMap<T, String> tr_;
//        tr_ = getElements();
//        T e_ = tr_.getKey(_anIndex);
//        tr_.removeKey(e_);
//        getCombo().removeItem(_anIndex);
//    }

//    @Override
    public void removeAllItems() {
        getElements().clear();
        getCombo().removeAllItems();
    }
    public T getCurrent() {
        int index_ = getSelectedIndex();
        if (index_ < 0) {
            return null;
        }
        return getElements().getKey(index_);
    }

//    public void setCurrent(T _current) {
//        List<T> keys_ = new List<T>(elements.getKeys());
//        int index_ = keys_.indexOfObj(_current);
//        if (index_ < 0) {
//            return;
//        }
//        setSelectedIndex(index_);
//    }

}
