package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import cards.network.common.before.IndexOfArrivingCards;
import cards.network.common.before.PlayerCards;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsIndexArrive implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        IndexOfArrivingCards index_ = Net.importIndexArrive(_parts);
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        container_.getContainerMultiContent().setNoClient(index_.getIndex());
        container_.updateFirst(index_);
        PlayerCards p_ = new PlayerCards();
        p_.setIndex(container_.getContainerMultiContent().getNoClient());
        String pseudo_ = _window.getDialogServerContent().getNickname().getText();
        p_.setPseudo(pseudo_);
        container_.getContainerMultiContent().updateAfter(p_);
        NetGroupFrame.trySendString(Net.exportNewPlayer(container_.getContainerMultiContent().getNoClient(),pseudo_), _socket);
    }
}
