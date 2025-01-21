package code.gui.document;

import code.formathtml.render.MetaCaption;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaTable;
import code.gui.AbsCustComponent;
import code.gui.AbsGridConstraints;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.util.Ints;

public final class DualTable extends DualContainer {

    private final Ints remainders;

    public DualTable(DualContainer _container, MetaTable _component, RenderedPage _page) {
        super(_container, _component, _page);
        remainders = _component.getRemainders();
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        return _page.getCompoFactory().newGrid();
    }

    @Override
    public void add(DualComponent _dual) {
        if (_dual.getComponent() instanceof MetaCaption) {
            super.add(_dual);
            return;
        }
        getPage().getRefs().put(_dual.getComponent(), _dual);
        tryAddRef(_dual);
        if (!getChildren().isEmpty()) {
            getChildren().last().setNextSibling(_dual);
        }
        getChildren().add(_dual);
        _dual.getGraphic().top();
        _dual.getGraphic().left();
        AbsGridConstraints cts_ = getPage().getCompoFactory().newGridCts();
        if (remainders.indexOf(getChildren().size()-1L) > -1) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        getPanel().add(_dual.getGraphic(), cts_);
        postAdd(_dual);
    }

    @Override
    public void addComponent(AbsCustComponent _component) {
        AbsGridConstraints cts_ = getPage().getCompoFactory().newGridCts();
        cts_.gridwidth(GuiConstants.REMAINDER);
        getPanel().add(_component, cts_);
    }
}
