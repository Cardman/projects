package cards.network.threads;

import cards.gui.containers.ContainerMultiTarot;
import cards.tarot.RulesTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsRulesTarot implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        RulesTarot ch_ = Net.importRulesTarot(_parts,0);
        ContainerMultiTarot containerBelote_ = (ContainerMultiTarot) _window.getNetg().getContainerGame();
        containerBelote_.updateRules(ch_);
    }
}
