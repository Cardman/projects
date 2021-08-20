package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class ManageNicknameEvent implements AbsActionListener {

    private WindowCards window;

    public ManageNicknameEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageNicknames();
    }
}
