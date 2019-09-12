package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaLayout;
import code.gui.Panel;


public final class DualPanel extends DualContainer {

    public DualPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected Panel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        if (_component.getLayout() == MetaLayout.BOX) {
            return Panel.newPageBox();
        } else {
            return Panel.newLineBox();
        }
    }
}
