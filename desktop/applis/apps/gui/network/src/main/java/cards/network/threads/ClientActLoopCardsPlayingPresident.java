package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.actions.PlayingCardPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsPlayingPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        PlayingCardPresident ch_ = Net.importPlayingPresident(_parts);
        ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        if (ch_.isRefreshing()) {
            containerPresident_.refreshHand(ch_);
            return;
        }
        containerPresident_.displayPlayedCard(ch_);
    }
}
