package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.president.ResultsPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsResultsPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ResultsPresident ch_ = Net.importGamePresident(_parts);
        ContainerMultiPresident containerBelote_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerBelote_.endGame(ch_);
    }
}
