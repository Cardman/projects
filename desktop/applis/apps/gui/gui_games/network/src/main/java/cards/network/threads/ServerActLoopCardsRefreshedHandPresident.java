package cards.network.threads;

import cards.president.GamePresident;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.Bytes;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsRefreshedHandPresident implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer((byte) NumberUtil.parseInt(_input.get(0)), _instance);
        Bytes pl_ = Net.activePlayers(_instance, _common);
        GamePresident g_ = Net.getGames(_instance).partiePresident();
        if (Net.allReceivedAmong(g_.getLoosers(pl_), _instance)) {
            Net.initAllReceived(_instance, _common);
            //Go playing
            ServerActLoopCardsActedByClientReceived.playingPresidentCard(_instance,_fct, _common);
        }
    }
}
