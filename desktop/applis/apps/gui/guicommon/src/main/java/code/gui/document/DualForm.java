package code.gui.document;
import code.formathtml.render.IntForm;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaForm;
import code.gui.AbsPanel;
import code.sml.Element;

public final class DualForm extends DualContainer implements IntForm {

    private final long number;
    private final Element elt;

    public DualForm(DualContainer _container, MetaForm _component,
                    RenderedPage _page) {
        super(_container, _component, _page);
        number = _component.getNumber();
        elt = _component.getElt();
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        return _page.getCompoFactory().newPageBox();
    }

    @Override
    public Element getElt() {
        return elt;
    }

    @Override
    public long getNumber() {
        return number;
    }
}
