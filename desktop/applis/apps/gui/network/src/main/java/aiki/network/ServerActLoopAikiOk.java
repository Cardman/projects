package aiki.network;

import code.gui.initialize.AbstractSocket;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.core.BoolVal;

public final class ServerActLoopAikiOk implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        if (_common.allReady()) {
            for(AbstractSocket so_: _common.getSockets().values()){
                NetGroupFrame.trySendString(NetAiki.exportClientOk(), so_);
            }
            for (int i: _common.getReadyPlayers().getKeys()) {
                _common.getReadyPlayers().put(i, BoolVal.FALSE);
            }
        }
    }
}
