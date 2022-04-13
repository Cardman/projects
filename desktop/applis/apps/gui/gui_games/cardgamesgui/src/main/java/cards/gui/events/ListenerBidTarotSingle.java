package cards.gui.events;

import cards.gui.animations.AnimationBidTarot;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.events.AbsActionListener;

public class ListenerBidTarotSingle implements AbsActionListener {

    private ContainerSingleTarot container;
    private BidTarot enchere;
    private boolean clicked;

    public ListenerBidTarotSingle(ContainerSingleTarot _container, BidTarot _enchere) {
        container = _container;
        enchere = _enchere;
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
        container.setContratUtilisateur(enchere);
        container.thread(new AnimationBidTarot(container));

    }

}
