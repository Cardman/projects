package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.PlayingCardTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsPlayingTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        PlayingCardTarot ch_ = Net.importPlayingTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        if (ch_.isRefreshing()) {
            containerBelote_.refreshHand(ch_);
            return;
        }
        containerBelote_.displayPlayedCard(ch_);
    }
}
