package cards.network.threads;

import cards.tarot.RulesTarot;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsRulesTarot implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        RulesTarot playerActionBeforeGame_ = Net.importRulesTarot(_input,0);
        String str_ = Net.exportClientRulesTarot(playerActionBeforeGame_).toString();
        _common.resend(str_);
    }
}
