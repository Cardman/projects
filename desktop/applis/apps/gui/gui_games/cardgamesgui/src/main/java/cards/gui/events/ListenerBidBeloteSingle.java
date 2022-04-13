package cards.gui.events;

import cards.belote.BidBeloteSuit;
import cards.gui.animations.AnimationBidBelote;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.events.AbsActionListener;

public class ListenerBidBeloteSingle implements AbsActionListener {

    private ContainerSingleBelote container;
    private BidBeloteSuit texte = new BidBeloteSuit();
    private boolean clicked;
    public ListenerBidBeloteSingle(ContainerSingleBelote _container,BidBeloteSuit _texteBouton) {
        container = _container;
        texte=_texteBouton;
    }

    @Override
    public void action() {
        if (clicked) {
            return;
        }
        clicked = true;
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        container.setContratUtilisateurBelote(texte);
        container.thread(new AnimationBidBelote(container));
    }
}
