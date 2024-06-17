package cards.network.threads;

import cards.belote.GameBelote;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsActedByClientCompletedHandBelote extends ServerActLoopCardsActedByClientReceived {
    @Override
    protected void loopReceive(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        game_.setEntameurPremier();
        playingBeloteCard(_instance,_fct,_common);
    }
}
