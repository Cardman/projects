package code.gui.document;

import code.formathtml.HtmlPage;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;
import code.sml.Element;

public final class AnchorEvent implements AbsMouseListenerIntRel {

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
            d.stop();
        }
        HtmlPage htmlPage_ = page.getStandards().getPage();
        htmlPage_.setForm(false);
        htmlPage_.setUrl(nb);
        page.getGene().getThreadFactory().newStartedThread(EventThreadActions.inst(page, false,anchor));
        page.animateProcess();
    }
}
