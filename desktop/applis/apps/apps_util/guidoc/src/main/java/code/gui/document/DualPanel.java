package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaLayout;
import code.gui.AbsPanel;


public final class DualPanel extends DualContainer {

    public DualPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        if (_component.getLayout() == MetaLayout.BOX) {
            return _page.getCompoFactory().newPageBox();
        } else {
            return _page.getCompoFactory().newLineBox();
        }
    }
}
