package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.belote.BidBeloteSuit;
import cards.gui.animations.AnimationBidBelote;
import cards.gui.containers.ContainerSingleBelote;

public class ListenerBidBeloteSingle extends MouseAdapter {

    private ContainerSingleBelote container;
    private BidBeloteSuit texte = new BidBeloteSuit();
    private boolean clicked;
    public ListenerBidBeloteSingle(ContainerSingleBelote _container,BidBeloteSuit _texteBouton) {
        container = _container;
        texte=_texteBouton;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (clicked) {
            return;
        }
        clicked = true;
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        container.setContratUtilisateurBelote(texte);
        container.setAnimContratBelote(new AnimationBidBelote(container));
        container.getAnimContratBelote().start();
    }
}
