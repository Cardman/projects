package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.displaying.DealtHandBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDealtHandBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        DealtHandBelote ch_ = Net.importDealtHandBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.updateForBeginningGame(ch_);
    }
}
