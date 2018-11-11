package cards.gui.comboboxes;
import code.format.Translatable;
import code.gui.TreeComboBox;
import code.util.CustList;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;

public final class ComboBoxEnumCards<E extends Translatable> extends TreeComboBox<Integer> {

    private CustList<E> real = new CustList<E>();

    public ComboBoxEnumCards() {
        super(new TreeMap<Integer,String>(new NaturalComparator<Integer>()));
    }

    public void addItem(E _item, String _lg) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _item.toString(_lg));
        real.add(_item);
//        getElements().put(_item, _item.toString());
        super.addItem(_item.toString(_lg));
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
//
//    public E getCurrentElement() {
//        return (E) getSelectedItem();
//    }
}
