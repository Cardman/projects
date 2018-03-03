package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.util.consts.Constants;
import cards.belote.BidBeloteSuit;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.BiddingBelote;

public class ListenerBidBeloteMulti extends MouseAdapter {

    private ContainerMultiBelote container;
    private BidBeloteSuit texte = new BidBeloteSuit();

    public ListenerBidBeloteMulti(
            ContainerMultiBelote _container,
            BidBeloteSuit _texteBouton) {
        container = _container;
        texte = _texteBouton;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!container.isCanBid()) {
            return;
        }
        container.setCanBid(false);
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(container.getIndexInGame());
        bid_.setBidBelote(texte);
        bid_.setLocale(Constants.getLanguage());
        container.getOwner().sendObject(bid_);
    }
}
