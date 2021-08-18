package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.game.player.enums.Sex;
import aiki.gui.WindowAiki;

public class HeroSelect extends MouseAdapter {

    private WindowAiki window;

    private Sex sex;

    public HeroSelect(WindowAiki _window, Sex _sex) {
        window = _window;
        sex = _sex;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.changeSex(sex);
    }
}
