package code.gui.document;


import code.formathtml.render.SubmitForm;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public final class FormEvent extends AbsMouseListenerRel {

    private DualButton current;

    private RenderedPage page;

    public FormEvent(DualButton _current, RenderedPage _page) {
        current = _current;
        page = _page;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        DualForm form_ = current.getParentForm();
        if (form_ == null) {
            return;
        }
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        SubmitForm.submit(form_,page.getNavigation());
        page.getGene().getThreadFactory().newStartedThread(EventThreadActions.inst(page, true));
        page.animateProcess();
    }
}
