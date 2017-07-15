package aiki.gui.components.checks;
import javax.swing.JCheckBox;

public abstract class CheckBox extends JCheckBox {

    private String key;

    public CheckBox(String _key, String _text, boolean _selected) {
        key = _key;
        setText(_text);
        setSelected(_selected);
        addActionListener(new CheckEvent(this));
//        addItemListener(new ItemListener() {
//
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                processKey(key);
//            }
//        });
    }

    String getKey() {
        return key;
    }

    protected abstract void processKey(String _key);
}
