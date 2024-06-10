package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.displaying.DealtHandPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDealtHandPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        DealtHandPresident ch_ = Net.importDealtHandPresident(_parts);
        ContainerMultiPresident containerBelote_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerBelote_.updateForBeginningGame(ch_);
    }
}
