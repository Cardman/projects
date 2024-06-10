package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.displaying.DealtHandTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDealtHandTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        DealtHandTarot ch_ = Net.importDealtHandTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.updateForBeginningGame(ch_);
    }
}
