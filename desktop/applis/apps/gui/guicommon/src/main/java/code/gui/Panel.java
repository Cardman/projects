package code.gui;

import code.gui.images.AbstractImageFactory;
import code.util.CustList;

import java.awt.*;

import javax.swing.*;

public final class Panel extends CustComponent {

    private final JPanel panel;

    private Panel(LayoutManager _panel) {
        this(new JPanel(_panel));
    }

    private Panel(BorderLayout _panel) {
        this(new JPanel(_panel));
    }

    private Panel(GridLayout _panel) {
        this(new JPanel(_panel));
    }

    private Panel(JPanel _panel) {
        panel = _panel;
    }

    public static Panel newAbsolute() {
        return new Panel((LayoutManager)null);
    }

    public static Panel newBorder() {
        return new Panel(new BorderLayout());
    }

    public static Panel newGrid(int _row,int _col) {
        return new Panel(new GridLayout(_row,_col));
    }

    public static Panel newGrid(int _row,int _col, int _h, int _v) {
        return new Panel(new GridLayout(_row,_col,_h,_v));
    }
    public static Panel newPageBox() {
        JPanel inner_ = new JPanel();
        Panel panel_ = new Panel(inner_);
        panel_.setLayout(new BoxLayout(inner_, BoxLayout.PAGE_AXIS));
        return panel_;
    }

    public static Panel newLineBox() {
        JPanel inner_ = new JPanel();
        Panel panel_ = new Panel(inner_);
        panel_.setLayout(new BoxLayout(inner_, BoxLayout.LINE_AXIS));
        return panel_;
    }

    public int getComponentCount() {
        return panel.getComponentCount();
    }

    public AbsCustComponent getComponent(int _n) {
        return getChildren().get(_n);
    }

    public void add(Clock _comp) {
        this.add(_comp.getComponent());
    }

    public void add(AbsMetaLabel _comp) {
        add(_comp.getPaintableLabel());
    }
    public void add(AbsCustComponent _comp) {
        if (_comp.getParent() != null) {
            return;
        }
        innerAdd(_comp);
    }

    public void innerAdd(AbsCustComponent _comp) {
        _comp.setParent(this);
        innAdd(_comp);
    }

    public void add(AbsMetaLabel _comp, int _index) {
        add(_comp.getPaintableLabel(),_index);
    }
    public void add(AbsCustComponent _comp, int _index) {
        if (_comp.getParent() != null) {
            return;
        }
        innerAdd(_comp, _index);
    }

    public void innerAdd(AbsCustComponent _comp, int _index) {
        _comp.setParent(this);
        getChildren().add(_index, _comp);
        panel.add(((CustComponent) _comp).getNatComponent(), _index);
    }

    public void add(AbsMetaLabel _comp, String _constraints) {
        add(_comp.getPaintableLabel(),_constraints);
    }
    public void add(AbsCustComponent _comp, String _constraints) {
        if (_comp.getParent() != null) {
            return;
        }
        innerAdd(_comp, _constraints);
    }

    public void innerAdd(AbsCustComponent _comp, String _constraints) {
        _comp.setParent(this);
        getChildren().add(_comp);
        panel.add(((CustComponent) _comp).getNatComponent(), _constraints);
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        panel.remove(_index);
    }

    public int remove(AbsCustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        CustList<AbsCustComponent> rem_ = new CustList<AbsCustComponent>();
        for (AbsCustComponent c: getChildren()) {
            if (c == _cust) {
                c.setParent(null);
                index_ = i_;
            } else {
                rem_.add(c);
            }
            i_++;
        }
        innerRemoveAll();
        for (AbsCustComponent c: rem_) {
            innAdd(c);
        }
        return index_;
    }

    public void innAdd(AbsCustComponent _c) {
        getChildren().add(_c);
        panel.add(((CustComponent) _c).getNatComponent());
    }

    public void removeAll() {
        for (AbsCustComponent c: getChildren()) {
            c.setParent(null);
        }
        innerRemoveAll();
    }

    public void innerRemoveAll() {
        getChildren().clear();
        panel.removeAll();
    }

    public void repaintSecondChildren(AbstractImageFactory _fact) {
        for (AbsCustComponent c: getChildren()) {
            procCh(_fact, c);
        }
        validate();
    }

    private static void procCh(AbstractImageFactory _fact, AbsCustComponent _c) {
        if (_c instanceof AbsPaintableLabel) {
            ((AbsPaintableLabel) _c).repaintLabel(_fact);
        } else if (_c instanceof Panel) {
            for (AbsCustComponent d: _c.getChildren()) {
                proc(_fact, d);
            }
            _c.validate();
        }
    }

    public void repaintChildren(AbstractImageFactory _fact) {
        for (AbsCustComponent c: getChildren()) {
            proc(_fact, c);
        }
        validate();
    }

    private static void proc(AbstractImageFactory _fact, AbsCustComponent _c) {
        if (_c instanceof AbsPaintableLabel) {
            ((AbsPaintableLabel) _c).repaintLabel(_fact);
        }
    }

    public void invalidate() {
        panel.invalidate();
    }

    @Override
    protected JComponent getNatComponent() {
        return panel;
    }

    private void setLayout(BoxLayout _borderLayout) {
        panel.setLayout(_borderLayout);
    }

}
