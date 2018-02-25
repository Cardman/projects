package code.gui.document;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;

import code.formathtml.render.MetaForm;

public final class DualForm extends DualContainer {

    public DualForm(DualContainer _container, MetaForm _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        LayoutManager lay_;
        lay_ = new BoxLayout(getGraphic(), BoxLayout.PAGE_AXIS);
        getGraphic().setLayout(lay_);
    }

}
