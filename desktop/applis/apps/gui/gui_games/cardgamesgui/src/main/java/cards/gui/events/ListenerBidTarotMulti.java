package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.BiddingTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ListenerBidTarotMulti extends AbsMouseListenerRel {

    private ContainerMultiTarot container;
    private BidTarot enchere;

    public ListenerBidTarotMulti(ContainerMultiTarot _container,BidTarot _enchere) {
        container = _container;
        enchere = _enchere;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        BiddingTarot bid_ = new BiddingTarot();
        bid_.setBid(enchere);
        bid_.setPlace(container.getIndexInGame());
        String lg_ = container.getOwner().getLanguageKey();
        bid_.setLocale(lg_);
        container.getOwner().sendObject(bid_);
    }
}
