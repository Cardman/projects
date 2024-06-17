package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsRefreshHandBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        RefreshHandBelote ch_ = Net.importRefreshHandBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.refreshHand(ch_);
    }
}
