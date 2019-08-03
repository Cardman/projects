package code.gui.document;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import code.formathtml.render.MetaCell;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaTable;
import code.gui.Panel;
import code.util.Ints;

public final class DualTable extends DualContainer {

    private GridBagConstraints constraints;
    private Ints remainders;

    public DualTable(DualContainer _container, MetaTable _component, RenderedPage _page) {
        super(_container, _component, _page);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        remainders = _component.getRemainders();
    }

    @Override
    protected Panel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        Panel p_ = new Panel();
        GridBagLayout lay_ = new GridBagLayout();
        p_.getComponent().setLayout(lay_);
        return p_;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    @Override
    public void add(DualComponent _dual) {
        boolean rem_ = remainders.containsObj(getComponentCount());
        if (rem_) {
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            if (_dual.getComponent() instanceof MetaCell) {
                constraints.gridheight = ((MetaCell)_dual.getComponent()).getColspan();
            } else {
                constraints.gridheight = 1;
            }
        } else {
            //BorderFactory.createLineBorder(color);
            //BorderFactory.createMatteBorder(top, left, bottom, right, color)
            constraints.gridheight = ((MetaCell)_dual.getComponent()).getRowspan();
            constraints.gridwidth = ((MetaCell)_dual.getComponent()).getColspan();
        }
        getLayout().setConstraints(_dual.getGraphic().getComponent(), constraints);
        super.add(_dual);
    }

    private GridBagLayout getLayout() {
        return (GridBagLayout) getGraphic().getComponent().getLayout();
    }
}
