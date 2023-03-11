package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class ValidateAliases implements AbsActionListener {
    private final StringMap<String> messages;
    private final StringMap<String> aliases;
    private final StringMap<String> keyWords;
    private final WindowCdmEditor windowCdmEditor;

    public ValidateAliases(StringMap<String> _m, StringMap<String> _a, StringMap<String> _k, WindowCdmEditor _ed) {
        this.messages = _m;
        this.aliases = _a;
        keyWords = _k;
        windowCdmEditor = _ed;
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

    @Override
    public void action() {
        windowCdmEditor.setLgMessages(filterFields(messages));
        windowCdmEditor.setLgAliases(filterFields(aliases));
        windowCdmEditor.setLgKeyWords(filterFields(keyWords));
        windowCdmEditor.afterChangingSyntaxPreferences();
    }

}
