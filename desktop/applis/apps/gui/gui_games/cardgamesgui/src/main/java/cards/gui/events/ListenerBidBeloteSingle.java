package cards.gui.events;

import cards.belote.BidBeloteSuit;
import cards.gui.animations.AnimationBidBelote;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
        container.setAnimContratBelote(new AnimationBidBelote(container));
        container.getOwner().getThreadFactory().newStartedThread(container.getAnimContratBelote());
    }
}
