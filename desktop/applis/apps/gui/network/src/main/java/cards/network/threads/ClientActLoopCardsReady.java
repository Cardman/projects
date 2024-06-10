package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import cards.network.common.before.Ready;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsReady implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        Ready ch_ = Net.importReady(_parts);
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        container_.getContainerMultiContent().updateReady(ch_);
    }
}
