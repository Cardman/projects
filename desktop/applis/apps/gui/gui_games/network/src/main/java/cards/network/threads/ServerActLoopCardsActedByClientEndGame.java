package cards.network.threads;

import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsActedByClientEndGame implements IntServerActLoopCards {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(NumberUtil.parseInt(_input.get(0)), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReady(_common);
            Net.setProgressingGame(false, _instance);
        }
    }
}
