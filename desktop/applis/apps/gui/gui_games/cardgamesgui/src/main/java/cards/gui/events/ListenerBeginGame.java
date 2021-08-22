package cards.gui.events;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import code.gui.*;
import code.gui.events.AbsMouseListenerRel;

public class ListenerBeginGame extends AbsMouseListenerRel {
    private GameEnum jeuBouton;
    private WindowCards window;
    public ListenerBeginGame(GameEnum _pj, WindowCards _window){
        jeuBouton=_pj;
        window = _window;
    }
    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.beginGame(jeuBouton);
    }
}
