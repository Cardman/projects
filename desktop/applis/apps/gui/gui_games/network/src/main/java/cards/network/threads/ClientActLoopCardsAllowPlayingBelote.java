package cards.network.threads;

import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsAllowPlayingBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        AllowPlayingBelote ch_ = Net.importAllowPlayingBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.canPlayBelote(ch_);
    }
}
