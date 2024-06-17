package cards.network.threads;

import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsValidateDiscardBelote implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        char ch_ = _input.get(0).charAt(0);
        if (Net.VALIDATE_DISCARD_SLAM == ch_) {
            Net.getGames(_instance).partieBelote().ajouterChelemUtilisateur();
        }
        Net.getGames(_instance).partieBelote().validateDiscard();
        ServerActLoopCardsActedByClientReceived.playingBeloteCard(_instance,_fct, _common);
    }
}
