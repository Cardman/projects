package cards.network.threads;

import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsDonePause extends ServerActLoopCardsActedByClientAll {

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        playingBeloteCard(_instance,_fct, _common);
    }

    @Override
    protected void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        playingPresidentCard(_instance,_fct, _common);
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        playingTarotCard(_instance,_fct, _common);
    }
}
