package code.gui;
import code.util.*;
import code.util.TreeMap;
import code.util.comparators.ComparatorMapValue;

public final class IntTreeComboBox extends AbsComboBox {
    //implements GraphicComboGrIntBase
//implements TranslatableComponent

    private static final String EMPTY_STRING = "";

//    private FacadeGame facade;

//    private List<T> order = new List<T>();

//    private Class<T> enumClass;

    private TreeMap<Integer,String> elements;

    private boolean withDefaultValue;

    public IntTreeComboBox(GraphicComboGrInt _combo){
        super(_combo);
    }

    private static StringList str(Integer... _elements) {
        StringList ls_ = new StringList();
        for (Integer i: _elements) {
            ls_.add(Integer.toString(i));
        }
        return ls_;
    }
    protected TreeMap<Integer, String> getElements() {
        return elements;
    }

    public void refresh(AbsMap<Integer,String> _tr) {
        getCombo().removeAllItems();
        IntMap<String> m_ = createMap(_tr);
        elements = new TreeMap<Integer,String>(new ComparatorMapValue<Integer>(m_));
        elements.putAllMap(m_);
        for (Integer e: elements.getKeys()) {
            getCombo().addItem(_tr.getVal(e));
        }
    }

    private IntMap<String> createMap(AbsMap<Integer,String> _tr) {
        IntMap<String> m_ = new IntMap<String>(_tr);
        if (withDefaultValue) {
            m_.put(null, EMPTY_STRING);
        }
        return m_;
    }

//    @Override
    public void removeItem(int _anIndex) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        Integer e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        getCombo().removeItem(_anIndex);
    }

    public void setItems(int[] _numerosPlis) {
        removeAllItems();
        for (int i: _numerosPlis) {
            getCombo().addItem(Integer.toString(i));
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
    public Integer getCurrent() {
        int index_ = getCombo().getSelectedIndex();
        if (index_ < 0) {
            return null;
        }
        return elements.getKey(index_);
    }
    public boolean isSelectNullCurrent() {
        int index_ = getCombo().getSelectedIndex();
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
