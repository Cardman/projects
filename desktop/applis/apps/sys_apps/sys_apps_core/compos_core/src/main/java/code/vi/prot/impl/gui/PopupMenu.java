package code.vi.prot.impl.gui;

import code.gui.*;

import javax.swing.*;

public final class PopupMenu extends CustComponent implements AbsPopupMenu {
    private final JPopupMenu pop = new JPopupMenu();
    @Override
    public JComponent getNatComponent() {
        return pop;
    }

    public void show(AbsCustComponent _panel, int _i, int _height) {
        pop.show(((CustComponent) _panel).getNatComponent(),_i,_height);
    }

    public void show(int _i, int _height) {
        pop.show(null,_i,_height);
    }
    public void add(AbsCustComponent _global) {
        pop.add(((CustComponent)_global).getNatComponent());
    }
    public void remove(AbsCustComponent _global) {
        pop.remove(((CustComponent)_global).getNatComponent());
    }
}
