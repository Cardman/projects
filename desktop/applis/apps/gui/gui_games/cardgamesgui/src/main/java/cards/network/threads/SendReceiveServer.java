package cards.network.threads;
import java.net.Socket;

import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.DeclareHandBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.belote.displaying.players.RefreshingDoneBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.*;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.NewPlayer;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.Pause;
import cards.network.common.select.*;
import cards.network.president.actions.DiscardedCards;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.*;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.displaying.players.RefreshingDonePresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.displaying.players.*;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.network.tarot.unlock.DisplaySlamButton;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TricksHandsPresident;
import cards.president.enumerations.CardPresident;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.*;
import code.gui.GroupFrame;
import code.threads.AbstractLock;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.network.AddingPlayer;
import code.network.BasicServer;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.consts.Constants;
import code.util.core.IndexConstants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServer extends BasicServer {

    private static final String EMPTY_STRING = "";

    private final AbstractLock lock;

    private final Net instance;
    /**This class thread is independant from EDT*/
    public SendReceiveServer(Socket _socket, NetGroupFrame _net, Net _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input, Object _object) {
        lock.lock(this);
        loop(_input, _object, instance,getNet().getThreadFactory());
        lock.unlock(this);
    }

    private static void loop(String _input, Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        if (_readObject instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)_readObject;
            if (!newPlayer_.isAcceptable()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setBusy(true);
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _instance);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
        }
        if (_readObject instanceof NewPlayer) {
            NewPlayer newPlayer_ = (NewPlayer)_readObject;
            if (Net.getNbPlayers(_instance) == Net.getNicknames(_instance).size()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _instance);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            if (Net.isProgressingGame(_instance)) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _instance);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            Net.getPlayersLocales(_instance).put(newPlayer_.getIndex(), newPlayer_.getLanguage());
            Net.getNicknames(_instance).put(newPlayer_.getIndex(),newPlayer_.getPseudo());
            PlayersNamePresent pl_ = new PlayersNamePresent();
            if (Net.getGames(_instance).getRulesBelote() != null) {
                pl_.setRulesBelote(Net.getGames(_instance).getRulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            } else if (Net.getGames(_instance).getRulesPresident() != null) {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(Net.getGames(_instance).getRulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            } else if (Net.getGames(_instance).getRulesTarot() != null) {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(Net.getGames(_instance).getRulesTarot());
            } else {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            }
            pl_.setNbPlayers(Net.getNbPlayers(_instance));
            pl_.setPseudos(new IntMap<String>(Net.getNicknames(_instance)));
            pl_.setPlacesPlayers(Net.getPlacesPlayers(_instance));
            pl_.setReadyPlayers(new IntMap<Boolean>(Net.getReadyPlayers(_instance)));
            for (int p:Net.getSockets(_instance).getKeys()) {
                pl_.setFirst(p == newPlayer_.getIndex());
                Net.sendObject(Net.getSockets(_instance).getVal(p),pl_);
            }
            return;
        }
        if (_readObject instanceof ChoosenPlace) {
            int noClient_ = ((ChoosenPlace)_readObject).getIndex();
            Net.getPlacesPlayers(_instance).put(noClient_, (byte)((ChoosenPlace)_readObject).getPlace());
            ((ChoosenPlace) _readObject).setPlacesPlayers(Net.getPlacesPlayers(_instance));
            for(Socket so_:Net.getSockets(_instance).values()) {
                Net.sendObject(so_,_readObject);
            }
            return;
        }
        if (_readObject instanceof RulesBelote) {
            for(Socket so_:Net.getSockets(_instance).values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof RulesPresident) {
            for(Socket so_:Net.getSockets(_instance).values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof RulesTarot) {
            for(Socket so_:Net.getSockets(_instance).values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof Ready) {
            int noClient_ = ((Ready)_readObject).getIndex();
            Net.getReadyPlayers(_instance).put(noClient_, (( Ready)_readObject).isReady());
            for(Socket so_:Net.getSockets(_instance).values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof Quit) {
            quitProcess(_readObject, _instance,_fct);
            return;
        }
        if (_readObject == PlayGame.INSTANCE) {
            Net.initAllPresent(_instance);
            Net.initAllReceived(_instance);
            if (Net.getGames(_instance).enCoursDePartieBelote()) {
                Net.setProgressingGame(true, _instance);
                DealBelote deal_ = Net.getGames(_instance).partieBelote().getDistribution();
                DealtHandBelote hand_ = new DealtHandBelote();
                hand_.setDeck(deal_.derniereMain());
                hand_.setDealer(Net.getGames(_instance).partieBelote().playerAfter(deal_.getDealer()));
                hand_.setAllowedBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
                hand_.setRep(Net.getGames(_instance).partieBelote().getRegles().getRepartition());
                hand_.setPoints(Net.getGames(_instance).partieBelote().getContrat().getPoints());
                for (byte i:Net.activePlayers(_instance)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _instance), hand_);
                }
            } else if (Net.getGames(_instance).enCoursDePartiePresident()) {
                Net.setProgressingGame(true, _instance);
                int nbSuits_ = Suit.couleursOrdinaires().size();
                RulesPresident rules_ = Net.getGames(_instance).partiePresident().getRegles();
                int nbStacks_ = rules_.getNbStacks();
                DealPresident deal_ = Net.getGames(_instance).partiePresident().getDistribution();
                DealtHandPresident hand_ = new DealtHandPresident();
                hand_.setDealer(Net.getGames(_instance).partiePresident().getDistribution().getDonneur());
                hand_.setMaxCards(Math.min(nbSuits_ * nbStacks_, rules_.getNbMaxCardsPerPlayer()));
                hand_.setStatus(Net.getGames(_instance).partiePresident().getLastStatus());
                for (byte i:Net.activePlayers(_instance)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _instance), hand_);
                }
            } else if (Net.getGames(_instance).enCoursDePartieTarot()) {
                Net.setProgressingGame(true, _instance);
                DealTarot deal_ = Net.getGames(_instance).partieTarot().getDistribution();
                DealtHandTarot hand_ = new DealtHandTarot();
                hand_.setDog(deal_.derniereMain());
                hand_.setDealer(Net.getGames(_instance).partieTarot().playerAfter(deal_.getDealer()));
                hand_.setAllowedBids(Net.getGames(_instance).partieTarot().allowedBids());
                hand_.setRep(Net.getGames(_instance).partieTarot().getRegles().getRepartition());
                for (byte i:Net.activePlayers(_instance)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _instance), hand_);
                }
            }
            return;
        }
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            processGameBelote(_input, _readObject, _instance,_fct);
            return;
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            processGamePresident(_readObject, _instance,_fct);
            return;
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            processGameTarot(_input, _readObject, _instance,_fct);
            return;
        }
    }

    private static void processGameTarot(String _input, Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        PlayerActionGameType actionType_ = getActionType(_readObject);
        if (actionType_ == PlayerActionGameType.DEALT) {
            processDealtTarot(_readObject, _instance,_fct);
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_BIDDING) {
            processBetweenBidsTarot(_readObject, _instance,_fct);
            return;
        }
        if (_readObject instanceof BiddingTarot) {
            BiddingTarot bid_ = (BiddingTarot)_readObject;
            BidTarot b_ = bid_.getBid();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!b_.estDemandable(game_.getContrat())) {
                ErrorBidding error_ = new ErrorBidding();
                error_.setBid(game_.getContrat());
                Net.sendObject(Net.getSocketByPlace(bid_.getPlace(), _instance), error_);
                return;
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendText(Net.getSocketByPlace(p, _instance), _input);
            }
            return;
        }
        if (_readObject instanceof CalledCards) {
            processCallingTarot(_readObject, _instance,_fct);
            return;
        }
        if (_readObject instanceof DiscardedCard) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            DiscardedCard discarded_ = (DiscardedCard) _readObject;
            if (!discarded_.isInHand()) {
                game_.retirerUneCarteDuChien(discarded_.getCard());
                game_.addCard(discarded_.getPlace(),discarded_.getCard());
                Net.sendText(Net.getSocketByPlace(discarded_.getPlace(), _instance), _input);
                return;
            }
            ReasonDiscard reason_ = game_.autoriseEcartDe(discarded_.getCard());
            if (reason_ != ReasonDiscard.NOTHING) {
                ErrorDiscarding error_ = new ErrorDiscarding();
                error_.setErrorMessage(Games.autoriseMessEcartDe(game_,reason_,discarded_.getCard(), discarded_.getLocale()).toString());
                error_.setCard(discarded_.getCard());
                Net.sendObject(Net.getSocketByPlace(discarded_.getPlace(), _instance), error_);
                return;
            }
            game_.ajouterUneCarteDansPliEnCours(discarded_.getPlace(),discarded_.getCard());
            Net.sendText(Net.getSocketByPlace(discarded_.getPlace(), _instance), _input);
            return;
        }
        if (actionType_ == PlayerActionGameType.CALLED_CARD_KNOWN) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog show_ = new Dog();
                    show_.setDog(game_.getDistribution().derniereMain());
                    show_.setTaker(false);
                    show_.setHumanTaker(false);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), show_);
                    }
                    return;
                }
                game_.gererChienInconnu();
                if (!game_.getContrat().isFaireTousPlis()) {
                    game_.slam();
                    if (game_.chelemAnnonce()) {
                        Net.initAllReceived(_instance);
                        PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                        bid_.setPlace(game_.getPreneur());
                        bid_.setLocale(Constants.getDefaultLanguage());
                        for (byte p: Net.activePlayers(_instance)) {
                            Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                        }
                        return;
                    }
                }
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
            }
            return;
        }
        if (_readObject instanceof SeenDiscardedTrumps) {
            processDiscardingTrumps(_readObject, _instance,_fct);
            return;
        }
        if (actionType_ == PlayerActionGameType.SHOW_DOG) {
            processShowDogTarot(_readObject, _instance,_fct);
            return;
        }
        if (_readObject == TakeCard.INSTANCE) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            game_.ajouterCartesUtilisateur();
            if (!game_.getRegles().getDiscardAfterCall()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setDiscarding(true);
                callableCardsDto_.setCallableCards(game_.callableCards());
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur(), _instance), callableCardsDto_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.VALIDATE_DOG) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                game_.addCurTrick();
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), discarded_);
                    }
                    return;
                }
            }
            byte donneur_=game_.getDistribution().getDealer();
            if(!game_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                game_.setEntameur(game_.playerAfter(donneur_));
            }
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct);
            return;
        }
        if (actionType_ == PlayerActionGameType.SLAM) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if(!game_.chelemAnnonce()) {
                game_.ajouterChelemUtilisateur();
            }
            if (!game_.getRegles().getDiscardAfterCall()) {
                game_.addCurTrick();
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setDeclaringSlam(true);
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), discarded_);
                    }
                    return;
                }
            }
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendText(Net.getSocketByPlace(p, _instance), _input);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_DISPLAY_SLAM) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            processBetweenPlayedCardsTarot(_readObject, _instance,_fct);
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
            }
            return;
        }
        if (_readObject instanceof PlayingCardTarot) {
            processPlayingTarot(_readObject, _instance);
            return;
        }
        if (_readObject instanceof RefreshingDone) {
            PlayingCardTarot p_ = new PlayingCardTarot();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(((RefreshingDone)_readObject).getPlace());
            p_.setPlayedCard(((RefreshingDone)_readObject).getCard());
            p_.setChoosenHandful(((RefreshingDone)_readObject).getChoosenHandful());
            p_.setHandful(((RefreshingDone)_readObject).getHandful());
            p_.setMiseres(((RefreshingDone)_readObject).getMiseres());
            p_.setCalledCard(((RefreshingDone)_readObject).isCalledCard());
            p_.setExcludedTrumps(new HandTarot());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendObject(Net.getSocketByPlace(p, _instance), p_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        processShowTarot(_readObject, _instance);
    }

    private static PlayerActionGameType getActionType(Object _readObject) {
        PlayerActionGameType actionType_ = PlayerActionGameType.SIMPLE;
        if (_readObject instanceof PlayerActionGame) {
            actionType_ = ((PlayerActionGame)_readObject).getActionType();
        }
        return actionType_;
    }

    private static void processBetweenBidsTarot(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance);

            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!game_.keepBidding()) {
                processAfterBidTarot(game_, _instance,_fct);
                return;
            }
            byte place_ = game_.playerHavingToBid();
            if (Net.isHumanPlayer(place_, _instance)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(game_.allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_, _instance), allowedBids_);
                return;
            }
            //Les "robots" precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_fct,1000);
            if (Net.getGames(_instance).partieTarot().playerHasAlreadyBidded(place_)) {
                return;
            }
            BiddingTarot bid_ = new BiddingTarot();
            bid_.setPlace(place_);
            bid_.setBid(Net.getGames(_instance).partieTarot().getLastBid());
            bid_.setLocale(Constants.getDefaultLanguage());
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
            }
        }
        return;
    }

    private static void processBetweenPlayedCardsTarot(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance);
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!game_.keepPlayingCurrentTrick()) {
                game_.ajouterPetitAuBoutPliEnCours();

                if (!game_.keepPlayingCurrentGame()) {
                    ThreadUtil.sleep(_fct,1000);
                    endGameTarot(_instance);
                    return;
                }
                ThreadUtil.sleep(_fct,3000);
                Net.initAllReceived(_instance);
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), Pause.INSTANCE);
                }
                return;
            }

            playingTarotCard(_instance,_fct);
        }
        return;
    }

    private static void processDealtTarot(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance);
            if (!Net.getGames(_instance).partieTarot().avecContrat()) {
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
            byte place_ = Net.getGames(_instance).partieTarot().playerHavingToBid();
            if (Net.isHumanPlayer(place_, _instance)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(Net.getGames(_instance).partieTarot().allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_, _instance), allowedBids_);
                return;
            }
            //Les "robots" precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_fct,1000);
            if (Net.getGames(_instance).partieTarot().playerHasAlreadyBidded(place_)) {
                return;
            }
            BiddingTarot bid_ = new BiddingTarot();
            bid_.setPlace(place_);
            bid_.setBid(Net.getGames(_instance).partieTarot().getLastBid());
            bid_.setLocale(Constants.getDefaultLanguage());
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
            }
        }
    }

    private static void processShowDogTarot(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance);
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (game_.getRegles().getDiscardAfterCall()) {
                game_.ecarter(true);
            }
            ThreadUtil.sleep(_fct,5000);
            if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                DiscardedTrumps discarded_ = new DiscardedTrumps();
                discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                Net.initAllReceived(_instance);
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), discarded_);
                }
                return;
            }
            if (game_.chelemAnnonce()) {
                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                }
                return;
            }
            byte dealer_=game_.getDistribution().getDealer();
            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
            game_.setEntameur(game_.playerAfter(dealer_));
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct);
            return;
        }
        return;
    }

    private static void processPlayingTarot(Object _readObject, Net _instance) {
        PlayingCardTarot info_ = (PlayingCardTarot) _readObject;
        CardTarot card_ = info_.getPlayedCard();
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        if (info_.getChoosenHandful() != Handfuls.NO) {
            String messErr_ = Games.isValidHandfulMessage(game_, info_.getChoosenHandful(),
                    info_.getHandful(), info_.getExcludedTrumps(), info_.getLocale());
            if (!game_.isValidHandful(info_.getChoosenHandful(),
                    info_.getHandful(), info_.getExcludedTrumps())) {
                ErrorHandful error_ = new ErrorHandful();
                error_.setHandful(info_.getChoosenHandful());
                error_.setError(messErr_);
                Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), error_);
                return;
            }
        }
        if (!game_.autorise(card_)) {
            ErrorPlaying error_ = new ErrorPlaying();
            error_.setCard(card_);
            error_.setReason(Games.autoriseTarot(game_, info_.getLocale()));
            Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), error_);
            return;
        }
        game_.changerConfiance();
        game_.ajouterUneCarteDansPliEnCours(info_.getPlace(), card_);
        if (info_.getChoosenHandful() != Handfuls.NO) {
            EnumList<Handfuls> handfuls_ = new EnumList<Handfuls>();
            handfuls_.add(info_.getChoosenHandful());
            game_.setAnnoncesPoignees(info_.getPlace(), handfuls_);
            game_.ajouterPoignee(info_.getHandful(), info_.getPlace());
        }
        EnumList<Miseres> declaredMiseres_ = new EnumList<Miseres>();
        for (Miseres m: info_.getMiseres()) {
            if (!game_.getAnnoncesMiseresPossibles(info_.getPlace()).containsObj(m)) {
                continue;
            }
            declaredMiseres_.add(m);
        }
        game_.setAnnoncesMiseres(info_.getPlace(), declaredMiseres_);
        RefreshHand ref_ = new RefreshHand();
        ref_.setCard(card_);
        ref_.setPlace(info_.getPlace());
        if (!game_.getAnnoncesPoignees(info_.getPlace()).isEmpty()) {
            ref_.setChoosenHandful(game_.getAnnoncesPoignees(info_.getPlace()).first());
        } else {
            ref_.setChoosenHandful(Handfuls.NO);
        }
        ref_.setHandful(game_.getPoignee(info_.getPlace()));
        ref_.setMiseres(info_.getMiseres());
        ref_.setLocale(Constants.getDefaultLanguage());
        ref_.setCalledCard(game_.getCarteAppelee().contient(card_));
        Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), ref_);
    }

    private static void processShowTarot(Object _readObject, Net _instance) {
        PlayerActionGameType actionType_ = getActionType(_readObject);
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance)) {
                return;
            }
            byte place_ = ((PlayerActionGame)_readObject).getPlace();
            TricksHandsTarot tricksHands_ = new TricksHandsTarot();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            tricksHands_.setDistributionCopy(game_.getDistribution());
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_, _instance), tricksHands_);
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TEAMS) {
            if (!Net.isSameTeam(_instance)) {
                return;
            }
            byte place_ = ((PlayerActionGame)_readObject).getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            teams_.setTeams(game_.getTeamsRelation().teams());
            Net.sendObject(Net.getSocketByPlace(place_, _instance), teams_);
        }
    }

    private static void processDiscardingTrumps(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Net.setReceivedForPlayer(((SeenDiscardedTrumps)_readObject).getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (Net.isHumanPlayer(game_.getPreneur(), _instance)) {
                if (((SeenDiscardedTrumps)_readObject).isDeclaringSlam()) {
                    Net.initAllReceived(_instance);
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(game_.getPreneur());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                    return;
                }
                byte donneur_=game_.getDistribution().getDealer();
                if(!game_.chelemAnnonce()) {
                    /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                    game_.setEntameur(game_.playerAfter(donneur_));
                }
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
            if (game_.chelemAnnonce()) {
                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                }
                return;
            }
            byte donneur_=game_.getDistribution().getDealer();
            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
            game_.setEntameur(game_.playerAfter(donneur_));
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct);
            return;
        }
        return;
    }

    private static void processCallingTarot(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        //called cards by a human player
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        CalledCards calledCards_ = (CalledCards) _readObject;
        game_.initConfianceAppeleUtilisateur(calledCards_.getCalledCards());
        if (!game_.getRegles().getDiscardAfterCall()) {
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur(), _instance), DisplaySlamButton.INSTANCE);
            } else {
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
        }else if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
            //before taking cards of the dog
            Dog dog_ = new Dog();
            dog_.setHumanTaker(true);
            dog_.setDog(game_.getDistribution().derniereMain());
            for (byte p: Net.activePlayers(_instance)) {
                dog_.setTaker(p == game_.getPreneur());
                Net.sendObject(Net.getSocketByPlace(p, _instance), dog_);
            }
        } else {
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur(), _instance), DisplaySlamButton.INSTANCE);
            } else {
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
        }
        return;
    }

    private static void processAfterBidTarot(GameTarot _game, Net _instance, AbstractThreadFactory _fct) {
        //Call, dog or play
        if (!_game.getContrat().isJouerDonne()) {
            if (_game.pasJeuApresPasse()) {
                endGameTarot(_instance);
            } else {
                _game.setEntameur(_game.playerAfter(_game.getDistribution().getDealer()));
                _game.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
            }
            return;
        }

        CallingCard appel_=_game.getRegles().getRepartition().getAppel();
        if(appel_==CallingCard.DEFINED||appel_==CallingCard.WITHOUT) {
            if(appel_==CallingCard.DEFINED) {
                _game.initEquipeDeterminee();
            } else {
                _game.initDefense();
            }
        }
        if (Net.isHumanPlayer(_game.getPreneur(), _instance)) {
            HandTarot callableCards_ = _game.callableCards();
            if (callableCards_.estVide()) {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog dog_ = new Dog();
                    dog_.setHumanTaker(true);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setDog(_game.getDistribution().derniereMain());
                    for (byte p: Net.activePlayers(_instance)) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p, _instance), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                if (!_game.getContrat().isFaireTousPlis()) {
                    Net.sendObject(Net.getSocketByPlace(_game.getPreneur(), _instance), DisplaySlamButton.INSTANCE);
                } else {
                    playingTarotCard(_instance,_fct);
                    return;
                }
            } else if (_game.getRegles().getDiscardAfterCall()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers(_instance)) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p, _instance), callableCardsDto_);
                }
            } else {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Net.initAllReceived(_instance);
                    Dog dog_ = new Dog();
                    dog_.setDog(_game.getDistribution().derniereMain());
                    dog_.setTaker(false);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setHumanTaker(true);
                    dog_.setCallAfter(true);
                    for (byte p: Net.activePlayers(_instance)) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p, _instance), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers(_instance)) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p, _instance), callableCardsDto_);
                }
                return;
            }
            return;
        }
        HandTarot callableCards_ = _game.callableCards();
        if (callableCards_.estVide()) {
            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                Net.initAllReceived(_instance);
                Dog show_ = new Dog();
                show_.setDog(_game.getDistribution().derniereMain());
                show_.setTaker(false);
                show_.setTakerIndex(_game.getPreneur());
                show_.setHumanTaker(false);
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), show_);
                }
                return;
            }
            _game.gererChienInconnu();
            if (!_game.getContrat().isFaireTousPlis()) {
                _game.slam();
                if (_game.chelemAnnonce()) {
                    Net.initAllReceived(_instance);
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(_game.getPreneur());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                    return;
                }
            }
            playingTarotCard(_instance,_fct);
            return;
        }
        if (!_game.getRegles().getDiscardAfterCall()) {
            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                _game.appelApresEcart();
            } else {
                _game.intelligenceArtificielleAppel();
            }
        } else {
            _game.intelligenceArtificielleAppel();
        }
        CalledCards calledCards_ = new CalledCards();
        calledCards_.setPlace(_game.getPreneur());
        calledCards_.setCalledCards(_game.getCarteAppelee());
        calledCards_.setLocale(Constants.getDefaultLanguage());
        Net.initAllReceived(_instance);
        for (byte p: Net.activePlayers(_instance)) {
            Net.sendObject(Net.getSocketByPlace(p, _instance), calledCards_);
        }
        return;
    }

    private static void processGameBelote(String _input, Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        PlayerActionGameType actionType_ = getActionType(_readObject);
        if (actionType_ == PlayerActionGameType.DEALT) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                byte place_ = Net.getGames(_instance).partieBelote().playerHavingToBid();
                if (Net.isHumanPlayer(place_, _instance)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
                    allowedBids_.setPoints(Net.getGames(_instance).partieBelote().getContrat().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_, _instance), allowedBids_);
                    return;
                }
                //Les "robots" precedant l'utilisateur annoncent leur contrat
                ThreadUtil.sleep(_fct,1000);
                if (Net.getGames(_instance).partieBelote().playerHasAlreadyBidded(place_)) {
                    return;
                }
                BiddingBelote bid_ = new BiddingBelote();
                bid_.setPlace(place_);
                bid_.setBidBelote(Net.getGames(_instance).partieBelote().getLastBid());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                }
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_BIDDING) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                if (!game_.keepBidding()) {
                    if (!game_.getContrat().jouerDonne()) {
                        endGameBelote(_instance);
                    } else {
                        if (game_.completedDeal()) {
                            return;
                        }
                        for (byte p: Net.activePlayers(_instance)) {
                            RefreshHandBelote hand_ = new RefreshHandBelote();
                            hand_.setRefreshedHand(game_.getDistribution().hand(p));
                            hand_.setLocale(Constants.getDefaultLanguage());
                            Net.sendObject(Net.getSocketByPlace(p, _instance), hand_);
                        }
                    }
                    return;
                }
                byte place_ = game_.playerHavingToBid();
                if (Net.isHumanPlayer(place_, _instance)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(game_.getGameBeloteBid().allowedBids());
                    allowedBids_.setPoints(game_.getContrat().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_, _instance), allowedBids_);
                    return;
                }
                //Les "robots" precedant l'utilisateur annoncent leur contrat
                ThreadUtil.sleep(_fct,1000);
                if (Net.getGames(_instance).partieBelote().playerHasAlreadyBidded(place_)) {
                    return;
                }
                BiddingBelote bid_ = new BiddingBelote();
                bid_.setPlace(place_);
                bid_.setBidBelote(Net.getGames(_instance).partieBelote().getLastBid());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                }
            }
            return;
        }
        if (_readObject instanceof BiddingBelote) {
            BiddingBelote bid_ = (BiddingBelote)_readObject;
            BidBeloteSuit b_ = bid_.getBidBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            if (!game_.getRegles().dealAll()) {
                if (!b_.estDemandable(game_.getContrat())) {
                    ErrorBiddingBelote error_ = new ErrorBiddingBelote();
                    error_.setBid(game_.getContrat());
                    Net.sendObject(Net.getSocketByPlace(bid_.getPlace(), _instance), error_);
                    return;
                }
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            if (!game_.getRegles().dealAll()) {
                if (game_.tailleContrats() == game_.getNombreDeJoueurs()) {
                    game_.finEncherePremierTour();
                }
            }
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendText(Net.getSocketByPlace(p, _instance), _input);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.COMPLETED_HAND) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                byte donneur_=game_.getDistribution().getDealer();
                game_.setEntameur(game_.playerAfter(donneur_));
                if (game_.getRegles().dealAll()) {
                    int pts_ = game_.getContrat().getPoints();
                    if (pts_ >= HandBelote.pointsTotauxDixDeDer(game_.getContrat())) {
                        game_.setEntameur(game_.getPreneur());
                    }
                }
                game_.setPliEnCours();
                playingBeloteCard(_instance,_fct);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                if (!game_.keepPlayingCurrentTrick()) {
                    game_.ajouterDixDeDerPliEnCours();

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(_fct,1000);
                        endGameBelote(_instance);
                        return;
                    }
                    ThreadUtil.sleep(_fct,3000);
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), Pause.INSTANCE);
                    }
                    return;
                }

                playingBeloteCard(_instance,_fct);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                game_.setPliEnCours();
                playingBeloteCard(_instance,_fct);
            }
            return;
        }
        if (_readObject instanceof PlayingCardBelote) {
            PlayingCardBelote info_ = (PlayingCardBelote) _readObject;
            CardBelote card_ = info_.getPlayedCard();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            boolean autorise_ = game_.autorise(card_);
            if(info_.isDeclaringBeloteRebelote()) {
                boolean annonceBeloteRebelote_ = game_.cartesBeloteRebelote().contient(card_);
                if (!annonceBeloteRebelote_) {
                    autorise_ = false;
                }
                if(!annonceBeloteRebelote_) {
                    //Si l'utilisateur joue une carte de la belote rebelote en l'annoncant mais en respectant pas les regles
                    //alors c'est le message d'erreur sur la jouerie des cartes qui est prioritaire au
                    //message d'erreur sur la belote rebelote
                    HandBelote cartesBeloteRebelote_=game_.cartesBeloteRebelote();
                    /*On ordonne la poignee d'atouts*/
                    ErrorPlayingBelote error_ = new ErrorPlayingBelote();
                    error_.setCard(card_);
                    error_.setCards(cartesBeloteRebelote_);
                    Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), error_);
                    return;
                }
            }
            if (!autorise_) {
                ErrorPlayingBelote error_ = new ErrorPlayingBelote();
                error_.setCard(card_);
                error_.setCards(new HandBelote());
                error_.setReason(Games.autoriseBelote(game_,info_.getLocale()));
                Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), error_);
                return;
            }
            if(info_.isDeclaring()) {
                game_.annoncer(info_.getPlace());
            }
            game_.ajouterUneCarteDansPliEnCours(info_.getPlace(), card_);
            if(info_.isDeclaringBeloteRebelote()) {
                game_.setAnnoncesBeloteRebelote(info_.getPlace(),card_);
            }
            RefreshHandPlayingBelote ref_ = new RefreshHandPlayingBelote();
            ref_.setCard(card_);
            ref_.setDeclaringBeloteRebelote(info_.isDeclaringBeloteRebelote());
            ref_.setDeclaring(info_.isDeclaring());
            ref_.setPlace(info_.getPlace());
            ref_.setDeclare(game_.getAnnonce(info_.getPlace()));
            ref_.setLocale(Constants.getDefaultLanguage());
            Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _instance), ref_);
            return;
        }
        if (_readObject instanceof RefreshingDoneBelote) {
            PlayingCardBelote p_ = new PlayingCardBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(((RefreshingDoneBelote)_readObject).getPlace());
            p_.setPlayedCard(((RefreshingDoneBelote)_readObject).getCard());
            p_.setDeclaringBeloteRebelote(((RefreshingDoneBelote)_readObject).isDeclaringBeloteRebelote());
            p_.setDeclaring(((RefreshingDoneBelote)_readObject).isDeclaring());
            p_.setDeclare(((RefreshingDoneBelote)_readObject).getDeclare());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendObject(Net.getSocketByPlace(p, _instance), p_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance)) {
                return;
            }
            byte place_ = ((PlayerActionGame)_readObject).getPlace();
            TricksHandsBelote tricksHands_ = new TricksHandsBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            tricksHands_.setRules(game_.getRegles());
            tricksHands_.setDistributionCopy(game_.getDistribution());
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setBid(game_.getContrat());
            tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_, _instance), tricksHands_);
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TEAMS) {
            if (!Net.isSameTeam(_instance)) {
                return;
            }
            byte place_ = ((PlayerActionGame)_readObject).getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            teams_.setTeams(game_.playersBelongingToSameTeam());
            Net.sendObject(Net.getSocketByPlace(place_, _instance), teams_);
            return;
        }
    }

    private static void processGamePresident(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        PlayerActionGameType actionType_ = getActionType(_readObject);
        if (actionType_ == PlayerActionGameType.DEALT) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GamePresident g_ = Net.getGames(_instance).partiePresident();
                g_.initCartesEchanges();
                g_.donnerMeilleuresCartes();
                if (g_.availableSwitchingCards()) {
                    Bytes pl_ = Net.activePlayers(_instance);
                    Bytes humWin_ = g_.getWinners(pl_);
                    Bytes humLos_ = g_.getLoosers(pl_);
                    if (!humWin_.isEmpty()) {
                        //Display discarding
                        AllowDiscarding allow_ = new AllowDiscarding();
                        for (byte p: humWin_) {
                            byte l_ = g_.getMatchingLoser(p);
                            allow_.setReceivedCards(g_.getSwitchedCards().get(l_));
                            Net.sendObject(Net.getSocketByPlace(p, _instance), allow_);
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
                        ReceivedGivenCards dis_ = new ReceivedGivenCards();
                        for (byte p: humLos_) {
                            byte w_ = g_.getMatchingWinner(p);
                            dis_.setReceived(g_.getSwitchedCards().get(w_));
                            dis_.setGiven(g_.getSwitchedCards().get(p));
                            dis_.setNewHand(g_.getDistribution().hand(w_));
                            Net.sendObject(Net.getSocketByPlace(p, _instance), dis_);
                        }
                        return;
                    }
                    g_.giveWorstCards();
                }
                //Go playing
                playingPresidentCard(_instance,_fct);
            }
            return;
        }
        if (_readObject instanceof DiscardedCards) {
            Bytes pl_ = Net.activePlayers(_instance);
            DiscardedCards dis_ = (DiscardedCards) _readObject;
            HandPresident cards_ = dis_.getDiscarded();
            byte player_ = dis_.getPlace();
            GamePresident g_ = Net.getGames(_instance).partiePresident();
            if (!g_.giveWorstCards(pl_, player_, cards_)) {
                return;
            }
            Bytes humWin_ = g_.getWinners(pl_);
            Bytes humLos_ = g_.getLoosers(pl_);
            Bytes humLosReceiving_ = new Bytes();
            for (byte p: humLos_) {
                byte w_ = g_.getMatchingWinner(p);
                if (humWin_.containsObj(w_)) {
                    humLosReceiving_.add(p);
                }
            }
            if (!humLosReceiving_.isEmpty()) {
                //refresh hands of losers
                ReceivedGivenCards disAfter_ = new ReceivedGivenCards();
                for (byte p: humLos_) {
                    byte w_ = g_.getMatchingWinner(p);
                    disAfter_.setReceived(g_.getSwitchedCards().get(w_));
                    disAfter_.setGiven(g_.getSwitchedCards().get(p));
                    disAfter_.setNewHand(g_.getDistribution().hand(w_));
                    Net.sendObject(Net.getSocketByPlace(p, _instance), disAfter_);
                }
                return;
            }
            playingPresidentCard(_instance,_fct);
            return;
        }
        if (actionType_ == PlayerActionGameType.REFHESHED_HAND_PRESIDENT) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            Bytes pl_ = Net.activePlayers(_instance);
            GamePresident g_ = Net.getGames(_instance).partiePresident();
            if (Net.allReceivedAmong(g_.getLoosers(pl_), _instance)) {
                Net.initAllReceived(_instance);
                //Go playing
                playingPresidentCard(_instance,_fct);
            }
        }
        if (_readObject instanceof PlayingCardPresident) {
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            PlayingCardPresident pl_ = (PlayingCardPresident) _readObject;
            byte player_ = pl_.getPlace();
            if (pl_.isPass()) {
                if (!game_.canPass(player_)) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setPassIssue(true);
                    e_.setReason(Games.canPassMess(game_, pl_.getLocale()));
                    e_.setCard(CardPresident.WHITE);
                    Net.sendObject(Net.getSocketByPlace(player_, _instance), e_);
                } else {
                    game_.noPlay(player_);
                    RefreshHandPlayingPresident cardDto_ = new RefreshHandPlayingPresident();
                    cardDto_.setPlayedHand(game_.getPlayedCards());
                    cardDto_.setPlace(player_);
                    cardDto_.setNextPlayer(game_.getNextPlayer());
                    cardDto_.setStatus(game_.getLastStatus());
                    cardDto_.setReversed(game_.isReversed());
                    cardDto_.setPlayedCard(CardPresident.WHITE);
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.sendObject(Net.getSocketByPlace(player_, _instance),cardDto_);
                }
            } else {
                if (!game_.allowPlaying(player_, pl_.getPlayedCard())) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setCard(pl_.getPlayedCard());
                    e_.setReason(Games.autorisePresident(game_,player_, pl_.getPlayedCard(), pl_.getIndex(), pl_.getLocale()).toString());
                    Net.sendObject(Net.getSocketByPlace(player_, _instance), e_);
                } else {
                    game_.addCardsToCurrentTrick(player_, pl_.getPlayedCard(), pl_.getIndex());
                    RefreshHandPlayingPresident cardDto_ = new RefreshHandPlayingPresident();
                    cardDto_.setPlayedHand(game_.getPlayedCards());
                    cardDto_.setPlace(player_);
                    cardDto_.setNextPlayer(game_.getNextPlayer());
                    cardDto_.setStatus(game_.getLastStatus());
                    cardDto_.setReversed(game_.isReversed());
                    cardDto_.setPlayedCard(CardPresident.WHITE);
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.sendObject(Net.getSocketByPlace(player_, _instance),cardDto_);
                }
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                GamePresident game_ = Net.getGames(_instance).partiePresident();
                if (game_.getProgressingTrick().estVide()) {

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(_fct,1000);
                        endGamePresident(_instance);
                        return;
                    }
                    ThreadUtil.sleep(_fct,3000);
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), Pause.INSTANCE);
                    }
                    return;
                }

                playingPresidentCard(_instance,_fct);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance);
                playingPresidentCard(_instance,_fct);
            }
            return;
        }
        if (_readObject instanceof RefreshingDonePresident) {
            PlayingCardPresident cardDto_ = new PlayingCardPresident();
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            cardDto_.setPlayedHand(game_.getPlayedCards());
            cardDto_.setPlace(((RefreshingDonePresident)_readObject).getPlace());
            cardDto_.setNextPlayer(game_.getNextPlayer());
            cardDto_.setStatus(game_.getLastStatus());
            cardDto_.setLocale(Constants.getDefaultLanguage());
            cardDto_.setPlayedCard(CardPresident.WHITE);
            Net.initAllReceived(_instance);
            for (byte p: Net.activePlayers(_instance)) {
                Net.sendObject(Net.getSocketByPlace(p, _instance),cardDto_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(((PlayerActionGame)_readObject).getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance)) {
                return;
            }
            byte place_ = ((PlayerActionGame)_readObject).getPlace();
            TricksHandsPresident tricksHands_ = new TricksHandsPresident();
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            tricksHands_.setReversed(game_.isReversed());
            tricksHands_.setDistributionCopy(game_.getDistribution());
            tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
            tricksHands_.setRanks(game_.getRanks());
            tricksHands_.setSwitchedCards(game_.getSwitchedCards());
            tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
            tricksHands_.setDistributionCopy(game_.getDistribution());
            Net.sendObject(Net.getSocketByPlace(place_, _instance), tricksHands_);
            return;
        }
    }

    private static void quitProcess(Object _readObject, Net _instance, AbstractThreadFactory _fct) {
        Quit bye_ = (Quit)_readObject;
        if (bye_.isServer()) {
            if (!Net.delegateServer(bye_, _instance)) {
                return;
            }
        }
        Net.quit(bye_.getPlace(), _instance);
        Bye forcedBye_ = new Bye();
        forcedBye_.setForced(false);
        forcedBye_.setServer(false);
        forcedBye_.setClosing(bye_.isClosing());
        Ints placesPlayersByValue_ = Net.getPlacesPlayersByValue(bye_.getPlace(), _instance);
        if (!placesPlayersByValue_.isEmpty()) {
            Net.removePlayer(placesPlayersByValue_.first(), forcedBye_, _instance);
        }
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            if (game_.keepBidding()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(_fct,1000);
                    if (game_.playerHasAlreadyBidded(place_)) {
                        return;
                    }
                    BiddingBelote bid_ = new BiddingBelote();
                    bid_.setPlace(place_);
                    bid_.setBidBelote(game_.getLastBid());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                }
            } else {
                byte place_ = game_.playerHavingToPlay();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(_fct,800);
                    if (game_.currentPlayerHasPlayed(place_)) {
                        return;
                    }
                    CardBelote card_ = game_.getCarteJouee();
                    boolean declareBeloteRebelote_ = false;
                    if(game_.getAnnoncesBeloteRebelote(place_).contient(card_)) {
                        declareBeloteRebelote_ = true;
                    }
                    PlayingCardBelote cardDto_ = new PlayingCardBelote();
                    cardDto_.setPlace(place_);
                    cardDto_.setPlayedCard(card_);
                    cardDto_.setDeclaringBeloteRebelote(declareBeloteRebelote_);
                    cardDto_.setDeclare(game_.getAnnonce(place_));
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), cardDto_);
                    }
                }
            }
            return;
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            byte player_ = bye_.getPlace();
            Bytes pl_ = Net.activePlayers(_instance);
            if (game_.availableSwitchingCards() && !game_.readyToPlayMulti(pl_)) {
                Bytes humWin_ = game_.getWinners(new Bytes(player_));
                if (!humWin_.isEmpty()) {
                    HandPresident h_ = game_.strategieEchange(player_);
                    if (!game_.giveWorstCards(pl_, player_, h_)) {
                        return;
                    }
                    humWin_ = game_.getWinners(pl_);
                    Bytes humLos_ = game_.getLoosers(pl_);
                    Bytes humLosReceiving_ = new Bytes();
                    for (byte p: humLos_) {
                        byte w_ = game_.getMatchingWinner(p);
                        if (humWin_.containsObj(w_)) {
                            humLosReceiving_.add(p);
                        }
                    }
                    if (!humLosReceiving_.isEmpty()) {
                        //refresh hands of losers
                        ReceivedGivenCards disAfter_ = new ReceivedGivenCards();
                        for (byte p: humLos_) {
                            byte w_ = game_.getMatchingWinner(p);
                            disAfter_.setReceived(game_.getSwitchedCards().get(w_));
                            disAfter_.setGiven(game_.getSwitchedCards().get(p));
                            disAfter_.setNewHand(game_.getDistribution().hand(w_));
                            Net.sendObject(Net.getSocketByPlace(p, _instance), disAfter_);
                        }
                        return;
                    }
                    playingPresidentCard(_instance,_fct);
                }
                return;
            }
            if (game_.getNextPlayer() == player_) {
                ThreadUtil.sleep(_fct,800);
                if (game_.currentPlayerHasPlayed(player_)) {
                    return;
                }
                PlayingCardPresident cardDto_ = new PlayingCardPresident();
                cardDto_.setPlayedHand(game_.getPlayedCards());
                cardDto_.setPlace(player_);
                cardDto_.setNextPlayer(game_.getNextPlayer());
                cardDto_.setStatus(game_.getLastStatus());
                cardDto_.setPlayedCard(CardPresident.WHITE);
                cardDto_.setLocale(Constants.getDefaultLanguage());
                Net.initAllReceived(_instance);
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance),cardDto_);
                }
            }
            return;
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            if (game_.keepBidding()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(_fct,1000);
                    if (game_.playerHasAlreadyBidded(place_)) {
                        return;
                    }
                    BiddingTarot bid_ = new BiddingTarot();
                    bid_.setPlace(place_);
                    bid_.setBid(game_.getLastBid());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                }
                return;
            }
            if (!game_.unionPlis().isEmpty() && game_.getPliEnCours().getVuParToutJoueur() && game_.keepPlayingCurrentTrick()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(_fct,800);
                    if (game_.currentPlayerHasPlayed(place_)) {
                        return;
                    }
                    CardTarot card_ = game_.getCarteJoueee();
                    PlayingCardTarot cardDto_ = new PlayingCardTarot();
                    cardDto_.setPlace(place_);
                    cardDto_.setPlayedCard(card_);
                    EnumList<Handfuls> annoncesPoignees_ = game_.getAnnoncesPoignees(place_);
                    EnumList<Miseres> annoncesMiseres_ = game_.getAnnoncesMiseres(place_);
                    HandTarot poignee_=game_.getPoignee(place_);
                    if (!annoncesPoignees_.isEmpty()) {
                        cardDto_.setChoosenHandful(annoncesPoignees_.first());
                    } else {
                        cardDto_.setChoosenHandful(Handfuls.NO);
                    }
                    cardDto_.setHandful(poignee_);
                    cardDto_.setMiseres(annoncesMiseres_);
                    cardDto_.setExcludedTrumps(new HandTarot());
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), cardDto_);
                    }
                }
                return;
            }
            if (!game_.getRegles().getDiscardAfterCall()) {
                game_.appelApresEcart();
                CalledCards calledCards_ = new CalledCards();
                calledCards_.setPlace(game_.getPreneur());
                calledCards_.setCalledCards(game_.getCarteAppelee());
                calledCards_.setLocale(Constants.getDefaultLanguage());
                Net.initAllReceived(_instance);
                for (byte p: Net.activePlayers(_instance)) {
                    Net.sendObject(Net.getSocketByPlace(p, _instance), calledCards_);
                }
                return;
            }
            HandTarot callableCards_ = game_.callableCards();
            if (!callableCards_.estVide()) {
                if (game_.getCarteAppelee().estVide()) {
                    game_.intelligenceArtificielleAppel();
                    CalledCards calledCards_ = new CalledCards();
                    calledCards_.setPlace(game_.getPreneur());
                    calledCards_.setCalledCards(game_.getCarteAppelee());
                    calledCards_.setLocale(Constants.getDefaultLanguage());
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), calledCards_);
                    }
                    return;
                }
            }

            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                game_.ecarter(game_.unionPlis().isEmpty());
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived(_instance);
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), discarded_);
                    }
                    return;
                }
                if (game_.chelemAnnonce()) {
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setPlace(game_.getPreneur());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                    return;
                }
                byte dealer_=game_.getDistribution().getDealer();
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                game_.setEntameur(game_.playerAfter(dealer_));
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct);
                return;
            }
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                game_.slam();
                if (game_.chelemAnnonce()) {
                    Net.initAllReceived(_instance);
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setPlace(game_.getPreneur());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers(_instance)) {
                        Net.sendObject(Net.getSocketByPlace(p, _instance), bid_);
                    }
                    return;
                }
            }
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct);
        }
    }
    private static void endGameBelote(Net _instance) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partieBelote().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames(_instance).contains(i)) {
                players_.add(Net.getNicknames(_instance).getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        for (byte p: Net.activePlayers(_instance)) {
            ResultsBelote res_ = new ResultsBelote();
            CustList<Longs> scores_ = Net.getScores(_instance);
            CustList<Longs> list_ = new CustList<Longs>();
            for (Longs v: scores_) {
                list_.add(new Longs(v));
            }
            res_.setGame(Net.getGames(_instance).partieBelote());
            res_.setUser(p);
            res_.initialize(new StringList(players_), list_);
            String loc_ = Net.getLanguageByPlace(p, _instance);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            Net.sendObject(Net.getSocketByPlace(p, _instance), res_);
        }
    }
    private static void endGamePresident(Net _instance) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partiePresident().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames(_instance).contains(i)) {
                players_.add(Net.getNicknames(_instance).getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(Net.getGames(_instance).partiePresident());
        CustList<Longs> scores_ = Net.getScores(_instance);
        CustList<Longs> list_ = new CustList<Longs>();
        for (Longs v: scores_) {
            list_.add(new Longs(v));
        }
        res_.initialize(new StringList(players_), list_);
        for (byte p: Net.activePlayers(_instance)) {
            String loc_ = Net.getLanguageByPlace(p, _instance);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.setUser(p);
            Net.sendObject(Net.getSocketByPlace(p, _instance), res_);
        }
    }
    private static void endGameTarot(Net _instance) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partieTarot().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames(_instance).contains(i)) {
                players_.add(Net.getNicknames(_instance).getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        for (byte p: Net.activePlayers(_instance)) {
            ResultsTarot res_ = new ResultsTarot();
            res_.setGame(Net.getGames(_instance).partieTarot());
            CustList<Longs> scores_ = Net.getScores(_instance);
            CustList<Longs> list_ = new CustList<Longs>();
            for (Longs v: scores_) {
                list_.add(new Longs(v));
            }
            String loc_ = Net.getLanguageByPlace(p, _instance);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.setUser(p);
            res_.initialize(new StringList(players_), list_);
            Net.sendObject(Net.getSocketByPlace(p, _instance), res_);
        }
    }
    private static void playingBeloteCard(Net _instance, AbstractThreadFactory _fct) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance)) {
            AllowPlayingBelote decla_ = new AllowPlayingBelote();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setFirstRoundPlaying(game_.premierTour());
            if (game_.premierTour()) {
                decla_.setDeclaration(game_.strategieAnnonces());
            } else {
                decla_.setDeclaration(new DeclareHandBelote());
            }
            decla_.setPossibleBeloteRebelote(!game_.cartesBeloteRebelote().estVide());
            decla_.setAllowedBeloteRebelote(game_.autoriseBeloteRebelote(place_));
            Net.sendObject(Net.getSocketByPlace(place_, _instance),decla_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        CardBelote card_ = game_.getCarteJouee();
        boolean declareBeloteRebelote_ = false;
        if(game_.getAnnoncesBeloteRebelote(place_).contient(card_)) {
            declareBeloteRebelote_ = true;
        }
        PlayingCardBelote cardDto_ = new PlayingCardBelote();
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
        cardDto_.setLocale(Constants.getDefaultLanguage());
        Net.initAllReceived(_instance);
        for (byte p: Net.activePlayers(_instance)) {
            Net.sendObject(Net.getSocketByPlace(p, _instance),cardDto_);
        }
    }
    private static void playingPresidentCard(Net _instance, AbstractThreadFactory _fct) {
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        byte place_ = game_.getNextPlayer();
        if (Net.isHumanPlayer(place_, _instance)) {
            AllowPlayingPresident allow_ = new AllowPlayingPresident();
            allow_.setEnabledPass(!game_.getProgressingTrick().estVide());
            allow_.setStatus(game_.getStatus(place_));
            allow_.setReversed(game_.isReversed());
            Net.sendObject(Net.getSocketByPlace(place_, _instance),allow_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        PlayingCardPresident cardDto_ = new PlayingCardPresident();
        cardDto_.setPlayedHand(game_.getPlayedCards());
        cardDto_.setPlace(place_);
        cardDto_.setNextPlayer(game_.getNextPlayer());
        cardDto_.setStatus(game_.getLastStatus());
        cardDto_.setPlayedCard(CardPresident.WHITE);
        cardDto_.setLocale(Constants.getDefaultLanguage());
        Net.initAllReceived(_instance);
        for (byte p: Net.activePlayers(_instance)) {
            Net.sendObject(Net.getSocketByPlace(p, _instance),cardDto_);
        }
    }
    private static void playingTarotCard(Net _instance, AbstractThreadFactory _fct) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance)) {
            AllowPlayingTarot decla_ = new AllowPlayingTarot();
            decla_.setTakerIndex(game_.getPreneur());
            boolean firstRound_ = game_.premierTourNoMisere();
            decla_.setFirstRoundPlaying(game_.premierTour());
            if (firstRound_) {
                EnumList<Handfuls> handfuls_ = new EnumList<Handfuls>(game_.getRegles().getCurrentAllowedHandfuls());
                decla_.setAllowedHandfuls(new EnumList<Handfuls>(handfuls_));
                decla_.setRequiredTrumps(new EnumMap<Handfuls,Integer>(game_.getRegles().getPoigneesAutorisees()));
                decla_.setAllowedMiseres(new EnumList<Miseres>(game_.getRegles().getMiseres()));
            } else {
                decla_.setAllowedHandfuls(new EnumList<Handfuls>());
                decla_.setRequiredTrumps(new EnumMap<Handfuls,Integer>());
                decla_.setAllowedMiseres(new EnumList<Miseres>());
            }
            Net.sendObject(Net.getSocketByPlace(place_, _instance), decla_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        CardTarot card_ = game_.getCarteJoueee();
        PlayingCardTarot cardDto_ = new PlayingCardTarot();
        cardDto_.setTakerIndex(game_.getPreneur());
        cardDto_.setPlace(place_);
        cardDto_.setPlayedCard(card_);
        cardDto_.setLocale(Constants.getDefaultLanguage());
        EnumList<Handfuls> annoncesPoignees_ = game_.getAnnoncesPoignees(place_);
        EnumList<Miseres> annoncesMiseres_ = game_.getAnnoncesMiseres(place_);
        HandTarot poignee_=game_.getPoignee(place_);
        if (!annoncesPoignees_.isEmpty()) {
            cardDto_.setChoosenHandful(annoncesPoignees_.first());
        } else {
            cardDto_.setChoosenHandful(Handfuls.NO);
        }
        cardDto_.setHandful(poignee_);
        cardDto_.setMiseres(annoncesMiseres_);
        cardDto_.setExcludedTrumps(new HandTarot());
        Net.initAllReceived(_instance);
        for (byte p: Net.activePlayers(_instance)) {
            Net.sendObject(Net.getSocketByPlace(p, _instance), cardDto_);
        }
    }
}
