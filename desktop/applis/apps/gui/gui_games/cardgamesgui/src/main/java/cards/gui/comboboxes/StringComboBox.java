package cards.gui.comboboxes;
import code.gui.GraphicComboGrInt;
import code.gui.TreeComboBox;
import code.util.AbsMap;
import code.util.*;


public class StringComboBox extends TreeComboBox<Integer> {

    public StringComboBox(GraphicComboGrInt _combo) {
        super(new IntTreeMap<String>(), _combo);
    }

//    @Override
    public void addItem(String _anObject) {
        AbsMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _anObject);
        getCombo().addItem(_anObject);
    }

    public String getSelectedComboItem() {
        return getCombo().getSelectedItem();
    }
}
