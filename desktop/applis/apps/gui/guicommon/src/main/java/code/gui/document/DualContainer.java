package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.CustComponent;
import code.gui.Panel;

public abstract class DualContainer extends DualComponent {

    private AbsPanel panel;

    public DualContainer(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
        panel = newPanel(_container,_component,_page);
        updateGraphics(getPanel(),_component);
    }

    protected abstract AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page);

    @Override
    public AbsCustComponent getGraphic() {
        return getPanel();
    }

    protected AbsPanel getPanel() {
        return panel;
    }
}
