package cards.network.threads;

import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public interface IntServerActLoopCards {
    void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);
}
