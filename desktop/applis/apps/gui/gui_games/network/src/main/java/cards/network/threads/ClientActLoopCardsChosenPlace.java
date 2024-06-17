package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import cards.network.common.before.ChoosenPlace;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsChosenPlace implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ChoosenPlace ch_ = Net.importChosenPlace(_parts);
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        container_.getContainerMultiContent().updatePlaces(ch_);
    }
}
