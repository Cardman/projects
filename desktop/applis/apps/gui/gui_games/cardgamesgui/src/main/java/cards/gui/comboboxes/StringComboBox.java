package cards.gui.comboboxes;
import code.gui.TreeComboBox;
import code.util.AbsMap;
import code.util.*;


public class StringComboBox extends TreeComboBox<Integer> {

    public StringComboBox() {
        super(new IntTreeMap<String>());
    }

    @Override
    public void addItem(String _anObject) {
        AbsMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _anObject);
        super.addItem(_anObject);
    }

    public String getSelectedComboItem() {
        return getSelectedItem();
    }
}
