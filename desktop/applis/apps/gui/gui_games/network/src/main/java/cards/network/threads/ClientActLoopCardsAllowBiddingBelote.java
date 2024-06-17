package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsAllowBiddingBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        AllowBiddingBelote ch_ = Net.importAllowBiddingBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.canBidBelote(ch_);
    }
}
