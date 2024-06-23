package cards.network.threads;

import cards.belote.GameBelote;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.tarot.GameTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class ServerActLoopCardsActedByClientBid extends ServerActLoopCardsActedByClientFull {
    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        if (game_.keepBidding()) {
            byte place_ = game_.playerHavingToBid();
            if (Net.isHumanPlayer(place_, _common)) {
//            if (Net.isHumanPlayer(place_, _instance, _common))
                AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                allowedBids_.setBids(game_.getGameBeloteBid().allowedBids());
                allowedBids_.setBid(game_.getBid());
                NetGroupFrame.trySendString(Net.exportAllowBiddingBelote(allowedBids_), Net.getSocketByPlace(place_, _common));
                return;
            }
            //Les "robots" precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_fct, 1000);
//                if (Net.getGames(_instance).partieBelote().hasBid(place_)) {
//                    return;
//                }
            BiddingBelote bid_ = new BiddingBelote();
            bid_.setPlace(place_);
            bid_.setBidBelote(Net.getGames(_instance).partieBelote().bid(_instance.getIa().getBelote()));
            //bid_.setLocale(Constants.getDefaultLanguage());
//                bid_.setLocale("");
            for (byte p : Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(Net.exportClientBiddingBelote(bid_), Net.getSocketByPlace(p, _common));
            }
            return;
        }
        if (!game_.getBid().jouerDonne()) {
            endGameBelote(_instance, _common);
            return;
        }
        if (game_.getRegles().getDealing().getDiscarded() > 0) {
            if (Net.isHumanPlayer(game_.getPreneur(), _common)) {
//            if (Net.isHumanPlayer(game_.getPreneur(), _instance, _common))
                game_.ajouterCartesUtilisateur();
                DiscardPhaseBelote dog_ = new DiscardPhaseBelote();
                dog_.setDiscard(game_.getDistribution().derniereMain());
                dog_.getDiscardPhase().setTakerIndex(game_.getPreneur());
                for (byte p : Net.activePlayers(_instance, _common)) {
                    update(p, game_, dog_);
                    NetGroupFrame.trySendString(Net.exportDiscardPhaseBelote(dog_), Net.getSocketByPlace(p, _common));
                }
                return;
            }
//                    Net.initAllReceived(_instance, _common);
            game_.ecarter(_instance.getIa().getBelote());
            game_.completerDonne();
            playingBeloteCard(_instance, _fct, _common);
            return;
        }
        game_.completerDonne();
//                        if (game_.completedDeal()) {
//                            return;
//                        }
        for (byte p : Net.activePlayers(_instance, _common)) {
            RefreshHandBelote hand_ = new RefreshHandBelote();
            hand_.setRefreshedHand(game_.getDistribution().hand(p));
            //hand_.setLocale(Constants.getDefaultLanguage());
//                            hand_.setLocale("");
            NetGroupFrame.trySendString(Net.exportRefreshHandBelote(hand_), Net.getSocketByPlace(p, _common));
        }
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        if (!game_.keepBidding()) {
            processAfterBidTarot(game_, _instance,_fct, _common);
            return;
        }
        byte place_ = game_.playerHavingToBid();
        if (Net.isHumanPlayer(place_, _common)) {
//        if (Net.isHumanPlayer(place_, _instance, _common))
            AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
            allowedBids_.setBids(game_.allowedBids());
            allowedBids_.setMaxBid(game_.getContrat());
            NetGroupFrame.trySendString(Net.exportAllowBiddingTarot(allowedBids_), Net.getSocketByPlace(place_, _common));
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
            NetGroupFrame.trySendString(Net.exportClientBiddingTarot(bid_), Net.getSocketByPlace(p, _common));
        }
    }
}
