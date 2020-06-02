package aiki.gui.components.checks;
import java.awt.Color;


import code.gui.CustCheckBox;

public abstract class CheckBox {

    private CustCheckBox component = new CustCheckBox();

    private String key;

    public CheckBox(String _key, String _text, boolean _selected) {
        key = _key;
        component.setText(_text);
        component.setSelected(_selected);
        component.addActionListener(new CheckEvent(this));
    }

    public void setSelected(boolean _b) {
        component.setSelected(_b);
    }

    public void setBackground(Color _bg) {
        component.setBackground(_bg);
    }

    public CustCheckBox getComponent() {
        return component;
    }

    String getKey() {
        return key;
    }

    protected abstract void processKey(String _key);
}
