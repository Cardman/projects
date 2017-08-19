package code.gui;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import code.util.NumberMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorMapValue;
import code.util.comparators.NaturalComparator;
import code.util.ints.ListableEntries;

public final class IntTreeComboBox extends JComboBox {
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
            addItem(e);
        }
    }
    public IntTreeComboBox(TreeMap<Integer,String> _tr){
        elements = _tr;    }

    protected TreeMap<Integer, String> getElements() {
        return elements;
    }
//    public ComboBox(Class<T> _class){
//        enumClass = _class;
//    }

//    protected FacadeGame getFacade() {
//        return facade;
//    }
//
//    public void setFacade(FacadeGame _facade) {
//        facade = _facade;
//    }

//    @Override
//    public void translate() {
//        List<T> elements_ = new List<T>();
//        for (T t: enumClass.getEnumConstants()) {
//            elements_.add(t);
//        }
//        refresh(elements_, Constants.getLanguage(), true);
//    }

//    public abstract void refresh(List<T> _order, String _language, boolean _sortStrings);

//    public void refresh(Listable<T> _order,ListableEntries<T,String> _tr) {
//        order.clear();
//        order.addAllElts(_order);
//        super.removeAllItems();
////        Map<T,String> m_ = new Map<T,String>(_tr);
////        elements = new TreeMap<T,String>(new Comparator<T>(){
////            @Override
////            public int compare(T _o1, T _o2) {
////                return order.indexOfObj(_o1) - order.indexOfObj(_o2);
////            }
////        });
//        elements = new TreeMap<T,String>(new ComparatorIndexes<T>(order));
//        Map<T,String> m_ = createMap(_tr);
//        elements.putAllMap(m_);
//        for (T e: elements.getKeys()) {
//            addItem(_tr.getVal(e));
//        }
//    }

    public void refresh(ListableEntries<Integer,String> _tr) {
        super.removeAllItems();
        NumberMap<Integer,String> m_ = createMap(_tr);
        elements = new TreeMap<Integer,String>(new ComparatorMapValue<Integer>(m_));
        elements.putAllMap(m_);
        for (Integer e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
    }

    private NumberMap<Integer,String> createMap(ListableEntries<Integer,String> _tr) {
        NumberMap<Integer,String> m_ = new NumberMap<Integer,String>(_tr);
        if (withDefaultValue) {
            m_.put(null, EMPTY_STRING);
        }
        return m_;
    }

    @Override
    public void removeItemAt(int _anIndex) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        Integer e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        super.removeItemAt(_anIndex);
    }

    public void setModel(Integer... _numerosPlis) {
        super.setModel(new DefaultComboBoxModel(_numerosPlis));
        refresh(getTree(_numerosPlis));
    }
    private static TreeMap<Integer, String> getTree(Integer... _ints) {
        TreeMap<Integer, String> tr_;
        tr_ = new TreeMap<Integer, String>(new NaturalComparator<Integer>());
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
