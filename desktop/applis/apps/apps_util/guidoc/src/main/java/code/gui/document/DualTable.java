package code.gui.document;

import code.formathtml.render.MetaCaption;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaTable;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.util.Ints;

public final class DualTable extends DualContainer {

    private final Ints remainders;
    private final int width;
    private int count;

    public DualTable(DualContainer _container, MetaTable _component, RenderedPage _page) {
        super(_container, _component, _page);
        remainders = _component.getRemainders();
        width = _component.getDiff();
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        return _page.getCompoFactory().newPageBox();
    }

    @Override
    public void add(DualComponent _dual) {
        count++;
        if (_dual.getComponent() instanceof MetaCaption) {
            super.add(_dual);
            getPanel().add(getPage().getCompoFactory().newGrid(0,width));
            return;
        }
        int remNext_ = remainders.indexOf(count-1L);
        int count_ = getPanel().getComponentCount();
        if (count_ == 0) {
            getPanel().add(getPage().getCompoFactory().newGrid(0,width));
            count_++;
        }
        AbsCustComponent c_ = getPanel().getComponent(count_ -1);
        getPage().getRefs().put(_dual.getComponent(), _dual);
        if (!getChildren().isEmpty()) {
            getChildren().last().setNextSibling(_dual);
        }
        getChildren().add(_dual);
        if (c_ instanceof AbsPanel) {
            ((AbsPanel)c_).add(_dual.getGraphic());
        }
        if (remNext_ > -1 && remNext_ != remainders.size() - 1) {
            getPanel().add(getPage().getCompoFactory().newGrid(0, width));
        }
        postAdd(_dual);
    }
}
