package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.gui.CustComponent;
import code.gui.Panel;

public abstract class DualContainer extends DualComponent {

    private Panel panel;

    public DualContainer(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
        panel = newPanel(_container,_component,_page);
        updateGraphics(panel,_component);
    }

    protected abstract Panel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page);

    @Override
    public CustComponent getGraphic() {
        return panel;
    }
}
