package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsTricksHandsTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        TricksHandsTarot ch_ = Net.importTricksHandsTarot(_parts);
        ContainerMultiTarot containerTarot_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerTarot_.showTricksHands(ch_);
    }
}
