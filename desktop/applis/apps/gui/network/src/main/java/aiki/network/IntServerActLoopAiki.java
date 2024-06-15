package aiki.network;

import code.network.NetCommon;
import code.util.CustList;

public interface IntServerActLoopAiki {
    void loop(CustList<String> _input, NetAiki _instance, NetCommon _common);
}
