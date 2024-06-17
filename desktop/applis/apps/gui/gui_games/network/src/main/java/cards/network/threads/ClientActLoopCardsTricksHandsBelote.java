package cards.network.threads;

import cards.belote.TricksHandsBelote;
import cards.gui.containers.ContainerMultiBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsTricksHandsBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        TricksHandsBelote ch_ = Net.importTricksHandsBelote(_parts);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.showTricksHands(ch_);
    }
}
