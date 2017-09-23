package code.gui;
import javax.swing.JComboBox;

import code.util.TreeMap;

public abstract class TreeComboBox<T> extends JComboBox {
//implements TranslatableComponent

//    private static final String EMPTY_STRING = "";

//    private FacadeGame facade;

//    private List<T> order = new List<T>();

//    private Class<T> enumClass;

    private TreeMap<T,String> elements;

    private boolean withDefaultValue;
    public TreeComboBox(){
    }

    public TreeComboBox(TreeMap<T,String> _tr){
        elements = _tr;    }

    protected TreeMap<T, String> getElements() {
        return elements;
    }

    @Override
    public void removeItemAt(int _anIndex) {
        TreeMap<T, String> tr_;
        tr_ = getElements();
        T e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        super.removeItemAt(_anIndex);
    }

    @Override
    public void removeAllItems() {
        getElements().clear();
        super.removeAllItems();
    }
    public T getCurrent() {
        int index_ = getSelectedIndex();
        if (index_ < 0) {
            return null;
        }
        return elements.getKey(index_);
    }
    public boolean isSelectNullCurrent() {
        int index_ = getSelectedIndex();
        if (index_ < 0) {
            return false;
        }
        return elements.getKey(index_) == null;
    }

//    public void setCurrent(T _current) {
//        List<T> keys_ = new List<T>(elements.getKeys());
//        int index_ = keys_.indexOfObj(_current);
//        if (index_ < 0) {
//            return;
//        }
//        setSelectedIndex(index_);
//    }

    public void setWithDefaultValue(boolean _withDefaultValue) {
        withDefaultValue = _withDefaultValue;
    }

    public boolean isWithDefaultValue() {
        return withDefaultValue;
    }
}
