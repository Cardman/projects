package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.StringMap;

public final class ValidateMessages implements AbsActionListener {
    private final CustList<EditMessageRow> messages;
    private final WindowCdmEditor windowCdmEditor;

    public ValidateMessages(CustList<EditMessageRow> _m, WindowCdmEditor _ed) {
        this.messages = _m;
        windowCdmEditor = _ed;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogAliases().setVisible(false);
        int len_ = messages.size();
        StringMap<String> messages_ = new StringMap<String>();
        for (int i = 0; i < len_; i++) {
            EditMessageRow e_ = messages.get(i);
            String value_ = e_.getContent().getText();
            if (value_.isEmpty()) {
                continue;
            }
            messages_.addEntry(e_.getKey(), value_);
        }
        windowCdmEditor.setLgMessages(messages_);
        windowCdmEditor.afterChangingSyntaxPreferences();
    }
}
