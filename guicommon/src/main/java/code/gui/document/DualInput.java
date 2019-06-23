package code.gui.document;

import javax.swing.JComponent;

import code.formathtml.render.MetaInput;
import code.gui.Input;

public abstract class DualInput extends DualLeaf {
    private Input select;
    private int group;
    private String name;

    public DualInput(DualContainer _container, MetaInput _component,
                     RenderedPage _page) {
        super(_container, _component, _page);
        group = _component.getGroup();
        name = _component.getName();
    }

    public DualInput(DualContainer _container, MetaInput _component,
            Input _graphic, RenderedPage _page) {
        super(_container, _component, _page);
        select = _graphic;
        group = _component.getGroup();
        name = _component.getName();
    }

    @Override
    public JComponent getGraphic() {
        return select.getGlobal();
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

    public String getName() {
        return name;
    }


}
