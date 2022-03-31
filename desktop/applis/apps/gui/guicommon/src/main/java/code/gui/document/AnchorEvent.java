package code.gui.document;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;
import code.sml.Element;

public final class AnchorEvent extends AbsMouseListenerRel {

    private final Element anchor;

    private final RenderedPage page;
    private final long nb;

    public AnchorEvent(Element _anchor, RenderedPage _page, long _n) {
        anchor = _anchor;
        page = _page;
        nb = _n;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        Navigation nav_ = page.getNavigation();
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(false);
        htmlPage_.setUrl(nb);
        page.getGene().getThreadFactory().newStartedThread(EventThreadActions.inst(page, false,anchor));
        page.animateProcess();
    }
}
