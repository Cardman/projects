package code.gui.document;

import javax.swing.RootPaneContainer;

import code.formathtml.Navigation;
import code.formathtml.render.MetaComponent;
import code.util.IdMap;

public final class RenderedPage {

    private DualPanel page;
    private final RootPaneContainer frame;
    private final Navigation navigation;
    private IdMap<MetaComponent,DualComponent> refs = new IdMap<MetaComponent,DualComponent>();

    public RenderedPage(RootPaneContainer _frame, Navigation _navigation) {
        frame = _frame;
        navigation = _navigation;
    }
    public void setPage(DualPanel _page) {
        page = _page;
    }
    public IdMap<MetaComponent, DualComponent> getRefs() {
        return refs;
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
