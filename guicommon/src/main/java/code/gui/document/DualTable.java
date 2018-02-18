package code.gui.document;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import code.formathtml.render.MetaCell;
import code.formathtml.render.MetaTable;

public final class DualTable extends DualContainer {

    private GridBagConstraints constraints;

    private int row;

    private int col;

    public DualTable(DualContainer _container, MetaTable _component, RenderedPage _page) {
        super(_container, _component, _page);
        GridBagLayout lay_ = new GridBagLayout();
        getGraphic().setLayout(lay_);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    @Override
    public MetaTable getComponent() {
        return (MetaTable) super.getComponent();
    }

    @Override
    public void add(DualComponent _dual) {
        MetaTable table_ = getComponent();
        boolean rem_ = table_.getRemainders().containsObj(getComponentCount());
        if (rem_) {
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            if (_dual.getComponent() instanceof MetaCell) {
                constraints.gridheight = ((MetaCell)_dual.getComponent()).getColspan();
            } else {
                constraints.gridheight = 1;
            }
        } else {
            constraints.gridwidth = ((MetaCell)_dual.getComponent()).getRowspan();
            constraints.gridheight = ((MetaCell)_dual.getComponent()).getColspan();
        }
        constraints.gridx = row;
        constraints.gridy = col;
        getLayout().setConstraints(_dual.getGraphic(), constraints);
        super.add(_dual);
        if (rem_) {
            row++;
            col = 0;
        } else {
            col++;
        }
    }

    public GridBagLayout getLayout() {
        return (GridBagLayout) getGraphic().getLayout();
    }
}
