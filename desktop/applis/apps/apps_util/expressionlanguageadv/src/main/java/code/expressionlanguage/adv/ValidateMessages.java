package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class ValidateMessages implements AbsActionListener {
    private final StringMap<String> messages;
    private final WindowCdmEditor windowCdmEditor;

    public ValidateMessages(StringMap<String> _m, WindowCdmEditor _ed) {
        this.messages = _m;
        windowCdmEditor = _ed;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogAliases().setVisible(false);
        windowCdmEditor.setLgMessages(filterFields(messages));
        windowCdmEditor.afterChangingSyntaxPreferences();
    }

    static StringMap<String> filterFields(StringMap<String> _fields) {
        int len_ = _fields.size();
        StringMap<String> messages_ = new StringMap<String>();
        for (int i = 0; i < len_; i++) {
            String value_ = _fields.getValue(i);
            if (value_.isEmpty()) {
                continue;
            }
            messages_.addEntry(_fields.getKey(i), value_);
        }
        return messages_;
    }
}
