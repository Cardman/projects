package cards.network.threads;

import cards.belote.DeclareHandBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.ResultsBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerSingleTarot;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.DiscardPhaseTarot;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.president.GamePresident;
import cards.president.ResultsPresident;
import cards.president.enumerations.CardPresident;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.*;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.IdList;
import code.util.core.NumberUtil;

public abstract class ServerActLoopCardsActedByClientReceived implements IntServerActLoopCards {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer((byte) NumberUtil.parseInt(_input.get(0)), _instance);
        if (!Net.allReceived(_instance)) {
            return;
        }
        Net.initAllReceived(_instance, _common);
        loopReceive(_input, _instance, _fct, _common);
    }
    protected static void processAfterBidTarot(GameTarot _game, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        //Call, dog or play
        if (!_game.getContrat().isJouerDonne()) {
            if (_game.pasJeuApresPasse()) {
                endGameTarot(_instance, _common);
            } else {
//                _game.setEntameur(_game.playerAfter(_game.getDistribution().getDealer()));
//                _game.setPliEnCours(true);
                _game.initPlayWithoutBid();
                _game.firstLead();
                playingTarotCard(_instance,_fct, _common);
            }
            return;
        }

        CallingCard appel_= _game.getRegles().getDealing().getAppel();
        if (appel_ == CallingCard.DEFINED) {
            _game.initEquipeDeterminee();
//            } else {
//                _game.initDefense();
        }
        if (Net.isHumanPlayer(_game.getPreneur(), _instance, _common)) {
            HandTarot callableCards_ = _game.callableCards();
//            if (callableCards_.estVide()) {
//                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
//                    _game.ajouterCartesUtilisateur();
//                    Dog dog_ = new Dog();
////                    dog_.setHumanTaker(true);
//                    dog_.setTakerIndex(_game.getPreneur());
//                    dog_.setDog(_game.getDistribution().derniereMain());
//                    dog_.setCallableCards(callableCards_);
//                    for (byte p: Net.activePlayers(_instance, _common)) {
////                        dog_.setTaker(p == _game.getPreneur());
//                        update(p, _game, dog_);
//                        Net.sendObject(Net.getSocketByPlace(p, _common), dog_);
//                    }
//                    return;
//                }
//                _game.gererChienInconnu();
//                if (!_game.getContrat().isFaireTousPlis()) {
//                    Net.sendObjectDisplaySlamButton(Net.getSocketByPlace(_game.getPreneur(), _common));
//                } else {
//                    playingTarotCard(_instance,_fct, _common);
//                    return;
//                }
//            } else if (_game.getRegles().getDiscardAfterCall()) {
//                CallableCards callableCardsDto_ = new CallableCards();
//                callableCardsDto_.setTakerIndex(_game.getPreneur());
//                callableCardsDto_.setCallableCards(callableCards_);
//                Net.sendObject(Net.getSocketByPlace(_game.getPreneur(), _common), callableCardsDto_);
////                for (byte p: Net.activePlayers(_instance, _common)) {
////                    if (p == _game.getPreneur()) {
////                        callableCardsDto_.setCallableCards(callableCards_);
////                    } else {
////                        callableCardsDto_.setCallableCards(new HandTarot());
////                    }
////                    Net.sendObject(Net.getSocketByPlace(p, _common), callableCardsDto_);
////                }
//            } else {
            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                _game.ajouterCartesUtilisateur();
//                    Net.initAllReceived(_instance, _common);
                DiscardPhaseTarot dog_ = new DiscardPhaseTarot();
                dog_.setDiscardCard(_game.getDistribution().derniereMain());
                dog_.setCallableCards(callableCards_);
//                    dog_.setTaker(false);
                dog_.getDiscardPhase().setTakerIndex(_game.getPreneur());
//                    dog_.setHumanTaker(true);
                callAfter(_game, dog_);
//                    dog_.setCallAfter(true);
                for (byte p: Net.activePlayers(_instance, _common)) {
//                        dog_.setTaker(p == _game.getPreneur());
                    update(p, _game, dog_);
                    NetGroupFrame.trySendString(Net.exportDiscardPhaseTarot(dog_), Net.getSocketByPlace(p, _common));
                }
                return;
            }
            _game.gererChienInconnu();
            if (!callableCards_.estVide()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                callableCardsDto_.setCallableCards(callableCards_);
                NetGroupFrame.trySendString(Net.exportCallableCards(callableCardsDto_), Net.getSocketByPlace(_game.getPreneur(), _common));
            } else if (!_game.getContrat().isFaireTousPlis()) {
                NetGroupFrame.trySendString(Net.exportSlamTarot(), Net.getSocketByPlace(_game.getPreneur(), _common));
            } else {
                playingTarotCard(_instance,_fct, _common);
            }
//                for (byte p: Net.activePlayers(_instance, _common)) {
//                    if (p == _game.getPreneur()) {
//                        callableCardsDto_.setCallableCards(callableCards_);
//                    } else {
//                        callableCardsDto_.setCallableCards(new HandTarot());
//                    }
//                    Net.sendObject(Net.getSocketByPlace(p, _common), callableCardsDto_);
//                }
//                return;
//            }
            return;
        }
//        HandTarot callableCards_ = _game.callableCards();
//        if (callableCards_.estVide()) {
//            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
//                Net.initAllReceived(_instance, _common);
//                Dog show_ = new Dog();
//                show_.setDog(_game.getDistribution().derniereMain());
//                show_.setCallableCards(callableCards_);
//                show_.setTaker(false);
//                show_.setTakerIndex(_game.getPreneur());
//                show_.setHumanTaker(false);
//                show_.setTaker(Dog.TAKER_NO);
//                for (byte p: Net.activePlayers(_instance, _common)) {
//                    Net.sendObject(Net.getSocketByPlace(p, _common), show_);
//                }
//                return;
//            }
//            _game.ecart(_instance.getIa().getTarot());
//            _game.gererChienInconnu();
//            AfterAnimationBidTarot.possibleSlam(_game, _instance.getIa());
//            if (!_game.getContrat().isFaireTousPlis()) {
//                _game.slam(_instance.getIa().getTarot());
//                if (_game.chelemAnnonce()) {
//                    Net.initAllReceived(_instance, _common);
//                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
//                    //bid_.setLocale(Constants.getDefaultLanguage());
//                    bid_.setLocale("");
//                    bid_.setPlace(_game.getPreneur());
//                    for (byte p: Net.activePlayers(_instance, _common)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
//                    }
//                    return;
//                }
//            }
//            playingTarotCard(_instance,_fct, _common);
//            return;
//        }
        ContainerSingleTarot.ecart(_game,_instance.getIa());
//        if (!_game.getRegles().getDiscardAfterCall()) {
//            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
//                _game.appelApresEcart(_instance.getIa().getTarot());
//            } else {
//                _game.intelligenceArtificielleAppel(_instance.getIa().getTarot());
//            }
//        } else {
//            _game.intelligenceArtificielleAppel(_instance.getIa().getTarot());
//        }
        playingTarotCard(_instance,_fct, _common);
//        CalledCards calledCards_ = new CalledCards();
//        calledCards_.setPlace(_game.getPreneur());
//        calledCards_.setCalledCards(_game.getCarteAppelee());
//        //calledCards_.setLocale(Constants.getDefaultLanguage());
//        calledCards_.setLocale("");
//        Net.initAllReceived(_instance, _common);
//        for (byte p: Net.activePlayers(_instance, _common)) {
//            Net.sendObject(Net.getSocketByPlace(p, _common), calledCards_);
//        }
//        return;
    }
    protected static void playingBeloteCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingBelote decla_ = new AllowPlayingBelote();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setFirstRoundPlaying(game_.premierTour());
            decla_.setCurrentBid(game_.getBid());
            decla_.setCards(game_.autorise());
            HandBelote belReb_ = game_.cartesBeloteRebelote();
            decla_.setBelReb(belReb_);
            if (game_.premierTour()) {
                decla_.setDeclaration(game_.strategieAnnonces());
            } else {
                decla_.setDeclaration(new DeclareHandBelote());
            }
            decla_.setPossibleBeloteRebelote(!belReb_.estVide());
            decla_.setAllowedBeloteRebelote(game_.autoriseBeloteRebelote());
            NetGroupFrame.trySendString(Net.exportAllowPlayingBelote(decla_), Net.getSocketByPlace(place_, _common));
            return;
        }
        ThreadUtil.sleep(_fct,800);
//        if (game_.aJoue(place_)) {
//            return;
//        }
        CardBelote card_ = game_.playCard(_instance.getIa().getBelote());
        boolean declareBeloteRebelote_ = game_.getAnnoncesBeloteRebelote(place_).contient(card_);
        PlayingCardBelote cardDto_ = new PlayingCardBelote();
        cardDto_.setRefreshing(false);
        cardDto_.setTakerIndex(game_.getPreneur());
        cardDto_.setPlace(place_);
        cardDto_.setPlayedCard(card_);
        cardDto_.setDeclaringBeloteRebelote(declareBeloteRebelote_);
        if (game_.premierTour()) {
            cardDto_.setDeclaring(true);
            cardDto_.setDeclare(game_.getAnnonce(place_));
        } else {
            cardDto_.setDeclaring(false);
            cardDto_.setDeclare(new DeclareHandBelote());
        }
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//        cardDto_.setLocale("");
//        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingBelote(cardDto_), Net.getSocketByPlace(p, _common));
        }
    }
    protected static void playingPresidentCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        byte place_ = game_.nextPlayer();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingPresident allow_ = new AllowPlayingPresident();
            allow_.setEnabledPass(!game_.getProgressingTrick().estVide() && game_.canPass());
            allow_.setStatus(game_.getStatus());
            allow_.setCards(game_.cartesJouables());
            allow_.setReversed(game_.isReversed());
            NetGroupFrame.trySendString(Net.exportAllowPlayingPresident(allow_), Net.getSocketByPlace(place_, _common));
            return;
        }
        ThreadUtil.sleep(_fct,800);
//        if (game_.aJoue(place_)) {
//            return;
//        }
        PlayingCardPresident cardDto_ = new PlayingCardPresident();
        cardDto_.setRefreshing(false);
        cardDto_.setPlayedHand(game_.addCardsToCurrentTrick(_instance.getIa().getPresident()));
        cardDto_.setPlace(place_);
        cardDto_.setNextPlayer(game_.nextPlayer());
        cardDto_.setStatus(game_.getLastStatus());
        cardDto_.setPlayedCard(CardPresident.WHITE);
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//        cardDto_.setLocale("");
//        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingPresident(cardDto_), Net.getSocketByPlace(p, _common));
        }
    }

    protected static void processCallingTarot(HandTarot _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        //called cards by a human player
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        game_.initConfianceAppeleUtilisateur(_instance.getIa().getTarot().strategieAppelUser(_readObject));
        if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
            //before taking cards of the dog
            DiscardPhaseTarot dog_ = new DiscardPhaseTarot();
            dog_.setDiscardCard(game_.getDistribution().derniereMain());
            dog_.setCallableCards(new HandTarot());
            callAfter(game_,dog_);
            for (byte p: Net.activePlayers(_instance, _common)) {
                update(p, game_, dog_);
                NetGroupFrame.trySendString(Net.exportDiscardPhaseTarot(dog_), Net.getSocketByPlace(p, _common));
            }
        } else {
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                NetGroupFrame.trySendString(Net.exportSlamTarot(), Net.getSocketByPlace(game_.getPreneur(), _common));
            } else {
                game_.firstLead();
                playingTarotCard(_instance,_fct, _common);
            }
        }
    }

    protected static void playingTarotCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingTarot decla_ = new AllowPlayingTarot();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setCurrentBid(game_.getContrat());
            boolean firstRound_ = game_.premierTourNoMisere();
            decla_.setFirstRoundPlaying(game_.premierTour());
            decla_.setCards(game_.autorise());
            if (firstRound_) {
                decla_.setDiscardedTrumps(ContainerSingleTarot.discarded(game_));
                decla_.setCalledCards(game_.getCalledCards());
//                IdList<Handfuls> handfuls_ = new IdList<Handfuls>(game_.getRegles().getCurrentAllowedHandfuls());
//                decla_.setAllowedHandfuls(new IdList<Handfuls>(handfuls_));
//                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>(game_.getRegles().getAllowedHandfuls()));
//                decla_.setAllowedMiseres(new IdList<Miseres>(game_.getRegles().getMiseres()));
            } else {
                decla_.setDiscardedTrumps(new HandTarot());
                decla_.setCalledCards(new HandTarot());
//                decla_.setAllowedHandfuls(new IdList<Handfuls>());
//                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>());
//                decla_.setAllowedMiseres(new IdList<Miseres>());
            }
            NetGroupFrame.trySendString(Net.exportAllowPlayingTarot(decla_), Net.getSocketByPlace(place_, _common));
            return;
        }
        ThreadUtil.sleep(_fct,800);
//        if (game_.aJoue(place_)) {
//            return;
//        }
        CardTarot card_ = game_.currentPlayerHasPlayed(_instance.getIa().getTarot());
        PlayingCardTarot cardDto_ = new PlayingCardTarot();
        cardDto_.setRefreshing(false);
        cardDto_.setTakerIndex(game_.getPreneur());
        cardDto_.setPlace(place_);
        cardDto_.setPlayedCard(card_);
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//        cardDto_.setLocale("");
        IdList<Handfuls> annoncesPoignees_ = game_.getAnnoncesPoignees(place_);
        IdList<Miseres> annoncesMiseres_ = game_.getAnnoncesMiseres(place_);
        HandTarot poignee_=game_.getPoignee(place_);
        if (!annoncesPoignees_.isEmpty()) {
            cardDto_.setChoosenHandful(annoncesPoignees_.first());
        } else {
            cardDto_.setChoosenHandful(Handfuls.NO);
        }
        cardDto_.setHandful(poignee_);
        cardDto_.setMiseres(annoncesMiseres_);
        cardDto_.setExcludedTrumps(new HandTarot());
//        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingTarot(cardDto_), Net.getSocketByPlace(p, _common));
        }
    }
    protected static void endGameBelote(Net _instance, NetCommon _common) {
//        int nbPlayers_ = Net.getGames(_instance).partieBelote().getNombreDeJoueurs();
//        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
//            if (_common.getNicknames().contains(i)) {
//                players_.add(_common.getNicknames().getVal(i));
//            } else {
//                players_.add(EMPTY_STRING);
//            }
//        }
        ResultsBelote res_ = new ResultsBelote();
//        CustList<Longs> scores_ = Net.getScores(_instance);
//        CustList<Longs> list_ = new CustList<Longs>();
//        for (Longs v: scores_) {
//            list_.add(new Longs(v));
//        }
        res_.setGame(Net.getGames(_instance).partieBelote());
        res_.getRes().setScores(Net.getScores(_instance));
        for (byte p: Net.activePlayers(_instance,_common)) {
//            String loc_ = Net.getLanguageByPlace(p, _instance,_common);
//            Games.setMessages(res_.getRes(),_common.getProgramInfos().getTranslations().getMapping().getVal(loc_));
            NetGroupFrame.trySendString(Net.exportGameBelote(res_), Net.getSocketByPlace(p, _common));
        }
    }
    protected static void endGamePresident(Net _instance, NetCommon _common) {
//        int nbPlayers_ = Net.getGames(_instance).partiePresident().getNombreDeJoueurs();
//        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
//            if (_common.getNicknames().contains(i)) {
//                players_.add(_common.getNicknames().getVal(i));
//            } else {
//                players_.add(EMPTY_STRING);
//            }
//        }
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(Net.getGames(_instance).partiePresident());
//        CustList<Longs> scores_ = Net.getScores(_instance);
//        CustList<Longs> list_ = new CustList<Longs>();
//        for (Longs v: scores_) {
//            list_.add(new Longs(v));
//        }
        res_.getRes().setScores(Net.getScores(_instance));
        for (byte p: Net.activePlayers(_instance, _common)) {
//            String loc_ = Net.getLanguageByPlace(p, _instance, _common);
//            Games.setMessages(res_.getRes(),_common.getProgramInfos().getTranslations().getMapping().getVal(loc_));
            NetGroupFrame.trySendString(Net.exportGamePresident(res_), Net.getSocketByPlace(p, _common));
        }
    }
    protected static void endGameTarot(Net _instance, NetCommon _common) {
//        int nbPlayers_ = Net.getGames(_instance).partieTarot().getNombreDeJoueurs();
//        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
//            if (_common.getNicknames().contains(i)) {
//                players_.add(_common.getNicknames().getVal(i));
//            } else {
//                players_.add(EMPTY_STRING);
//            }
//        }
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(Net.getGames(_instance).partieTarot());
//        CustList<Longs> scores_ = Net.getScores(_instance);
//        CustList<Longs> list_ = new CustList<Longs>();
//        for (Longs v: scores_) {
//            list_.add(new Longs(v));
//        }
        res_.getRes().setScores(Net.getScores(_instance));
        for (byte p: Net.activePlayers(_instance, _common)) {
//            String loc_ = Net.getLanguageByPlace(p, _instance, _common);
//            Games.setMessages(res_.getRes(),_common.getProgramInfos().getTranslations().getMapping().getVal(loc_));
            NetGroupFrame.trySendString(Net.exportGameTarot(res_), Net.getSocketByPlace(p, _common));
        }
    }
    protected static void callAfter(GameTarot _game, DiscardPhaseTarot _dog) {
        _dog.setCallAfter(!_game.getRegles().getDiscardAfterCall());
    }

    protected static void update(byte _player, GameTarot _game, DiscardPhaseTarot _dog) {
        if (_player == _game.getPreneur()) {
            _dog.getDiscardPhase().setTaker(DiscardPhaseTarot.TAKER_HUM_WRITE);
        } else {
            _dog.getDiscardPhase().setTaker(DiscardPhaseTarot.TAKER_HUM_READ);
        }
    }

    protected static void update(byte _player, GameBelote _game, DiscardPhaseBelote _dog) {
        if (_player == _game.getPreneur()) {
            _dog.getDiscardPhase().setTaker(DiscardPhaseBelote.TAKER_HUM_WRITE);
        } else {
            _dog.getDiscardPhase().setTaker(DiscardPhaseBelote.TAKER_HUM_READ);
        }
    }
    protected abstract void loopReceive(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);
}
