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
        int len_ = messages.size();
        StringMap<String> messages_ = new StringMap<String>();
        for (int i = 0; i < len_; i++) {
            String value_ = messages.getValue(i);
            if (value_.isEmpty()) {
                continue;
            }
            messages_.addEntry(messages.getKey(i), value_);
        }
        windowCdmEditor.setLgMessages(messages_);
        windowCdmEditor.afterChangingSyntaxPreferences();
    }
}
