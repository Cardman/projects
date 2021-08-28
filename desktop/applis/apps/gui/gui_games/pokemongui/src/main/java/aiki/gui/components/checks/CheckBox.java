package aiki.gui.components.checks;
import java.awt.Color;

import code.gui.AbsCustCheckBox;
import code.gui.initialize.AbsCompoFactory;

public abstract class CheckBox {

    private final AbsCustCheckBox component;

    private final String key;

    public CheckBox(String _key, String _text, boolean _selected, AbsCompoFactory _window) {
        component = _window.newCustCheckBox();
        key = _key;
        component.setText(_text);
        component.setSelected(_selected);
        component.addActionListener(new CheckEvent(this));
    }

    public void setSelected(boolean _b) {
        component.setSelected(_b);
    }

    public void setBackground(int _bg) {
        component.setBackground(_bg);
    }

    public AbsCustCheckBox getComponent() {
        return component;
    }

    String getKey() {
        return key;
    }

    protected abstract void processKey(String _key);
}
