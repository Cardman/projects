package cards.network.threads;

import cards.president.RulesPresident;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsRulesPresident implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        RulesPresident playerActionBeforeGame_ = Net.importRulesPresident(_input,0);
        String str_ = Net.exportClientRulesPresident(playerActionBeforeGame_).toString();
        _common.resend(str_);
    }
}
