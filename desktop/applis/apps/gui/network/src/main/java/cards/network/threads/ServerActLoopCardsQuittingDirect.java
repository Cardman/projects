package cards.network.threads;

import cards.network.common.Quit;
import cards.network.sml.DocumentWriterCardsMultiUtil;
import code.gui.initialize.AbstractSocket;
import code.network.Exiting;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.Bytes;
import code.util.CustList;
import code.util.Ints;

public final class ServerActLoopCardsQuittingDirect implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Quit q_ = Net.importQuitting(_input);
        if (Net.getGames(_instance).enCoursDePartie() || q_.isServer()) {

            Bytes pls_ = Net.activePlayers(_instance, _common);
            for (byte p: pls_) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setServer(true);
                if (p == q_.getPlace()) {
                    forcedBye_.setClosing(q_.isClosing());
                }
                NetGroupFrame.trySendString(Net.exportExiting(forcedBye_), Net.getSocketByPlace(p, _common));
            }
            _common.getNicknames().clear();
            Net.getGames(_instance).finirParties();
            _common.getPlacesPlayers().clear();
            _common.getSockets().clear();
            _common.getConnectionsServer().clear();
            _common.getReadyPlayers().clear();
            return;
        }
        Net.quit(q_.getPlace(), _instance);
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setServer(false);
        forcedBye_.setClosing(q_.isClosing());
        Ints placesPlayersByValue_ = Net.getPlacesPlayersByValue(q_.getPlace(), _common);
        if (!placesPlayersByValue_.isEmpty()) {
            removePlayer(placesPlayersByValue_.first(), forcedBye_, _common);
        }
//            quitProcess(q_, _instance,_fct,_common);
    }

    static void removePlayer(int _player, Exiting _bye, NetCommon _common) {
        AbstractSocket socket_ = _common.getSockets().getVal(_player);
        _common.getSockets().removeKey(_player);
        _common.getConnectionsServer().removeKey(_player);
        _common.getReadyPlayers().removeKey(_player);
        _common.getPlacesPlayers().removeKey(_player);
        NetGroupFrame.trySendString(Net.exportExiting(_bye), socket_);
    }
//    private static void quitProcess(Quit _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
//        if (_readObject.isServer()) {
//            if (!Net.delegateServer(_readObject, _instance)) {
//                return;
//            }
//        }
//        Net.quit(_readObject.getPlace(), _instance);
//        ByeCards forcedBye_ = new ByeCards();
//        forcedBye_.setForced(false);
//        forcedBye_.setServer(false);
//        forcedBye_.setClosing(_readObject.isClosing());
//        Ints placesPlayersByValue_ = Net.getPlacesPlayersByValue(_readObject.getPlace(), _instance);
//        if (!placesPlayersByValue_.isEmpty()) {
//            Net.removePlayer(placesPlayersByValue_.first(), forcedBye_, _instance);
//        }
//        if (Net.getGames(_instance).enCoursDePartieBelote()) {
//            GameBelote game_ = Net.getGames(_instance).partieBelote();
//            if (!game_.keepPlayingCurrentGame()) {
//                return;
//            }
//            if (game_.keepBidding()) {
//                byte place_ = game_.playerHavingToBid();
//                if (place_ == _readObject.getPlace()) {
//                    ThreadUtil.sleep(_fct,1000);
//                    if (game_.playerHasAlreadyBidded(place_)) {
//                        return;
//                    }
//                    BiddingBelote bid_ = new BiddingBelote();
//                    bid_.setPlace(place_);
//                    bid_.setBidBelote(game_.getLastBid());
//                    bid_.setLocale(Constants.getDefaultLanguage());
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
//                    }
//                }
//            } else {
//                byte place_ = game_.playerHavingToPlay();
//                if (place_ == _readObject.getPlace()) {
//                    ThreadUtil.sleep(_fct,800);
//                    if (game_.currentPlayerHasPlayed(place_)) {
//                        return;
//                    }
//                    CardBelote card_ = game_.getCarteJouee();
//                    boolean declareBeloteRebelote_ = game_.getAnnoncesBeloteRebelote(place_).contient(card_);
//                    PlayingCardBelote cardDto_ = new PlayingCardBelote();
//                    cardDto_.setPlace(place_);
//                    cardDto_.setPlayedCard(card_);
//                    cardDto_.setDeclaringBeloteRebelote(declareBeloteRebelote_);
//                    cardDto_.setDeclare(game_.getAnnonce(place_));
//                    cardDto_.setLocale(Constants.getDefaultLanguage());
//                    Net.initAllReceived(_instance);
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), cardDto_);
//                    }
//                }
//            }
//            return;
//        }
//        if (Net.getGames(_instance).enCoursDePartiePresident()) {
//            GamePresident game_ = Net.getGames(_instance).partiePresident();
//            if (!game_.keepPlayingCurrentGame()) {
//                return;
//            }
//            byte player_ = _readObject.getPlace();
//            Bytes pl_ = Net.activePlayers(_instance);
//            if (game_.availableSwitchingCards() && !game_.readyToPlayMulti(pl_)) {
//                Bytes humWin_ = game_.getWinners(new Bytes(player_));
//                if (!humWin_.isEmpty()) {
//                    HandPresident h_ = game_.strategieEchange(player_);
//                    if (!game_.giveWorstCards(pl_, player_, h_)) {
//                        return;
//                    }
//                    humWin_ = game_.getWinners(pl_);
//                    Bytes humLos_ = game_.getLoosers(pl_);
//                    Bytes humLosReceiving_ = new Bytes();
//                    for (byte p: humLos_) {
//                        byte w_ = game_.getMatchingWinner(p);
//                        if (humWin_.containsObj(w_)) {
//                            humLosReceiving_.add(p);
//                        }
//                    }
//                    if (!humLosReceiving_.isEmpty()) {
//                        //refresh hands of losers
//                        ReceivedGivenCards disAfter_ = new ReceivedGivenCards();
//                        for (byte p: humLos_) {
//                            byte w_ = game_.getMatchingWinner(p);
//                            disAfter_.setReceived(game_.getSwitchedCards().get(w_));
//                            disAfter_.setGiven(game_.getSwitchedCards().get(p));
//                            disAfter_.setNewHand(game_.getDistribution().hand(w_));
//                            Net.sendObject(Net.getSocketByPlace(p, _instance), disAfter_);
//                        }
//                        return;
//                    }
//                    playingPresidentCard(_instance,_fct,_common);
//                }
//                return;
//            }
//            if (game_.getNextPlayer() == player_) {
//                ThreadUtil.sleep(_fct,800);
//                if (game_.currentPlayerHasPlayed(player_)) {
//                    return;
//                }
//                PlayingCardPresident cardDto_ = new PlayingCardPresident();
//                cardDto_.setPlayedHand(game_.getPlayedCards());
//                cardDto_.setPlace(player_);
//                cardDto_.setNextPlayer(game_.getNextPlayer());
//                cardDto_.setStatus(game_.getLastStatus());
//                cardDto_.setPlayedCard(CardPresident.WHITE);
//                cardDto_.setLocale(Constants.getDefaultLanguage());
//                Net.initAllReceived(_instance);
//                for (byte p: Net.activePlayers(_instance)) {
//                    Net.sendObject(Net.getSocketByPlace(p, _instance),cardDto_);
//                }
//            }
//            return;
//        }
//        if (Net.getGames(_instance).enCoursDePartieTarot()) {
//            GameTarot game_ = Net.getGames(_instance).partieTarot();
//            if (!game_.keepPlayingCurrentGame()) {
//                return;
//            }
//            if (game_.keepBidding()) {
//                byte place_ = game_.playerHavingToBid();
//                if (place_ == _readObject.getPlace()) {
//                    ThreadUtil.sleep(_fct,1000);
//                    if (game_.playerHasAlreadyBidded(place_)) {
//                        return;
//                    }
//                    BiddingTarot bid_ = new BiddingTarot();
//                    bid_.setPlace(place_);
//                    bid_.setBid(game_.getLastBid());
//                    bid_.setLocale(Constants.getDefaultLanguage());
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
//                    }
//                }
//                return;
//            }
//            if (!game_.unionPlis().isEmpty() && game_.getPliEnCours().getVuParToutJoueur() && game_.keepPlayingCurrentTrick()) {
//                byte place_ = game_.playerHavingToBid();
//                if (place_ == _readObject.getPlace()) {
//                    ThreadUtil.sleep(_fct,800);
//                    if (game_.currentPlayerHasPlayed(place_)) {
//                        return;
//                    }
//                    CardTarot card_ = game_.getCarteJoueee();
//                    PlayingCardTarot cardDto_ = new PlayingCardTarot();
//                    cardDto_.setPlace(place_);
//                    cardDto_.setPlayedCard(card_);
//                    EnumList<Handfuls> annoncesPoignees_ = game_.getAnnoncesPoignees(place_);
//                    EnumList<Miseres> annoncesMiseres_ = game_.getAnnoncesMiseres(place_);
//                    HandTarot poignee_=game_.getPoignee(place_);
//                    if (!annoncesPoignees_.isEmpty()) {
//                        cardDto_.setChoosenHandful(annoncesPoignees_.first());
//                    } else {
//                        cardDto_.setChoosenHandful(Handfuls.NO);
//                    }
//                    cardDto_.setHandful(poignee_);
//                    cardDto_.setMiseres(annoncesMiseres_);
//                    cardDto_.setExcludedTrumps(new HandTarot());
//                    cardDto_.setLocale(Constants.getDefaultLanguage());
//                    Net.initAllReceived(_instance);
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), cardDto_);
//                    }
//                }
//                return;
//            }
//            if (!game_.getRegles().getDiscardAfterCall()) {
//                game_.appelApresEcart();
//                CalledCards calledCards_ = new CalledCards();
//                calledCards_.setPlace(game_.getPreneur());
//                calledCards_.setCalledCards(game_.getCarteAppelee());
//                calledCards_.setLocale(Constants.getDefaultLanguage());
//                Net.initAllReceived(_instance);
//                for (byte p: Net.activePlayers(_instance)) {
//                    Net.sendObject(Net.getSocketByPlace(p, _instance), calledCards_);
//                }
//                return;
//            }
//            HandTarot callableCards_ = game_.callableCards();
//            if (!callableCards_.estVide()) {
//                if (game_.getCarteAppelee().estVide()) {
//                    game_.intelligenceArtificielleAppel();
//                    CalledCards calledCards_ = new CalledCards();
//                    calledCards_.setPlace(game_.getPreneur());
//                    calledCards_.setCalledCards(game_.getCarteAppelee());
//                    calledCards_.setLocale(Constants.getDefaultLanguage());
//                    Net.initAllReceived(_instance);
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), calledCards_);
//                    }
//                    return;
//                }
//            }
//
//            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
//                game_.ecarter(game_.unionPlis().isEmpty());
//                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
//                    DiscardedTrumps discarded_ = new DiscardedTrumps();
//                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
//                    Net.initAllReceived(_instance);
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), discarded_);
//                    }
//                    return;
//                }
//                if (game_.chelemAnnonce()) {
//                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
//                    bid_.setPlace(game_.getPreneur());
//                    bid_.setLocale(Constants.getDefaultLanguage());
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
//                    }
//                    return;
//                }
//                byte dealer_=game_.getDistribution().getDealer();
//                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
//                game_.setEntameur(game_.playerAfter(dealer_));
//                game_.setPliEnCours(true);
//                playingTarotCard(_instance,_fct,_common);
//                return;
//            }
//            game_.gererChienInconnu();
//            if (!game_.getContrat().isFaireTousPlis()) {
//                game_.slam();
//                if (game_.chelemAnnonce()) {
//                    Net.initAllReceived(_instance);
//                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
//                    bid_.setPlace(game_.getPreneur());
//                    bid_.setLocale(Constants.getDefaultLanguage());
//                    for (byte p: Net.activePlayers(_instance)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
//                    }
//                    return;
//                }
//            }
//            game_.setPliEnCours(true);
//            playingTarotCard(_instance,_fct,_common);
//        }
//    }

}
