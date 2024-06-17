package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.tarot.ResultsTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsResultsTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ResultsTarot ch_ = Net.importGameTarot(_parts);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.endGame(ch_);
    }
}
