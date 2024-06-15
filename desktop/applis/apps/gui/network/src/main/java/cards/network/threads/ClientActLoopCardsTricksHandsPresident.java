package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.president.TricksHandsPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsTricksHandsPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        TricksHandsPresident ch_ = Net.importTricksHandsPresident(_parts);
        ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerPresident_.showTricksHands(ch_);
    }
}
