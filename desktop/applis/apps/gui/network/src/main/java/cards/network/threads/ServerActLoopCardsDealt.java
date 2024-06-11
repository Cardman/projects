package cards.network.threads;

import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.Bytes;
import code.util.CustList;

public final class ServerActLoopCardsDealt extends ServerActLoopCardsActedByClientAll {

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        byte place_ = Net.getGames(_instance).partieBelote().playerHavingToBid();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
            allowedBids_.setBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
            allowedBids_.setBid(Net.getGames(_instance).partieBelote().getBid());
            NetGroupFrame.trySendString(Net.exportAllowBiddingBelote(allowedBids_), Net.getSocketByPlace(place_, _common));
            return;
        }
        //Les "robots" precedant l'utilisateur annoncent leur contrat
        ThreadUtil.sleep(_fct,1000);
//                if (Net.getGames(_instance).partieBelote().hasBid(place_)) {
//                    return;
//                }
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(place_);
        bid_.setBidBelote(Net.getGames(_instance).partieBelote().bid(_instance.getIa().getBelote()));
        //bid_.setLocale(Constants.getDefaultLanguage());
//                bid_.setLocale("");
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportBiddingBelote(bid_), Net.getSocketByPlace(p, _common));
        }
    }

    @Override
    protected void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GamePresident g_ = Net.getGames(_instance).partiePresident();
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        if (g_.availableSwitchingCards()) {
            Bytes pl_ = Net.activePlayers(_instance, _common);
            Bytes humWin_ = g_.getWinners(pl_);
            Bytes humLos_ = g_.getLoosers(pl_);
            if (!humWin_.isEmpty()) {
                //Display discarding
                for (byte p: humWin_) {
                    byte l_ = g_.getMatchingLoser(p);
                    NetGroupFrame.trySendString(Net.exportAllowDiscarding(g_.getSwitchedCards().get(l_)), Net.getSocketByPlace(p, _common));
                }
//                        CustList<Byte> humLosReceiving_ = new CustList<>();
//                        for (Byte p: humLos_) {
//                            byte w_ = g_.getMatchingWinner(p);
//                            if (!humWin_.containsObj(w_)) {
//                                humLosReceiving_.add(p);
//                            }
//                        }
//                        ReceivedGivenCards dis_ = new ReceivedGivenCards();
//                        for (Byte p: humLosReceiving_) {
//                            byte w_ = g_.getMatchingWinner(p);
//                            dis_.setReceived(g_.getSwitchedCards().getVal(w_));
//                            dis_.setGiven(g_.getSwitchedCards().getVal(p));
//                            dis_.setNewHand(g_.getDistribution().main(w_));
//                            Net.sendObject(Net.getSocketByPlace(p), dis_);
//                            //refresh hands of losers
//                        }
                return;
            }
            if (!humLos_.isEmpty()) {
                //Display switched cards
                for (byte p: humLos_) {
                    byte w_ = g_.getMatchingWinner(p);
                    NetGroupFrame.trySendString(Net.exportReceivedGivenCards(g_.getSwitchedCards().get(w_),g_.getSwitchedCards().get(p),g_.getDeal().hand(w_)), Net.getSocketByPlace(p, _common));
                }
                return;
            }
            g_.giveWorstCards(_instance.getIa().getPresident());
        }
        //Go playing
        playingPresidentCard(_instance,_fct,_common);
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (!Net.getGames(_instance).partieTarot().avecContrat()) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
//                game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
//                game_.setPliEnCours(true);
            game_.firstLead();
            playingTarotCard(_instance,_fct, _common);
            return;
        }
        byte place_ = Net.getGames(_instance).partieTarot().playerHavingToBid();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
            allowedBids_.setBids(Net.getGames(_instance).partieTarot().allowedBids());
            allowedBids_.setMaxBid(Net.getGames(_instance).partieTarot().getContrat());
            Net.sendObject(Net.getSocketByPlace(place_, _common), allowedBids_);
            return;
        }
        //Les "robots" precedant l'utilisateur annoncent leur contrat
        ThreadUtil.sleep(_fct,1000);
//            if (Net.getGames(_instance).partieTarot().hasBid(place_)) {
//                return;
//            }
        BiddingTarot bid_ = new BiddingTarot();
        bid_.setPlace(place_);
        bid_.setBid(Net.getGames(_instance).partieTarot().playerHasAlreadyBidded(_instance.getIa().getTarot()));
        //bid_.setLocale(Constants.getDefaultLanguage());
//            bid_.setLocale("");
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
        }
    }
}
