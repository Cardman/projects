package code.gui;
import code.util.*;
import code.util.core.NumberUtil;


public class NumComboBox extends TreeComboBox<Integer> {

    public NumComboBox() {
        super(new IntTreeMap< String>());
    }
    public NumComboBox(Integer... _numerosPlis) {
        super(getTree(_numerosPlis));
    }

    public void addItem(Integer _item) {
        getElements().put(_item, _item.toString());
        super.addItem(_item.toString());
    }

    private static IntTreeMap< String> getTree(Integer... _ints) {
        IntTreeMap< String> tr_;
        tr_ = new IntTreeMap< String>();
        for (Integer i: _ints) {
            tr_.put(i, i.toString());
        }
        return tr_;
    }
    public void setSelectedItem(int _i) {
        int index_ = 0;
        for (Integer k: getElements().getKeys()) {
            if (NumberUtil.eq(k, _i)) {
                selectItem(index_);
                break;
            }
            index_++;
        }
    }
}
