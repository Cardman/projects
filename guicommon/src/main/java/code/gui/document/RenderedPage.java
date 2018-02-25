package code.gui.document;

import javax.swing.RootPaneContainer;

import code.formathtml.Navigation;
import code.formathtml.render.MetaDocument;

public final class RenderedPage {

    private final DualPanel page;
    private final RootPaneContainer frame;
    private final Navigation navigation;

    public RenderedPage(MetaDocument _document, RootPaneContainer _frame, Navigation _navigation) {
        page = new DualPanel(null, _document.getRoot(), this);
        frame = _frame;
        navigation = _navigation;
    }
    public Navigation getNavigation() {
        return navigation;
    }
    public RootPaneContainer getFrame() {
        return frame;
    }
    public DualPanel getPage() {
        return page;
    }
}
