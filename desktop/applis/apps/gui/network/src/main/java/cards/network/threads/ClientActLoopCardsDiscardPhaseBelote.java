package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.DiscardPhaseBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDiscardPhaseBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        DiscardPhaseBelote ch_ = Net.importDiscardPhaseBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.voirEcart(ch_);
    }
}
