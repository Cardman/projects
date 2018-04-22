package code.gui;

import javax.swing.JComponent;

public class PseudoComponent extends CustComponent {

    private JComponent component;

    public PseudoComponent(JComponent _component) {
        component = _component;
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
}
