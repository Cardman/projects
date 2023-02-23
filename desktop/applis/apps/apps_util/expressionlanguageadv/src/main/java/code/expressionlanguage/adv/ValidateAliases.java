package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class ValidateAliases implements AbsActionListener {
    private final StringMap<String> aliases;
    private final StringMap<String> keyWords;
    private final WindowCdmEditor windowCdmEditor;

    public ValidateAliases(StringMap<String> _a, StringMap<String> _k, WindowCdmEditor _ed) {
        this.aliases = _a;
        keyWords = _k;
        windowCdmEditor = _ed;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogAliases().setVisible(false);
        windowCdmEditor.setLgAliases(ValidateMessages.filterFields(aliases));
        windowCdmEditor.setLgKeyWords(ValidateMessages.filterFields(keyWords));
        windowCdmEditor.afterChangingSyntaxPreferences();
    }

}
