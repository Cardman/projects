package code.gui.events;

import code.gui.AbsChangeLanguage;
import code.gui.LanguageDialogButtons;

public class LanguageChoiceButton implements AbsActionListener {

    private final String locale;

    private final LanguageDialogButtons window;
    private final AbsChangeLanguage language;

    public LanguageChoiceButton(String _locale, LanguageDialogButtons _window, AbsChangeLanguage _frame) {
        locale = _locale;
        window = _window;
        language = _frame;
    }

    @Override
    public void action() {
        window.setLanguage(locale);
        language.changeLanguage(locale);
    }
}
