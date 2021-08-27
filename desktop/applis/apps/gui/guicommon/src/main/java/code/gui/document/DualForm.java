package code.gui.document;
import code.formathtml.render.IntForm;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaForm;
import code.gui.AbsPanel;
import code.gui.Panel;

public final class DualForm extends DualContainer implements IntForm {

    private final long number;

    public DualForm(DualContainer _container, MetaForm _component,
                    RenderedPage _page) {
        super(_container, _component, _page);
        number = _component.getNumber();
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        return Panel.newPageBox();
    }

    @Override
    public long getNumber() {
        return number;
    }
}
