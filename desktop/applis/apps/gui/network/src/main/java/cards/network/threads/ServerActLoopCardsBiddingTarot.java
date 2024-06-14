package cards.network.threads;

import cards.network.tarot.actions.BiddingTarot;
import cards.tarot.GameTarot;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsBiddingTarot implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        BiddingTarot playerActionBeforeGame_ = Net.importBiddingTarot(_input);
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        game_.ajouterContrat(_instance.getIa().getTarot().strategieContratUser(playerActionBeforeGame_.getBid()));
        Net.initAllReceived(_instance, _common);
        String str_ = Net.exportClientBiddingTarot(playerActionBeforeGame_);
        _common.resend(str_);
    }
}
