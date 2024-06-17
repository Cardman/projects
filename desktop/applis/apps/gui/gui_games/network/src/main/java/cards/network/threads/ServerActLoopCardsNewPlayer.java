package cards.network.threads;

import code.gui.initialize.AbstractSocket;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsNewPlayer implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        int index_ = NumberUtil.parseInt(_input.get(0));
        for (EntryCust<Integer, AbstractSocket> p:_common.getSockets().entryList()) {
            if (p.getKey() != index_) {
                NetGroupFrame.trySendString(Net.exportNewPlayer(index_,_input.get(1)), p.getValue());
            }
        }
    }
}
