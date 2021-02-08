package code.gui;
import code.util.*;
import code.util.comparators.ComparatorIndexes;
import code.util.comparators.ComparatorMapValue;
import code.util.ints.Listable;

public final class ComboBox<T> extends AbsComboBox implements GraphicComboGrIntBase {

    private static final String EMPTY_STRING = "";

    private final EnumList<T> order = new EnumList<T>();

    private TreeMap<T,String> elements;

    private boolean withDefaultValue;

    public ComboBox(GraphicComboGrInt _combo) {
        super(_combo);
    }
    public TreeMap<T, String> getElements() {
        return elements;
    }

    public void refresh(Listable<T> _order,AbsMap<T,String> _tr) {
        order.clear();
        order.addAllElts(_order);
        getCombo().removeAllItems();
        elements = new TreeMap<T,String>(new ComparatorIndexes<T>(order));
        EnumMap<T,String> m_ = createMap(_tr);
        elements.putAllMap(m_);
        for (T e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
    }

    public void refresh(AbsMap<T,String> _tr) {
        getCombo().removeAllItems();
        EnumMap<T,String> m_ = createMap(_tr);
        elements = new TreeMap<T,String>(new ComparatorMapValue<T>(m_));
        elements.putAllMap(m_);
        for (T e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
    }

    private EnumMap<T,String> createMap(AbsMap<T,String> _tr) {
        EnumMap<T,String> m_ = new EnumMap<T,String>(_tr);
        if (withDefaultValue) {
            m_.put(null, EMPTY_STRING);
        }
        return m_;
    }

    @Override
    public void removeItem(int _anIndex) {
        TreeMap<T, String> tr_;
        tr_ = getElements();
        if (!tr_.isValidIndex(_anIndex)) {
            return;
        }
        T e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        getCombo().removeItem(_anIndex);
    }

    public void addItem(T _t, String _dis) {
        TreeMap<T, String> tr_;
        tr_ = getElements();
        tr_.put(_t, _dis);
        addItem(_dis);
    }
    public void setSelectedItem(T _t) {
        int i_ = 0;
        for (EntryCust<T, String> e: getElements().entryList()) {
            if (e.getKey() == _t) {
                selectItem(i_);
                return;
            }
            i_++;
        }
    }
    @Override
    public void removeAllItems() {
        getElements().clear();
        getCombo().removeAllItems();
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

    public void setWithDefaultValue(boolean _withDefaultValue) {
        withDefaultValue = _withDefaultValue;
    }

    public boolean isWithDefaultValue() {
        return withDefaultValue;
    }
}
