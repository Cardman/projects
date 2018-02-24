package code.gui.document;

import javax.swing.JTextField;

import code.formathtml.render.MetaTextField;

public class DualTextField extends DualInput {

    public DualTextField(DualContainer _container, MetaTextField _component, RenderedPage _page) {
        super(_container, _component, new JTextField(_component.getValue(),_component.getCols()), _page);
    }

    @Override
    public JTextField getGraphic() {
        return (JTextField) super.getGraphic();
    }

    @Override
    public String getValue() {
        return getGraphic().getText();
    }

}
