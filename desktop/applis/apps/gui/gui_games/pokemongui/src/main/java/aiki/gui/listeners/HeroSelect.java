package aiki.gui.listeners;

import aiki.game.player.enums.Sex;
import aiki.gui.WindowAiki;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class HeroSelect implements AbsMouseListenerIntRel {

    private WindowAiki window;

    private Sex sex;

    public HeroSelect(WindowAiki _window, Sex _sex) {
        window = _window;
        sex = _sex;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.changeSex(sex);
    }
}
