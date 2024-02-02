package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;

public abstract class DualContainer extends DualComponent {

    private final AbsPanel panel;

    protected DualContainer(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
        panel = newPanel(_container,_component,_page);
        updateGraphics(getPanel(),_component);
    }

    protected abstract AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page);

    public void add(DualComponent _dual) {
        getPage().getRefs().put(_dual.getComponent(), _dual);
        if (!getChildren().isEmpty()) {
            getChildren().last().setNextSibling(_dual);
        }
        getChildren().add(_dual);
        _dual.getGraphic().top();
        _dual.getGraphic().left();
        addComponent(_dual.getGraphic());
        if (_dual instanceof DualAnimatedImage) {
            getPage().getAnims().add((DualAnimatedImage) _dual);
        }
        postAdd(_dual);
    }

    public void addComponent(AbsCustComponent _component) {
        getPanel().add(_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return getPanel();
    }

    protected AbsPanel getPanel() {
        return panel;
    }
}
