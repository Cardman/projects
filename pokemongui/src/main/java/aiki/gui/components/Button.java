package aiki.gui.components;
import javax.swing.JButton;

import code.util.StringMap;
import code.util.consts.Constants;

public class Button extends JButton implements TranslatableComponent {

    private String key;

    private StringMap<StringMap<String>> messages;

    public Button() {
    }

    public Button(String _text) {
        super(_text);
    }

    public Button(StringMap<StringMap<String>> _messages, String _key) {
        super(_messages.getVal(Constants.getLanguage()).getVal(_key));
        messages = _messages;
        key = _key;
    }

    @Override
    public void translate() {
        setText(messages.getVal(Constants.getLanguage()).getVal(key));
    }
}
