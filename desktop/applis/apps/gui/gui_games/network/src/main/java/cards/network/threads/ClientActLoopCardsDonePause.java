package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsDonePause implements IntClientActLoopCards {

    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        container_.pauseBetweenTrick();
    }
}
