package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsReceivedGivenCards implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ReceivedGivenCards ch_ = Net.importReceivedGivenCards(_parts);
        ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerPresident_.refreshLoserHand(ch_);
    }
}
