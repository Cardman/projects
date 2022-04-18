package cards.gui.events;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class ListenerBeginGame implements AbsActionListener {
    private GameEnum jeuBouton;
    private WindowCards window;
    public ListenerBeginGame(GameEnum _pj, WindowCards _window){
        jeuBouton=_pj;
        window = _window;
    }
    @Override
    public void action() {
        window.beginGame(jeuBouton);
    }
}
