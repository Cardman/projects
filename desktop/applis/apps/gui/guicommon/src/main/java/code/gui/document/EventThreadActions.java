package code.gui.document;

public final class EventThreadActions extends AbstractThreadActions {

    private String anchor;


    private boolean form;

    private EventThreadActions(){}

    static EventThreadActions inst(RenderedPage _page, String _anchor, boolean _form) {
        EventThreadActions t_ = new EventThreadActions();
        t_.setPage(_page);
        t_.getPage().start();
        t_.anchor = _anchor;
        t_.form = _form;
        return t_;
    }

    @Override
    public void run() {
        if (form) {
            getPage().getNavigation().processRendFormRequest(getPage().getStandards(), getPage().getContext());
            afterAction();
            return;
        }
        getPage().getNavigation().processRendAnchorRequest(anchor, getPage().getStandards(), getPage().getContext());
        afterAction();
    }
}
