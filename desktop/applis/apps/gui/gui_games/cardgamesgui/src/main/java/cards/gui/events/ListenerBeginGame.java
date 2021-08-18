package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;

public class ListenerBeginGame extends MouseAdapter {
    private GameEnum jeuBouton;
    private WindowCards window;
    public ListenerBeginGame(GameEnum _pj, WindowCards _window){
        jeuBouton=_pj;
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        window.beginGame(jeuBouton);
    }
}
