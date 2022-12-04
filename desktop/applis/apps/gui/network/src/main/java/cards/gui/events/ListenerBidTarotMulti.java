package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.BiddingTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.events.AbsActionListener;

public class ListenerBidTarotMulti implements AbsActionListener {

    private ContainerMultiTarot container;
    private BidTarot enchere;

    public ListenerBidTarotMulti(ContainerMultiTarot _container,BidTarot _enchere) {
        container = _container;
        enchere = _enchere;
    }

    @Override
    public void action() {
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        BiddingTarot bid_ = new BiddingTarot();
        bid_.setBid(enchere);
        bid_.setPlace(container.getIndexInGame());
        String lg_ = container.getOwner().getLanguageKey();
        bid_.setLocale(lg_);
        container.window().sendObject(bid_);
    }
}
