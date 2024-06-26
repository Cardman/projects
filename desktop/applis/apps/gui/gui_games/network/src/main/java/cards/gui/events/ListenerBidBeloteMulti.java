package cards.gui.events;

import cards.belote.BidBeloteSuit;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.BiddingBelote;
import code.gui.events.AbsActionListener;

public final class ListenerBidBeloteMulti implements AbsActionListener {

    private final ContainerMultiBelote container;
    private final BidBeloteSuit texte;

    public ListenerBidBeloteMulti(
            ContainerMultiBelote _container,
            BidBeloteSuit _texteBouton) {
        container = _container;
        texte = _texteBouton;
    }

    @Override
    public void action() {
        container.clearBids();
        container.pack();
//        if (!container.isCanBid()) {
//            return;
//        }
//        container.setCanBid(false);
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(container.getContainerMultiContent().getIndexInGame());
        bid_.setBidBelote(texte);
//        String lg_ = container.getOwner().getLanguageKey();
//        bid_.setLocale(lg_);
        container.getContainerMultiContent().window().sendObject(bid_);
    }
}
