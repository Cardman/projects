package cards.gui.comboboxes;
import code.gui.GraphicComboGrInt;
import code.gui.TreeComboBox;
import code.util.AbsMap;
import code.util.CustList;
import code.util.*;

public final class ComboBoxEnumCards<E> extends TreeComboBox {

    private final CustList<E> real = new CustList<E>();

    public ComboBoxEnumCards(GraphicComboGrInt _combo) {
        super(new IntTreeMap<String>(), _combo);
    }

    public void addItem(E _item, String _itemString) {
        AbsMap<Integer, String> tr_;
        tr_ = getElements();
        tr_.put(tr_.size(), _itemString);
        real.add(_item);
        addItem(_itemString);
    }

    public CustList<E> getReal() {
        return real;
    }

    public E getCurrentElement() {
        int key_ = getSelectedIndex();
        if (key_ < 0) {
            return null;
        }
        return real.get(key_);
    }
}
