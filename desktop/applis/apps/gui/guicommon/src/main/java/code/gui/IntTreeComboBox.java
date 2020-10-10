package code.gui;
import code.util.*;
import code.util.TreeMap;
import code.util.comparators.ComparatorMapValue;

public final class IntTreeComboBox extends GraphicCombo {
//implements TranslatableComponent

    private static final String EMPTY_STRING = "";

//    private FacadeGame facade;

//    private List<T> order = new List<T>();

//    private Class<T> enumClass;

    private TreeMap<Integer,String> elements;

    private boolean withDefaultValue;
    public IntTreeComboBox(){
    }

    public IntTreeComboBox(Integer... _elements){
        for (Integer e: _elements) {
            addItem(e.toString());
        }
    }
    public IntTreeComboBox(TreeMap<Integer,String> _tr){
        elements = _tr;
    }

    protected TreeMap<Integer, String> getElements() {
        return elements;
    }

    public void refresh(AbsMap<Integer,String> _tr) {
        super.removeAllItems();
        IntMap<String> m_ = createMap(_tr);
        elements = new TreeMap<Integer,String>(new ComparatorMapValue<Integer>(m_));
        elements.putAllMap(m_);
        for (Integer e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
    }

    private IntMap<String> createMap(AbsMap<Integer,String> _tr) {
        IntMap<String> m_ = new IntMap<String>(_tr);
        if (withDefaultValue) {
            m_.put(null, EMPTY_STRING);
        }
        return m_;
    }

    @Override
    public void removeItem(int _anIndex) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        Integer e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        super.removeItem(_anIndex);
    }

    public void setItems(Integer... _numerosPlis) {
        removeAllItems();
        for (Integer i: _numerosPlis) {
            addItem(i.toString());
        }
        refresh(getTree(_numerosPlis));
    }
    private static IntTreeMap< String> getTree(Integer... _ints) {
        IntTreeMap< String> tr_;
        tr_ = new IntTreeMap< String>();
        for (Integer i: _ints) {
            tr_.put(i, i.toString());
        }
        return tr_;
    }
    @Override
    public void removeAllItems() {
        getElements().clear();
        super.removeAllItems();
    }
    public Integer getCurrent() {
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
