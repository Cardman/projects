package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.sml.Element;
import code.util.CustList;

public class FormEvent extends MouseAdapter {

    private Element form;

    private DualButton current;

    private RenderedPage page;

    private CustList<DualAnimatedImage> anims;

    public FormEvent(Element _form, DualButton _current, RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        form = _form;
        current = _current;
        page = _page;
        anims = _anims;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        DualForm form_ = current.getParentForm();
        if (form_ == null) {
            return;
        }
        for (DualAnimatedImage d: anims) {
            d.getImageThread().setAnimated(false);
        }
        DualComponent current_ = form_.getChildren().first();
        while (true) {
            if (current_ instanceof DualInput) {
                DualInput input_ = (DualInput) current_;
                if (input_.getParentForm() == form_) {
                    System.out.println(input_.getValue());
                }
            }
            CustList<DualComponent> ch_ = current_.getChildren();
            if (!ch_.isEmpty()) {
                current_ = ch_.first();
                continue;
            }
            DualComponent n_ = getNextSibling(current_);
            if (n_ != null) {
                current_ = n_;
                continue;
            }
            DualContainer par_ = current_.getContainer();
            if (par_ == form_) {
                break;
            }
            n_ = getNextSibling(par_);
            while (n_ != null) {
                DualContainer grPar_ = par_.getContainer();
                if (grPar_ == form_) {
                    break;
                }
                n_ = getNextSibling(grPar_);
                par_ = grPar_;
            }
            if (n_ == null) {
                break;
            }
            current_ = n_;
        }
    }
    private static DualComponent getNextSibling(DualComponent _current) {
        DualContainer par_ = _current.getContainer();
        CustList<DualComponent> next_ = par_.getChildren();
        int len_ = next_.size();
        int index_ = -1;
        for (int i = 0; i < len_; i++) {
            if (next_.get(i) == _current) {
                index_ = i + 1;
                break;
            }
        }
        if (index_ < len_) {
            return next_.get(index_);
        }
        return null;
    }
}
