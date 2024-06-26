package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import cards.network.common.before.PlayerCards;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ClientActLoopCardsNewPlayer implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        PlayerCards n_ = new PlayerCards();
        n_.setIndex(NumberUtil.parseInt(_parts.get(0)));
        n_.setPseudo(_parts.get(1));
        container_.getContainerMultiContent().updateAfter(n_);
        NetGroupFrame.trySendString(Net.exportOldPlayer(container_.getContainerMultiContent().getNoClient(), n_.getIndex(),_window.getDialogServerContent().getNickname().getText()), _socket);
    }
}
