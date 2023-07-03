package code.gui;
import code.util.*;

public final class IntTreeComboBox extends AbsComboBox {
    //implements GraphicComboGrIntBase
//implements TranslatableComponent

//    private FacadeGame facade;

//    private List<T> order = new List<T>();

//    private Class<T> enumClass;

    private final IntMap<String> elements;

    public IntTreeComboBox(GraphicComboGrInt _combo){
        super(_combo);
        elements = new IntMap<String>();
    }

    public IntMap<String> getElements() {
        return elements;
    }

    public void refresh(AbsMap<Integer,String> _tr) {
        removeAllItems();
        elements.addAllEntries(_tr);
        for (EntryCust<Integer,String> e: _tr.entryList()) {
            addItem(e.getValue());
        }
    }

//    @Override
    public void removeItem(int _anIndex) {
        IntMap<String> tr_;
        tr_ = getElements();
        Integer e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        getCombo().removeItem(_anIndex);
    }

    public void setItems(int[] _numerosPlis) {
        removeAllItems();
        for (int i: _numerosPlis) {
            addItem(Integer.toString(i));
        }
        refresh(getTree(_numerosPlis));
    }
    private static IntTreeMap< String> getTree(int[] _ints) {
        IntTreeMap< String> tr_;
        tr_ = new IntTreeMap< String>();
        for (int i: _ints) {
            tr_.put(i, Integer.toString(i));
        }
        return tr_;
    }
//    @Override
    public void removeAllItems() {
        getElements().clear();
        getCombo().removeAllItems();
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
