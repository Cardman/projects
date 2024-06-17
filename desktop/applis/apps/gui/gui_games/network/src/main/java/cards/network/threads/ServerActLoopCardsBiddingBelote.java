package cards.network.threads;

import cards.belote.GameBelote;
import cards.network.belote.actions.BiddingBelote;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsBiddingBelote implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        BiddingBelote playerActionBeforeGame_ = Net.importBiddingBelote(_input);
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        game_.ajouterContrat(_instance.getIa().getBelote().strategieContratUser(playerActionBeforeGame_.getBidBelote()));
        Net.initAllReceived(_instance, _common);
        String str_ = Net.exportClientBiddingBelote(playerActionBeforeGame_);
        _common.resend(str_);
    }
}
