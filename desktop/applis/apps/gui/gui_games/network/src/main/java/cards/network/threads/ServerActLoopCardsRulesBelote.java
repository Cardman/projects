package cards.network.threads;

import cards.belote.RulesBelote;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsRulesBelote implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        RulesBelote playerActionBeforeGame_ = Net.importRulesBelote(_input,0);
        String str_ = Net.exportClientRulesBelote(playerActionBeforeGame_).toString();
        _common.resend(str_);
    }
}
