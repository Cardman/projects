package cards.gui.events;

import cards.facade.enumerations.GameEnum;
import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class ListenerBeginGameMulti implements AbsActionListener {
    private GameEnum jeuBouton;
    private WindowNetWork window;
    public ListenerBeginGameMulti(GameEnum _pj, WindowNetWork _window){
        jeuBouton=_pj;
        window = _window;
    }
    @Override
    public void action() {
        window.beginGame(jeuBouton);
    }
}
