package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.sml.Element;
import code.util.CustList;

public class FormEvent extends MouseAdapter {

    private Element form;

    private RenderedPage page;

    private CustList<DualAnimatedImage> anims;

    public FormEvent(Element _form, RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        form = _form;
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
