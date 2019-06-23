package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaContainer;

public abstract class DualContainer extends DualComponent {

    private JPanel panel;

    public DualContainer(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        super(_container, _component, _page);
        panel = new JPanel();
        updateGraphics(panel,_component);
    }

    @Override
    public JComponent getGraphic() {
        return panel;
    }
}
