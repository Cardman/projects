package cards.network.threads;

import cards.belote.RulesBelote;
import cards.gui.containers.ContainerMultiBelote;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsRulesBelote implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        RulesBelote ch_ = Net.importRulesBelote(_parts,0);
        ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) _window.getNetg().getContainerGame();
        containerBelote_.updateRules(ch_);
    }
}
