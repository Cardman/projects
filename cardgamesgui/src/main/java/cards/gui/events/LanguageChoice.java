package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.events.SetterLanguage;

public class LanguageChoice extends MouseAdapter {

    private String locale;

    private SetterLanguage window;

    public LanguageChoice(String _locale, SetterLanguage _window) {
        locale = _locale;
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.setLanguage(locale);
    }

}
