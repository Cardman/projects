package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.sml.Element;
import code.util.CustList;

public class AnchorEvent extends MouseAdapter {

    private Element anchor;

    private RenderedPage page;

    private CustList<DualAnimatedImage> anims;

    public AnchorEvent(Element _anchor, RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        anchor = _anchor;
        page = _page;
        anims = _anims;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        for (DualAnimatedImage d: anims) {
            d.getImageThread().setAnimated(false);
        }
    }
}
