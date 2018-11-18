package cards.gui.comboboxes;
import code.gui.TreeComboBox;
import code.util.TreeMap;
import code.util.comparators.ComparatorNatNumber;


public class StringComboBox extends TreeComboBox<Integer> {

    public StringComboBox() {
        super(new TreeMap<Integer,String>(new ComparatorNatNumber<Integer>()));
    }

    @Override
    public void addItem(String _anObject) {
        TreeMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _anObject);
        super.addItem(_anObject);
    }

    public String getSelectedComboItem() {
        return getSelectedItem();
    }
}
