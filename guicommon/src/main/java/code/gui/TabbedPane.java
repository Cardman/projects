package code.gui;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public final class TabbedPane extends CustComponent {

    private JTabbedPane component;

    public TabbedPane() {
        component = new JTabbedPane();
    }

    public int getComponentCount() {
        return getChildren().size();
    }

    public void add(String _title, CustComponent _component) {
        if (_component.getParent() != null) {
            return;
        }
        _component.setParent(this);
        getChildren().add(_component);
        component.add(_title, _component.getComponent());
    }

    public CustComponent getComponent(int _index) {
        return getChildren().get(_index);
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

    public void addTab(String _name, CustComponent _component) {
        if (_component.getParent() != null) {
            return;
        }
        component.addTab(_name,_component.getComponent());
        getChildren().add(_component);
    }
}
