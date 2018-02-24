package code.gui.document;

import javax.swing.JComponent;

import code.formathtml.render.MetaInput;

public abstract class DualInput extends DualLeaf {

    public DualInput(DualContainer _container, MetaInput _component,
            JComponent _graphic, RenderedPage _page) {
        super(_container, _component, _graphic, _page);
    }

    public int getGroup() {
        return getComponent().getGroup();
    }

    @Override
    public MetaInput getComponent() {
        return (MetaInput) super.getComponent();
    }

    public abstract Object getValue();
}
