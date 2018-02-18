package code.gui.document;

import javax.swing.JComponent;

import code.formathtml.render.MetaComponent;

public abstract class DualLeaf extends DualComponent {

    public DualLeaf(DualContainer _container, MetaComponent _component, JComponent _graphic, RenderedPage _page) {
        super(_container, _component, _graphic, _page);
    }

}
