package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.unlock.AllowPlayingPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsAllowPlayingPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        AllowPlayingPresident ch_ = Net.importAllowPlayingPresident(_parts);
        ContainerMultiPresident containerBelote_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerBelote_.canPlayPresident(ch_);
    }
}
