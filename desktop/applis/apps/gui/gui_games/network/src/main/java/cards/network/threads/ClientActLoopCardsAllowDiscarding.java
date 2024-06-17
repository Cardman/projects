package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.unlock.AllowDiscarding;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsAllowDiscarding implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        AllowDiscarding ch_ = Net.importAllowDiscarding(_parts);
        ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerPresident_.canDiscardPresident(ch_);
    }
}
