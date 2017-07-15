package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.animations.AnimationBidTarot;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.enumerations.BidTarot;

public class ListenerBidTarotSingle extends MouseAdapter {

    private ContainerSingleTarot container;
    private BidTarot enchere;
    private boolean clicked;

    public ListenerBidTarotSingle(ContainerSingleTarot _container, BidTarot _enchere) {
        container = _container;
        enchere = _enchere;
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
        container.setContratUtilisateur(enchere);
        container.setAnimContratTarot(new AnimationBidTarot(container));
        container.getAnimContratTarot().start();

    }

}
