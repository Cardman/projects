package aiki.network;

import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopAikiPlayer implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        int index_ = NumberUtil.parseInt(_input.get(0));
        NetGroupFrame.trySendString(NetAiki.exportInitTrading(), _common.getSockets().getVal(index_));
    }
}
