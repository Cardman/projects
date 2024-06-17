package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.BiddingTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsBiddingTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        BiddingTarot ch_ = Net.importBiddingTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.displayLastBid(ch_);
    }
}
