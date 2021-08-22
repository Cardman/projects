package code.gui.events;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;

public class LanguageChoice extends AbsMouseListenerRel {

    private String locale;

    private SetterLanguage window;

    public LanguageChoice(String _locale, SetterLanguage _window) {
        locale = _locale;
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.setLanguage(locale);
    }

}
