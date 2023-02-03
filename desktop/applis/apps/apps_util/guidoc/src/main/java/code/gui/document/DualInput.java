package code.gui.document;

import code.formathtml.render.MetaInput;

import code.gui.Input;

public abstract class DualInput extends DualLeaf {
    private Input select;
    private final int group;
    private final long formNb;

    protected DualInput(DualContainer _container, MetaInput _component,
                     RenderedPage _page) {
        super(_container, _component, _page);
        group = _component.getGroup();
        formNb = _component.getFormNb();
    }

    protected DualInput(DualContainer _container, MetaInput _component,
            Input _graphic, RenderedPage _page) {
        super(_container, _component, _page);
        select = _graphic;
        group = _component.getGroup();
        formNb = _component.getFormNb();
    }

    public long getFormNb() {
        return formNb;
    }

    public Input getSelect() {
        return select;
    }

    public DualForm getParentForm() {
        DualContainer parent_ = getContainer();
        while (parent_ != null) {
            if (parent_ instanceof DualForm) {
                break;
            }
            parent_ = parent_.getContainer();
        }
        return (DualForm) parent_;
    }

    public int getGroup() {
        return group;
    }


}
