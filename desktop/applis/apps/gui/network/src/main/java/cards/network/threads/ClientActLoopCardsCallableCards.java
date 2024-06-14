package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsCallableCards implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ContainerMultiTarot containerTarot_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerTarot_.displayCalling(Net.importCallableCards(_parts));
    }
}
