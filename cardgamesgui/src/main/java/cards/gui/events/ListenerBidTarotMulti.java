package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.BiddingTarot;
import cards.tarot.enumerations.BidTarot;

public class ListenerBidTarotMulti extends MouseAdapter {

    private ContainerMultiTarot container;
    private BidTarot enchere;

    public ListenerBidTarotMulti(ContainerMultiTarot _container,BidTarot _enchere) {
        container = _container;
        enchere = _enchere;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        BiddingTarot bid_ = new BiddingTarot();
        bid_.setBid(enchere);
        bid_.setPlace(container.getIndexInGame());
        container.getOwner().sendObject(bid_);
    }
}
