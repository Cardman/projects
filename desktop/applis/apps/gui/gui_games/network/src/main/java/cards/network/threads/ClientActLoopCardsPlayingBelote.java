package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.PlayingCardBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsPlayingBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        PlayingCardBelote ch_ = Net.importPlayingBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        if (ch_.isRefreshing()) {
            containerBelote_.refreshHand(ch_);
            return;
        }
        containerBelote_.displayPlayedCard(ch_);
    }
}
