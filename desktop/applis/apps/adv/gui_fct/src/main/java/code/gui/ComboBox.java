package code.gui;
import code.util.*;
import code.util.comparators.ComparatorIndexes;
import code.util.comparators.ComparatorMapValue;
import code.util.ints.Listable;

public final class ComboBox<T> extends AbsComboBox {
// implements GraphicComboGrIntBase

    private final IdList<T> order = new IdList<T>();

    private TreeMap<T,String> elements = new TreeMap<T,String>(new ComparatorIndexes<T>(order));

    public ComboBox(ScrollCustomCombo _combo) {
        super(_combo);
    }
    TreeMap<T, String> getElements() {
        return elements;
    }

    public void refresh(Listable<T> _order,AbsMap<T,String> _tr) {
        order.clear();
        order.addAllElts(_order);
        removeAllItems();
        elements = new TreeMap<T,String>(new ComparatorIndexes<T>(order));
        elements.putAllMap(_tr);
        for (T e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
        getCombo().repaint();
    }

    public void refresh(AbsMap<T,String> _tr) {
        removeAllItems();
        elements = new TreeMap<T,String>(new ComparatorMapValue<T>(_tr));
        elements.putAllMap(_tr);
        for (T e: elements.getKeys()) {
            addItem(_tr.getVal(e));
        }
        getCombo().repaint();
    }

//    @Override
    public void removeItem(int _anIndex) {
        TreeMap<T, String> tr_;
        tr_ = getElements();
        if (!tr_.isValidIndex(_anIndex)) {
            return;
        }
        T e_ = tr_.getKey(_anIndex);
        tr_.removeKey(e_);
        getCombo().remove(_anIndex);
    }

    public void addItem(T _t, String _dis) {
        getElements().put(_t, _dis);
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
//    @Override
    public void removeAllItems() {
        getElements().clear();
        getCombo().clear();
    }
    public T getCurrent() {
        int index_ = getSelectedIndex();
        if (!elements.isValidIndex(index_)) {
            return null;
        }
        return elements.getKey(index_);
    }

}
