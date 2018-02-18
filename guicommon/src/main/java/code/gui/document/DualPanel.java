package code.gui.document;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaLayout;


public final class DualPanel extends DualContainer {

    public DualPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
        LayoutManager lay_;
        if (_component.getLayout() == MetaLayout.BOX) {
            lay_ = new BoxLayout(getGraphic(), BoxLayout.PAGE_AXIS);
        } else {
            lay_ = new FlowLayout(FlowLayout.LEFT, 0, 0);
        }
        getGraphic().setLayout(lay_);
    }

}
