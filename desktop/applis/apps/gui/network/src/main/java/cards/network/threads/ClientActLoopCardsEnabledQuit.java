package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsEnabledQuit implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        if (((ContainerMulti) _window.getNetg().getContainerGame()).getContainerMultiContent().isHasCreatedServer()) {
            _window.getMultiStop().setEnabled(true);
        }
    }
}
