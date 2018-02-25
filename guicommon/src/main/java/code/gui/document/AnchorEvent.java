package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.sml.Element;
import code.util.CustList;

public class AnchorEvent extends MouseAdapter {

    private Element anchor;

    private RenderedPage page;

    private CustList<DualAnimatedImage> anims;

    private DualLeaf leaf;

    public AnchorEvent(Element _anchor, RenderedPage _page, CustList<DualAnimatedImage> _anims, DualLeaf _leaf) {
        anchor = _anchor;
        page = _page;
        anims = _anims;
        leaf = _leaf;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        for (DualAnimatedImage d: anims) {
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
        long na_ = CustList.INDEX_NOT_FOUND_ELT;
        if (anchor.hasAttribute("n-a")) {
            na_ = Long.parseLong(anchor.getAttribute("n-a"));
        }
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(false);
        htmlPage_.setUrl(na_);
        nav_.processAnchorRequest(anchorRef_);
    }
}
