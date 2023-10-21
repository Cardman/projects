package cards.gui.comboboxes;

import code.gui.AbsComboBox;
import code.gui.ScrollCustomCombo;


public class StringComboBox extends AbsComboBox {

    public StringComboBox(ScrollCustomCombo _combo) {
        super(_combo);
    }

//    @Override
//    public void addItem(String _anObject) {
//        AbsMap<Integer, String> tr_;
//        tr_ = getElements();
//        tr_.put(tr_.size(), _anObject);
//        getCombo().addItem(_anObject);
//    }

    public String getSelectedComboItem() {
        return getSelectedItem();
    }
}
