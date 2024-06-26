package code.gui;

import code.gui.events.AbsActionListenerAct;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.core.NumberUtil;


public class NumComboBox extends TreeComboBox {

    public NumComboBox(AbstractProgramInfos _fact) {
        this(new AlwaysActionListenerAct(),_fact);
    }

    public NumComboBox(AbsActionListenerAct _act, AbstractProgramInfos _fact) {
        super(new IntTreeMap<String>(), GuiBaseUtil.combo(_fact.getImageFactory(),new StringList(), 0, _fact.getCompoFactory(),_act));
    }

    public NumComboBox(AbstractProgramInfos _fact, int... _numerosPlis) {
        super(getTree(_numerosPlis), GuiBaseUtil.combo(_fact.getImageFactory(),new StringList(getTree(_numerosPlis).values()), 0, _fact.getCompoFactory()));
    }

    public void setItems(int _count) {
        removeAllItems();
        for (int i = 0; i < _count; i++) {
            addItem(i);
        }
        getCombo().repaint();
    }
    public void addItem(int _item) {
        getElements().put(_item, Integer.toString(_item));
        addItem(Integer.toString(_item));
    }

    private static IntTreeMap< String> getTree(int... _ints) {
        IntTreeMap< String> tr_;
        tr_ = new IntTreeMap< String>();
        for (int i: _ints) {
            tr_.put(i, Integer.toString(i));
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
