package code.gui.document;

import javax.swing.JComponent;

import code.formathtml.render.MetaInput;
import code.gui.Input;

public abstract class DualInput extends DualLeaf {
    private Input select;
    public DualInput(DualContainer _container, MetaInput _component,
            JComponent _graphic, RenderedPage _page) {
        super(_container, _component, _graphic, _page);
    }

    public DualInput(DualContainer _container, MetaInput _component,
            Input _graphic, RenderedPage _page) {
        super(_container, _component, _graphic.getGlobal(), _page);
        select = _graphic;
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
        return getComponent().getGroup();
    }

    public String getName() {
        return getComponent().getName();
    }

    @Override
    public MetaInput getComponent() {
        return (MetaInput) super.getComponent();
    }

    public abstract Object getValue();
}
