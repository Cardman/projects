package code.gui.document;

import javax.swing.JTextArea;

import code.formathtml.render.MetaTextArea;

public final class DualTextArea extends DualInput {

    public DualTextArea(DualContainer _container, MetaTextArea _component, RenderedPage _page) {
        super(_container, _component, new JTextArea(_component.getValue(),_component.getRows(),_component.getCols()), _page);
    }

    @Override
    public JTextArea getGraphic() {
        return (JTextArea) super.getGraphic();
    }

    @Override
    public String getValue() {
        return getGraphic().getText();
    }

}
