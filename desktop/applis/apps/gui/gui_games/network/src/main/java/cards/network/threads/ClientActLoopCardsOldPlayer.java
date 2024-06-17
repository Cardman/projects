package cards.network.threads;

import cards.gui.containers.ContainerMulti;
import cards.network.common.before.OldPlayerCards;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ClientActLoopCardsOldPlayer implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ContainerMulti container_ = (ContainerMulti) _window.getNetg().getContainerGame();
        OldPlayerCards o_ = new OldPlayerCards();
        o_.setIndex(NumberUtil.parseInt(_parts.get(0)));
        o_.setTarget(NumberUtil.parseInt(_parts.get(1)));
        o_.setPseudo(_parts.get(2));
        container_.getContainerMultiContent().updateAfter(o_);
    }
}
