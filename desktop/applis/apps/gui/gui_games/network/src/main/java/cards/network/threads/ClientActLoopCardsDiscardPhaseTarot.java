package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.DiscardPhaseTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDiscardPhaseTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        DiscardPhaseTarot ch_ = Net.importDiscardPhaseTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.displayDog(ch_);
    }
}
