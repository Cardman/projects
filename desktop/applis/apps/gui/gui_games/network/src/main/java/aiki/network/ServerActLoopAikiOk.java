package aiki.network;

import code.network.NetCommon;
import code.util.CustList;
import code.util.core.BoolVal;

public final class ServerActLoopAikiOk implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        if (_common.allReady()) {
            _common.resend(NetAiki.exportClientOk());
            for (int i: _common.getReadyPlayers().getKeys()) {
                _common.getReadyPlayers().put(i, BoolVal.FALSE);
            }
        }
    }
}
