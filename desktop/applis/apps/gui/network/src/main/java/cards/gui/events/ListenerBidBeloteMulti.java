package cards.gui.events;

import cards.belote.BidBeloteSuit;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.BiddingBelote;
import code.gui.events.AbsActionListener;

public class ListenerBidBeloteMulti implements AbsActionListener {

    private ContainerMultiBelote container;
    private BidBeloteSuit texte = new BidBeloteSuit();

    public ListenerBidBeloteMulti(
            ContainerMultiBelote _container,
            BidBeloteSuit _texteBouton) {
        container = _container;
        texte = _texteBouton;
    }

    @Override
    public void action() {
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(container.getIndexInGame());
        bid_.setBidBelote(texte);
        String lg_ = container.getOwner().getLanguageKey();
        bid_.setLocale(lg_);
        container.window().sendObject(bid_);
    }
}
