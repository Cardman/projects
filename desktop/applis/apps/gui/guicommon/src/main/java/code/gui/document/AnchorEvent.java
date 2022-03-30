package code.gui.document;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;
import code.sml.Element;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class AnchorEvent extends AbsMouseListenerRel {

    private Element anchor;

    private RenderedPage page;

    public AnchorEvent(Element _anchor, RenderedPage _page) {
        anchor = _anchor;
        page = _page;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        Navigation nav_ = page.getNavigation();
        long na_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (anchor.hasAttribute("n-a")) {
            na_ = NumberUtil.parseLongZero(anchor.getAttribute("n-a"));
        }
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(false);
        htmlPage_.setUrl(na_);
        page.getGene().getThreadFactory().newStartedThread(EventThreadActions.inst(page, false,anchor));
        page.animateProcess();
    }
}
