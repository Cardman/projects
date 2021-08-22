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

    private DualLeaf leaf;

    public AnchorEvent(Element _anchor, RenderedPage _page, DualLeaf _leaf) {
        anchor = _anchor;
        page = _page;
        leaf = _leaf;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        Navigation nav_ = page.getNavigation();
        String anchorRef_ = "";
        if (leaf instanceof DualAnchoredLabel) {
            anchorRef_ = ((DualAnchoredLabel)leaf).getHref();
        } else if (leaf instanceof DualSimpleImage) {
            anchorRef_ = ((DualSimpleImage)leaf).getHref();
        } else if (leaf instanceof DualAnimatedImage) {
            anchorRef_ = ((DualAnimatedImage)leaf).getHref();
        }
        long na_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (anchor.hasAttribute("n-a")) {
            na_ = NumberUtil.parseLongZero(anchor.getAttribute("n-a"));
        }
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(false);
        htmlPage_.setUrl(na_);
        page.getGene().getThreadFactory().newStartedThread(EventThreadActions.inst(page, anchorRef_, false));
        page.animateProcess();
    }
}
