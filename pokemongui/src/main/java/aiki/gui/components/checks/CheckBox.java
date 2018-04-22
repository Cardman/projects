package aiki.gui.components.checks;
import java.awt.Color;

import javax.swing.JCheckBox;

import code.gui.CustComponent;

public abstract class CheckBox extends CustComponent {

    private JCheckBox component = new JCheckBox();

    private String key;

    public CheckBox(String _key, String _text, boolean _selected) {
        key = _key;
        component.setText(_text);
        component.setSelected(_selected);
        component.addActionListener(new CheckEvent(this));
//        addItemListener(new ItemListener() {
//
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                processKey(key);
//            }
//        });
    }

    public void setSelected(boolean _b) {
        component.setSelected(_b);
    }

    public void setBackground(Color _bg) {
        component.setBackground(_bg);
    }

    @Override
    public JCheckBox getComponent() {
        return component;
    }

    String getKey() {
        return key;
    }

    protected abstract void processKey(String _key);
}
