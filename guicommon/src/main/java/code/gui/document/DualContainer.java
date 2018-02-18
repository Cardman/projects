package code.gui.document;

import javax.swing.JPanel;

import code.formathtml.render.MetaContainer;

public abstract class DualContainer extends DualComponent {

    public DualContainer(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, new JPanel(), _page);
    }

    @Override
    public JPanel getGraphic() {
        return (JPanel) super.getGraphic();
    }
}
