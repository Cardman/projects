package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.BiddingBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsBiddingBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        BiddingBelote ch_ = Net.importBiddingBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.displayLastBid(ch_);
    }
}
