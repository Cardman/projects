package cards.gui.comboboxes;
import code.gui.TreeComboBox;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;
import code.util.ints.Displayable;

public final class ComboBoxEnumCards<E extends Displayable> extends TreeComboBox<Integer> {

//    private EnumList<E> real = new EnumList<E>();

    public ComboBoxEnumCards() {
        super(new TreeMap<Integer,String>(new NaturalComparator<Integer>()));
    }

    public void addItem(E _item) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _item.display());
//        getElements().put(_item, _item.toString());
        super.addItem(_item);
    }
//
//    public E getCurrentElement() {
//        return (E) getSelectedItem();
//    }
}
