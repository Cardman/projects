package code.gui.document;

import javax.swing.RootPaneContainer;

import code.formathtml.render.MetaDocument;

public final class RenderedPage {

    private final DualPanel page;
    private final RootPaneContainer frame;

    public RenderedPage(MetaDocument _document, RootPaneContainer _frame) {
        page = new DualPanel(null, _document.getRoot(), this);
        frame = _frame;
    }
    public RootPaneContainer getFrame() {
        return frame;
    }
    public DualPanel getPage() {
        return page;
    }
}
