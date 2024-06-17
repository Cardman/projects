package aiki.network;

import aiki.network.stream.ReadyAiki;
import code.network.NetCommon;
import code.util.CustList;
import code.util.comparators.ComparatorBoolean;

public final class ServerActLoopAikiReady implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        ReadyAiki readyAiki_ = NetAiki.importReadyAiki(_input);
        int noClient_ = readyAiki_.getIndex();
        _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(readyAiki_.getContent().isReady()));
    }
}
