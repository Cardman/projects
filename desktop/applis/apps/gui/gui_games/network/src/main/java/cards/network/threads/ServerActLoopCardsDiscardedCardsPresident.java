package cards.network.threads;

import cards.network.president.actions.DiscardedCardsPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.Ints;
import code.util.CustList;

public final class ServerActLoopCardsDiscardedCardsPresident implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        DiscardedCardsPresident dis_ = Net.importDiscardedCardsPresident(_input);
        Ints pl_ = Net.activePlayers(_instance, _common);
        HandPresident cards_ = dis_.getDiscarded();
        int player_ = dis_.getPlace();
        GamePresident g_ = Net.getGames(_instance).partiePresident();
        if (!g_.giveWorstCards(pl_, player_, cards_)) {
            return;
        }
//        Bytes humWin_ = g_.getWinners(pl_);
        Ints humLos_ = g_.getLoosers(pl_);
//        Bytes humLosReceiving_ = new Bytes();
//        for (byte p: humLos_) {
//            byte w_ = g_.getMatchingWinner(p);
//            if (humWin_.containsObj(w_)) {
//                humLosReceiving_.add(p);
//            }
//        }
//        if (!humLosReceiving_.isEmpty()) {
        if (!humLos_.isEmpty()) {
            //refresh hands of losers
            for (int p: humLos_) {
                int w_ = g_.getMatchingWinner(p);
                NetGroupFrame.trySendString(Net.exportReceivedGivenCards(g_.getSwitchedCards().get(w_),g_.getSwitchedCards().get(p),g_.getDeal().hand(p)), Net.getSocketByPlace(p, _common));
            }
            return;
        }
        ServerActLoopCardsActedByClientReceived.playingPresidentCard(_instance,_fct, _common);
    }
}
