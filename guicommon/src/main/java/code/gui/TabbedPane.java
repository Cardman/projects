package code.gui;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class TabbedPane extends CustComponent {

    private JTabbedPane component;

    public TabbedPane() {
        component = new JTabbedPane();
    }

    public int getComponentCount() {
        return component.getComponentCount();
    }

    public Component add(String _title, JComponent _component) {
        return add(_title, new PseudoComponent(_component));
    }

    public Component add(String _title, CustComponent _component) {
        _component.setParent(this);
        getChildren().add(_component);
        return component.add(_title, _component.getComponent());
    }

    public Component getComponentAt(int _index) {
        return component.getComponentAt(_index);
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    public void removeAll() {
        for (CustComponent c: getChildren()) {
            c.setParent(null);
        }
        getChildren().clear();
        component.removeAll();
    }

}
