package code.gui;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.core.NumberUtil;


public class NumComboBox extends TreeComboBox<Integer> {

    public NumComboBox(AbstractProgramInfos _fact, AbstractGraphicComboBoxGenerator _gene) {
        super(new IntTreeMap<String>(), _gene.createCombo(_fact.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _fact.getCompoFactory()));
    }

    public NumComboBox(AbstractProgramInfos _fact, AbstractGraphicComboBoxGenerator _gene, int... _numerosPlis) {
        super(getTree(_numerosPlis), _gene.createCombo(_fact.getImageFactory(),new StringList(getTree(_numerosPlis).values()), 0, _fact.getCompoFactory()));
    }

    public void addItem(int _item) {
        getElements().put(_item, Integer.toString(_item));
        addItem(Integer.toString(_item));
    }

    private static IntTreeMap< String> getTree(int... _ints) {
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
