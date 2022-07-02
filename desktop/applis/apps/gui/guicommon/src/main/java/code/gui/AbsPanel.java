package code.gui;

import code.gui.images.AbstractImageFactory;

public interface AbsPanel extends AbsCustComponent {
    int getComponentCount();
    AbsCustComponent getComponent(int _n);
    void add(AbsClock _comp);
    void add(AbsMetaLabelComInt _comp);
    void add(AbsCustComponent _comp);
    void innerAdd(AbsCustComponent _comp);
    void add(AbsMetaLabelComInt _comp, int _index);
    void add(AbsCustComponent _comp, int _index);
    void innerAdd(AbsCustComponent _comp, int _index);
    void add(AbsMetaLabelComInt _comp, String _constraints);
    void add(AbsCustComponent _comp, String _constraints);
    void innerAdd(AbsCustComponent _comp, String _constraints);
    void remove(int _index);
    int remove(AbsCustComponent _cust);
    void innAdd(AbsCustComponent _c);
    void removeAll();
    void innerRemoveAll();
    void repaintSecondChildren(AbstractImageFactory _fact);
    void repaintChildren(AbstractImageFactory _fact);
}
