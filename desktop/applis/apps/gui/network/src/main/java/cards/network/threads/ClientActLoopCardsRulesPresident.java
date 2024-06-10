package cards.network.threads;

import cards.gui.containers.ContainerMultiPresident;
import cards.president.RulesPresident;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsRulesPresident implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        RulesPresident ch_ = Net.importRulesPresident(_parts,0);
        ContainerMultiPresident containerBelote_ = (ContainerMultiPresident) _window.getNetg().getContainerGame();
        containerBelote_.updateRules(ch_);
    }
}
