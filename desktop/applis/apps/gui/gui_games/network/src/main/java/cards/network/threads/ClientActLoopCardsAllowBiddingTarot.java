package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.unlock.AllowBiddingTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsAllowBiddingTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        AllowBiddingTarot ch_ = Net.importAllowBiddingTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.canBidTarot(ch_);
    }
}
