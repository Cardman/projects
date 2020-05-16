package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.formathtml.render.SubmitForm;
import code.gui.CustComponent;

public final class FormEvent extends MouseAdapter {

    private DualButton current;

    private RenderedPage page;

    public FormEvent(DualButton _current, RenderedPage _page) {
        current = _current;
        page = _page;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        DualForm form_ = current.getParentForm();
        if (form_ == null) {
            return;
        }
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        SubmitForm.submit(form_,page.getNavigation());
        CustComponent.newThread(ThreadActions.inst(page, page.getStandards(), "", "", true, false)).start();
        page.animateProcess();
    }
}
