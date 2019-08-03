package code.gui.document;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaForm;
import code.gui.Panel;

public final class DualForm extends DualContainer {

    public DualForm(DualContainer _container, MetaForm _component,
            RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected Panel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        return Panel.newPageBox();
    }
}
