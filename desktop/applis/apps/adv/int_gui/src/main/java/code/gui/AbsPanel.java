package code.gui;

public interface AbsPanel extends AbsCustComponent, AbsContainer {
    int getComponentCount();
    AbsCustComponent getComponent(int _n);
    void add(AbsCustComponent _comp);
//    void innerAdd(AbsCustComponent _comp);
    void add(AbsCustComponent _comp, int _index);
//    void innerAdd(AbsCustComponent _comp, int _index);

    void add(AbsCustComponent _comp, AbsGridConstraints _constraints);
    void add(AbsCustComponent _comp, String _constraints);
//    void innerAdd(AbsCustComponent _comp, String _constraints);
    void remove(int _index);
//    int remove(AbsCustComponent _cust);
//    void innAdd(AbsCustComponent _c);
    void removeAll();
//    void innerRemoveAll();
}
