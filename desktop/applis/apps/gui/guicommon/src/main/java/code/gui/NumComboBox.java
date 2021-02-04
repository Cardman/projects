package code.gui;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.*;
import code.util.core.NumberUtil;


public class NumComboBox extends TreeComboBox<Integer> {

    public NumComboBox(AbstractGraphicComboBoxGenerator _gene) {
        super(new IntTreeMap<String>(), _gene.createCombo(new StringList(new IntTreeMap<String>().values()), 0));
    }
    public NumComboBox(AbstractGraphicComboBoxGenerator _gene, Integer... _numerosPlis) {
        super(getTree(_numerosPlis), _gene.createCombo(new StringList(getTree(_numerosPlis).values()), 0));
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
