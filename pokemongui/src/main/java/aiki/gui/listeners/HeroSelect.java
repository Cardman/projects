package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.game.player.enums.Sex;
import aiki.gui.MainWindow;

public class HeroSelect extends MouseAdapter {

    private MainWindow window;

    private Sex sex;

    public HeroSelect(MainWindow _window, Sex _sex) {
        window = _window;
        sex = _sex;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.changeSex(sex);
    }
}
