package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;

public class ConfirmNewGameEvent implements AbsActionListener {

    private final WindowAiki window;
    private final AbsTextField nickname;

    public ConfirmNewGameEvent(WindowAiki _w, AbsTextField _txt) {
        window = _w;
        nickname = _txt;
    }

    @Override
    public void action() {
        window.confirmNewGame(nickname.getText());
    }
}
