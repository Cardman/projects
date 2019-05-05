package cards.gui.comboboxes;
import code.gui.TreeComboBox;
import code.util.CustList;
import code.util.TreeMap;
import code.util.comparators.ComparatorNatNumber;

public final class ComboBoxEnumCards<E> extends TreeComboBox<Integer> {

    private CustList<E> real = new CustList<E>();

    public ComboBoxEnumCards() {
        super(new TreeMap<Integer,String>(new ComparatorNatNumber<Integer>()));
    }

    public void addItem(E _item, String _itemString) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _itemString);
        real.add(_item);
        super.addItem(_itemString);
    }
    @Override
    public void removeItem(int _anIndex) {
        real.remove(_anIndex);
        super.removeItem(_anIndex);
    }
    @Override
    public void removeAllItems() {
        real.clear();
        super.removeAllItems();
    }
    public E getCurrentElement() {
        Integer key_ = getCurrent();
        if (key_ == null) {
            return null;
        }
        return real.get(key_);
    }
}
