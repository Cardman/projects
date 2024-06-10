package cards.network.threads;

import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsOldPlayer implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        int target_ = NumberUtil.parseInt(_input.get(1));
        NetGroupFrame.trySendString(Net.exportOldPlayer(NumberUtil.parseInt(_input.get(0)),target_,_input.get(2)), _common.getSockets().getVal(target_));
    }
}
