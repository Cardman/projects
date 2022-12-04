package cards.network.threads;

import cards.belote.*;
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
import cards.network.common.before.*;
import cards.gui.TeamsPlayers;
import cards.network.president.actions.DiscardedCards;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.displaying.players.RefreshingDonePresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.sml.DocumentReaderCardsMultiUtil;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.displaying.players.RefreshHand;
import cards.network.tarot.displaying.players.RefreshingDone;
import cards.network.tarot.displaying.players.SeenDiscardedTrumps;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.president.*;
import cards.president.enumerations.CardPresident;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.initialize.AbstractSocket;
import code.network.*;
import code.sml.Document;
import code.sml.Element;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.consts.Constants;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServerCards extends BasicServer {

    private static final String EMPTY_STRING = "";

    private final AbstractBaseExecutorService lock;

    private final Net instance;
    /**This class thread is independant from EDT*/
    public SendReceiveServerCards(AbstractSocket _socket, NetGroupFrame _net, Net _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input, Document _object) {
        lock.execute(new ServerIterationCards(_input,_object,instance,getNet().getThreadFactory(),getNet().getSockets()));
    }

    static void loop(String _input, Document _doc, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Element elt_ = _doc.getDocumentElement();
        PlayerActionBeforeGameCards playerActionBeforeGame_ = DocumentReaderCardsMultiUtil.getPlayerActionBeforeGame(elt_);
        if (playerActionBeforeGame_ instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)playerActionBeforeGame_;
            if (!newPlayer_.isAcceptable()) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setBusy(true);
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _common);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
        }
        if (playerActionBeforeGame_ instanceof NewPlayerCards) {
            NewPlayerCards newPlayer_ = (NewPlayerCards)playerActionBeforeGame_;
            if (Net.getNbPlayers(_instance) == _common.getNicknames().size()) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _common);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            if (Net.isProgressingGame(_instance)) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_, _common);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            Net.getPlayersLocales(_instance).put(newPlayer_.getIndex(), newPlayer_.getLanguage());
            _common.getNicknames().put(newPlayer_.getIndex(),newPlayer_.getPseudo());
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
            pl_.setPseudos(new IntMap<String>(_common.getNicknames()));
            pl_.setPlacesPlayers(_common.getPlacesPlayers());
            pl_.setReadyPlayers(new IntMap<BoolVal>(_common.getReadyPlayers()));
            for (int p:_common.getSockets().getKeys()) {
                pl_.setFirst(p == newPlayer_.getIndex());
                Net.sendObject(_common.getSockets().getVal(p),pl_);
            }
            return;
        }
        if (playerActionBeforeGame_ instanceof ChoosenPlace) {
            int noClient_ = playerActionBeforeGame_.getIndex();
            _common.getPlacesPlayers().put(noClient_, (byte)((ChoosenPlace)playerActionBeforeGame_).getPlace());
            ((ChoosenPlace) playerActionBeforeGame_).setPlacesPlayers(_common.getPlacesPlayers());
            for(AbstractSocket so_:_common.getSockets().values()) {
                Net.sendObject(so_, playerActionBeforeGame_);
            }
            return;
        }
        if (playerActionBeforeGame_ instanceof Ready) {
            if (Net.getGames(_instance).enCoursDePartie()) {
                int noClient_ = playerActionBeforeGame_.getIndex();
                _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of((( Ready)playerActionBeforeGame_).isReady()));
                if (_common.allReady()) {
                    Net.sendOkToQuit(_instance, _common);
                }
                return;
            }
            int noClient_ = playerActionBeforeGame_.getIndex();
            _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of((( Ready)playerActionBeforeGame_).isReady()));
            for(AbstractSocket so_:_common.getSockets().values()) {
                NetGroupFrame.trySendString(_input, so_);
            }
            return;
        }
        String tag_ = DocumentReaderCardsMultiUtil.tagName(elt_);
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_BELOTE, tag_)
        ||StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_PRESIDENT, tag_)
                ||StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_TAROT, tag_)) {
            for(AbstractSocket so_:_common.getSockets().values()) {
                NetGroupFrame.trySendString(_input, so_);
            }
            return;
        }
        PlayerActionGame playerActionGame_ = DocumentReaderCardsMultiUtil.getPlayerActionGame(elt_);
        if (playerActionGame_ instanceof Quit) {
            Quit q_ = (Quit) playerActionGame_;
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
                    Net.sendObject(Net.getSocketByPlace(p, _common),forcedBye_);
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
                Net.removePlayer(placesPlayersByValue_.first(), forcedBye_, _common);
            }
//            quitProcess((Quit) playerActionGame_, _instance,_fct);
            return;
        }
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_PLAY_GAME,tag_)) {
            Net.initAllPresent(_instance,_common);
            Net.initAllReceived(_instance,_common);
            if (Net.getGames(_instance).enCoursDePartieBelote()) {
                Net.setProgressingGame(true, _instance);
                DealBelote deal_ = Net.getGames(_instance).partieBelote().getDistribution();
                DealtHandBelote hand_ = new DealtHandBelote();
                hand_.setDeck(deal_.derniereMain());
                hand_.setDealer(Net.getGames(_instance).partieBelote().playerAfter(deal_.getDealer()));
                hand_.setAllowedBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
                hand_.setRep(Net.getGames(_instance).partieBelote().getRegles().getDealing());
                hand_.setPoints(Net.getGames(_instance).partieBelote().getBid().getPoints());
                for (byte i:Net.activePlayers(_instance, _common)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _common), hand_);
                }
            } else if (Net.getGames(_instance).enCoursDePartiePresident()) {
                Net.setProgressingGame(true, _instance);
                int nbSuits_ = Suit.couleursOrdinaires().size();
                RulesPresident rules_ = Net.getGames(_instance).partiePresident().getRules();
                int nbStacks_ = rules_.getNbStacks();
                DealPresident deal_ = Net.getGames(_instance).partiePresident().getDeal();
                DealtHandPresident hand_ = new DealtHandPresident();
                hand_.setDealer(Net.getGames(_instance).partiePresident().getDeal().getDealer());
                hand_.setMaxCards(NumberUtil.min(nbSuits_ * nbStacks_, rules_.getNbMaxCardsPerPlayer()));
                hand_.setStatus(Net.getGames(_instance).partiePresident().getLastStatus());
                for (byte i:Net.activePlayers(_instance, _common)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _common), hand_);
                }
            } else if (Net.getGames(_instance).enCoursDePartieTarot()) {
                Net.setProgressingGame(true, _instance);
                DealTarot deal_ = Net.getGames(_instance).partieTarot().getDistribution();
                DealtHandTarot hand_ = new DealtHandTarot();
                hand_.setDog(deal_.derniereMain());
                hand_.setDealer(Net.getGames(_instance).partieTarot().playerAfter(deal_.getDealer()));
                hand_.setAllowedBids(Net.getGames(_instance).partieTarot().allowedBids());
                hand_.setRep(Net.getGames(_instance).partieTarot().getRegles().getDealing());
                for (byte i:Net.activePlayers(_instance, _common)) {
                    hand_.setCards(deal_.hand(i));
                    Net.sendObject(Net.getSocketByPlace(i, _common), hand_);
                }
            }
            return;
        }
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            processGameBelote(_input,playerActionGame_, _instance,_fct, _common);
            return;
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            processGamePresident(playerActionGame_, _instance,_fct, _common);
            return;
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TAKE_CARD,tag_)) {
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.ajouterCartesUtilisateur();
                if (!game_.getRegles().getDiscardAfterCall()) {
                    CallableCards callableCardsDto_ = new CallableCards();
                    callableCardsDto_.setDiscarding(true);
                    callableCardsDto_.setCallableCards(game_.callableCards());
                    Net.sendObject(Net.getSocketByPlace(game_.getPreneur(), _common), callableCardsDto_);
                }
                return;
            }
            processGameTarot(_input, playerActionGame_, _instance,_fct, _common);
            return;
        }
    }

    private static void processGameTarot(String _input, PlayerActionGame _action, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (_action == null) {
            return;
        }
        PlayerActionGameType actionType_ = _action.getActionType();
        if (actionType_ == PlayerActionGameType.DEALT) {
            processDealtTarot(_action, _instance,_fct,_common);
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_BIDDING) {
            processBetweenBidsTarot(_action, _instance,_fct, _common);
            return;
        }
        if (_action instanceof BiddingTarot) {
            BiddingTarot bid_ = (BiddingTarot)_action;
            BidTarot b_ = bid_.getBid();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!b_.estDemandable(game_.getContrat())) {
                ErrorBidding error_ = new ErrorBidding();
                error_.setBid(game_.getContrat());
                Net.sendObject(Net.getSocketByPlace(bid_.getPlace(), _common), error_);
                return;
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(_input, Net.getSocketByPlace(p, _common));
            }
            return;
        }
        if (_action instanceof CalledCards) {
            processCallingTarot(_action, _instance,_fct,_common);
            return;
        }
        if (_action instanceof DiscardedCard) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            DiscardedCard discarded_ = (DiscardedCard) _action;
            if (!discarded_.isInHand()) {
                game_.retirerUneCarteDuChien(discarded_.getCard());
                game_.addCard(discarded_.getPlace(),discarded_.getCard());
                NetGroupFrame.trySendString(_input, Net.getSocketByPlace(discarded_.getPlace(), _common));
                return;
            }
            ReasonDiscard reason_ = game_.autoriseEcartDe(discarded_.getCard());
            if (reason_ != ReasonDiscard.NOTHING) {
                ErrorDiscarding error_ = new ErrorDiscarding();
                error_.setErrorMessage(Games.autoriseMessEcartDe(game_,reason_,discarded_.getCard(), discarded_.getLocale()).toString());
                error_.setCard(discarded_.getCard());
                Net.sendObject(Net.getSocketByPlace(discarded_.getPlace(), _common), error_);
                return;
            }
            game_.ajouterUneCarteDansPliEnCours(discarded_.getPlace(),discarded_.getCard());
            NetGroupFrame.trySendString(_input, Net.getSocketByPlace(discarded_.getPlace(), _common));
            return;
        }
        if (actionType_ == PlayerActionGameType.CALLED_CARD_KNOWN) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog show_ = new Dog();
                    show_.setDog(game_.getDistribution().derniereMain());
                    show_.setTaker(false);
                    show_.setHumanTaker(false);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObject(Net.getSocketByPlace(p, _common), show_);
                    }
                    return;
                }
                game_.gererChienInconnu();
                if (!game_.getContrat().isFaireTousPlis()) {
                    game_.slam();
                    if (game_.chelemAnnonce()) {
                        Net.initAllReceived(_instance, _common);
                        PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                        bid_.setPlace(game_.getPreneur());
                        bid_.setLocale(Constants.getDefaultLanguage());
                        for (byte p: Net.activePlayers(_instance, _common)) {
                            Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                        }
                        return;
                    }
                }
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
            }
            return;
        }
        if (_action instanceof SeenDiscardedTrumps) {
            processDiscardingTrumps(_action, _instance,_fct,_common);
            return;
        }
        if (actionType_ == PlayerActionGameType.SHOW_DOG) {
            processShowDogTarot(_action, _instance,_fct,_common);
            return;
        }
        if (actionType_ == PlayerActionGameType.VALIDATE_DOG) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                game_.addCurTrick();
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived(_instance, _common);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObject(Net.getSocketByPlace(p, _common), discarded_);
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
            playingTarotCard(_instance,_fct, _common);
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
                    Net.initAllReceived(_instance, _common);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObject(Net.getSocketByPlace(p, _common), discarded_);
                    }
                    return;
                }
            }
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(_input, Net.getSocketByPlace(p, _common));
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_DISPLAY_SLAM) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
                return;
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            processBetweenPlayedCardsTarot(_action, _instance,_fct, _common);
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
            }
            return;
        }
        if (_action instanceof PlayingCardTarot) {
            processPlayingTarot(_action, _instance, _common);
            return;
        }
        if (_action instanceof RefreshingDone) {
            PlayingCardTarot p_ = new PlayingCardTarot();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(_action.getPlace());
            p_.setPlayedCard(((RefreshingDone)_action).getCard());
            p_.setChoosenHandful(((RefreshingDone)_action).getChoosenHandful());
            p_.setHandful(((RefreshingDone)_action).getHandful());
            p_.setMiseres(((RefreshingDone)_action).getMiseres());
            p_.setCalledCard(((RefreshingDone)_action).isCalledCard());
            p_.setExcludedTrumps(new HandTarot());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                Net.sendObject(Net.getSocketByPlace(p, _common), p_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReady(_common);
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        processShowTarot(_action, _instance, actionType_, _common);
    }

    private static void processBetweenBidsTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance, _common);

            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!game_.keepBidding()) {
                processAfterBidTarot(game_, _instance,_fct, _common);
                return;
            }
            byte place_ = game_.playerHavingToBid();
            if (Net.isHumanPlayer(place_, _instance, _common)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(game_.allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_, _common), allowedBids_);
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
            for (byte p: Net.activePlayers(_instance, _common)) {
                Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
            }
        }
        return;
    }

    private static void processBetweenPlayedCardsTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance, _common);
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (!game_.keepPlayingCurrentTrick()) {
                game_.ajouterPetitAuBoutPliEnCours();

                if (!game_.keepPlayingCurrentGame()) {
                    ThreadUtil.sleep(_fct,1000);
                    endGameTarot(_instance, _common);
                    return;
                }
                ThreadUtil.sleep(_fct,3000);
                Net.initAllReceived(_instance, _common);
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObjectPause(Net.getSocketByPlace(p, _common));
                }
                return;
            }

            playingTarotCard(_instance,_fct,_common);
        }
        return;
    }

    private static void processDealtTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance, _common);
            if (!Net.getGames(_instance).partieTarot().avecContrat()) {
                GameTarot game_ = Net.getGames(_instance).partieTarot();
                game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
                return;
            }
            byte place_ = Net.getGames(_instance).partieTarot().playerHavingToBid();
            if (Net.isHumanPlayer(place_, _instance, _common)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(Net.getGames(_instance).partieTarot().allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_, _common), allowedBids_);
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
            for (byte p: Net.activePlayers(_instance, _common)) {
                Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
            }
        }
    }

    private static void processShowDogTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            Net.initAllReceived(_instance, _common);
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (game_.getRegles().getDiscardAfterCall()) {
                game_.ecarter(true);
            }
            ThreadUtil.sleep(_fct,5000);
            if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                DiscardedTrumps discarded_ = new DiscardedTrumps();
                discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                Net.initAllReceived(_instance, _common);
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), discarded_);
                }
                return;
            }
            if (game_.chelemAnnonce()) {
                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                }
                return;
            }
            byte dealer_=game_.getDistribution().getDealer();
            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
            game_.setEntameur(game_.playerAfter(dealer_));
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct, _common);
            return;
        }
        return;
    }

    private static void processPlayingTarot(PlayerActionGame _readObject, Net _instance, NetCommon _common) {
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
                Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), error_);
                return;
            }
        }
        if (!game_.autorise(card_)) {
            ErrorPlaying error_ = new ErrorPlaying();
            error_.setCard(card_);
            error_.setReason(Games.autoriseTarot(game_, info_.getLocale()));
            Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), error_);
            return;
        }
        game_.changerConfiance();
        game_.ajouterUneCarteDansPliEnCours(info_.getPlace(), card_);
        if (info_.getChoosenHandful() != Handfuls.NO) {
            IdList<Handfuls> handfuls_ = new IdList<Handfuls>();
            handfuls_.add(info_.getChoosenHandful());
            game_.setAnnoncesPoignees(info_.getPlace(), handfuls_);
            game_.ajouterPoignee(info_.getHandful(), info_.getPlace());
        }
        IdList<Miseres> declaredMiseres_ = new IdList<Miseres>();
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
        Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), ref_);
    }

    private static void processShowTarot(PlayerActionGame _action, Net _instance, PlayerActionGameType _actionType, NetCommon _common) {
        if (_actionType == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance, _common)) {
                return;
            }
            byte place_ = _action.getPlace();
            TricksHandsTarot tricksHands_ = new TricksHandsTarot();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            tricksHands_.setDistributionCopy(game_.getDistribution());
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_, _common), tricksHands_);
            return;
        }
        if (_actionType == PlayerActionGameType.SELECT_TEAMS) {
            if (!Net.isSameTeam(_instance, _common)) {
                return;
            }
            byte place_ = _action.getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            teams_.setTeams(game_.getTeamsRelation().teams());
            Net.sendObject(Net.getSocketByPlace(place_, _common), teams_);
        }
    }

    private static void processDiscardingTrumps(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
        if (Net.allReceived(_instance)) {
            GameTarot game_ = Net.getGames(_instance).partieTarot();
            if (Net.isHumanPlayer(game_.getPreneur(), _instance, _common)) {
                if (((SeenDiscardedTrumps)_readObject).isDeclaringSlam()) {
                    Net.initAllReceived(_instance, _common);
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(game_.getPreneur());
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                    }
                    return;
                }
                byte donneur_=game_.getDistribution().getDealer();
                if(!game_.chelemAnnonce()) {
                    /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                    game_.setEntameur(game_.playerAfter(donneur_));
                }
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
                return;
            }
            if (game_.chelemAnnonce()) {
                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                }
                return;
            }
            byte donneur_=game_.getDistribution().getDealer();
            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
            game_.setEntameur(game_.playerAfter(donneur_));
            game_.setPliEnCours(true);
            playingTarotCard(_instance,_fct, _common);
            return;
        }
        return;
    }

    private static void processCallingTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        //called cards by a human player
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        CalledCards calledCards_ = (CalledCards) _readObject;
        game_.initConfianceAppeleUtilisateur(calledCards_.getCalledCards());
        if (!game_.getRegles().getDiscardAfterCall()) {
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObjectDisplaySlamButton(Net.getSocketByPlace(game_.getPreneur(), _common));
            } else {
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
                return;
            }
        }else if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
            //before taking cards of the dog
            Dog dog_ = new Dog();
            dog_.setHumanTaker(true);
            dog_.setDog(game_.getDistribution().derniereMain());
            for (byte p: Net.activePlayers(_instance, _common)) {
                dog_.setTaker(p == game_.getPreneur());
                Net.sendObject(Net.getSocketByPlace(p, _common), dog_);
            }
        } else {
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObjectDisplaySlamButton(Net.getSocketByPlace(game_.getPreneur(), _common));
            } else {
                game_.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
                return;
            }
        }
        return;
    }

    private static void processAfterBidTarot(GameTarot _game, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        //Call, dog or play
        if (!_game.getContrat().isJouerDonne()) {
            if (_game.pasJeuApresPasse()) {
                endGameTarot(_instance, _common);
            } else {
                _game.setEntameur(_game.playerAfter(_game.getDistribution().getDealer()));
                _game.setPliEnCours(true);
                playingTarotCard(_instance,_fct, _common);
            }
            return;
        }

        CallingCard appel_= _game.getRegles().getDealing().getAppel();
        if(appel_==CallingCard.DEFINED||appel_==CallingCard.WITHOUT) {
            if(appel_==CallingCard.DEFINED) {
                _game.initEquipeDeterminee();
            } else {
                _game.initDefense();
            }
        }
        if (Net.isHumanPlayer(_game.getPreneur(), _instance, _common)) {
            HandTarot callableCards_ = _game.callableCards();
            if (callableCards_.estVide()) {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog dog_ = new Dog();
                    dog_.setHumanTaker(true);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setDog(_game.getDistribution().derniereMain());
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p, _common), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                if (!_game.getContrat().isFaireTousPlis()) {
                    Net.sendObjectDisplaySlamButton(Net.getSocketByPlace(_game.getPreneur(), _common));
                } else {
                    playingTarotCard(_instance,_fct, _common);
                    return;
                }
            } else if (_game.getRegles().getDiscardAfterCall()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers(_instance, _common)) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p, _common), callableCardsDto_);
                }
            } else {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Net.initAllReceived(_instance, _common);
                    Dog dog_ = new Dog();
                    dog_.setDog(_game.getDistribution().derniereMain());
                    dog_.setTaker(false);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setHumanTaker(true);
                    dog_.setCallAfter(true);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p, _common), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers(_instance, _common)) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p, _common), callableCardsDto_);
                }
                return;
            }
            return;
        }
        HandTarot callableCards_ = _game.callableCards();
        if (callableCards_.estVide()) {
            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                Net.initAllReceived(_instance, _common);
                Dog show_ = new Dog();
                show_.setDog(_game.getDistribution().derniereMain());
                show_.setTaker(false);
                show_.setTakerIndex(_game.getPreneur());
                show_.setHumanTaker(false);
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), show_);
                }
                return;
            }
            _game.gererChienInconnu();
            if (!_game.getContrat().isFaireTousPlis()) {
                _game.slam();
                if (_game.chelemAnnonce()) {
                    Net.initAllReceived(_instance, _common);
                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(_game.getPreneur());
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                    }
                    return;
                }
            }
            playingTarotCard(_instance,_fct, _common);
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
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common), calledCards_);
        }
        return;
    }

    private static void processGameBelote(String _input, PlayerActionGame _action, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (_action == null) {
            return;
        }
        PlayerActionGameType actionType_ = _action.getActionType();
        if (actionType_ == PlayerActionGameType.DEALT) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                byte place_ = Net.getGames(_instance).partieBelote().playerHavingToBid();
                if (Net.isHumanPlayer(place_, _instance, _common)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
                    allowedBids_.setPoints(Net.getGames(_instance).partieBelote().getBid().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_, _common), allowedBids_);
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
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                }
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_BIDDING) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                if (!game_.keepBidding()) {
                    if (!game_.getBid().jouerDonne()) {
                        endGameBelote(_instance,_common);
                    } else {
                        if (game_.completedDeal()) {
                            return;
                        }
                        for (byte p: Net.activePlayers(_instance, _common)) {
                            RefreshHandBelote hand_ = new RefreshHandBelote();
                            hand_.setRefreshedHand(game_.getDistribution().hand(p));
                            hand_.setLocale(Constants.getDefaultLanguage());
                            Net.sendObject(Net.getSocketByPlace(p, _common), hand_);
                        }
                    }
                    return;
                }
                byte place_ = game_.playerHavingToBid();
                if (Net.isHumanPlayer(place_, _instance, _common)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(game_.getGameBeloteBid().allowedBids());
                    allowedBids_.setPoints(game_.getBid().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_, _common), allowedBids_);
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
                for (byte p: Net.activePlayers(_instance, _common)) {
                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
                }
            }
            return;
        }
        if (_action instanceof BiddingBelote) {
            BiddingBelote bid_ = (BiddingBelote)_action;
            BidBeloteSuit b_ = bid_.getBidBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            if (!game_.getRegles().dealAll()) {
                if (!b_.estDemandable(game_.getBid())) {
                    ErrorBiddingBelote error_ = new ErrorBiddingBelote();
                    error_.setBid(game_.getBid());
                    Net.sendObject(Net.getSocketByPlace(bid_.getPlace(), _common), error_);
                    return;
                }
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            if (!game_.getRegles().dealAll()) {
                if (game_.tailleContrats() == game_.getNombreDeJoueurs()) {
                    game_.finEncherePremierTour();
                }
            }
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(_input, Net.getSocketByPlace(p, _common));
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.COMPLETED_HAND) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                byte donneur_=game_.getDistribution().getDealer();
                game_.setEntameur(game_.playerAfter(donneur_));
                if (game_.getRegles().dealAll()) {
                    int pts_ = game_.getBid().getPoints();
                    if (pts_ >= HandBelote.pointsTotauxDixDeDer(game_.getBid())) {
                        game_.setEntameur(game_.getPreneur());
                    }
                }
                game_.setPliEnCours();
                playingBeloteCard(_instance,_fct,_common);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReady(_common);
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                if (!game_.keepPlayingCurrentTrick()) {
                    game_.ajouterDixDeDerPliEnCours();

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(_fct,1000);
                        endGameBelote(_instance, _common);
                        return;
                    }
                    ThreadUtil.sleep(_fct,3000);
                    Net.initAllReceived(_instance, _common);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObjectPause(Net.getSocketByPlace(p, _common));
                    }
                    return;
                }

                playingBeloteCard(_instance,_fct, _common);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GameBelote game_ = Net.getGames(_instance).partieBelote();
                game_.setPliEnCours();
                playingBeloteCard(_instance,_fct, _common);
            }
            return;
        }
        if (_action instanceof PlayingCardBelote) {
            PlayingCardBelote info_ = (PlayingCardBelote) _action;
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
                    Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), error_);
                    return;
                }
            }
            if (!autorise_) {
                ErrorPlayingBelote error_ = new ErrorPlayingBelote();
                error_.setCard(card_);
                error_.setCards(new HandBelote());
                error_.setReason(Games.autoriseBelote(game_,info_.getLocale()));
                Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), error_);
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
            Net.sendObject(Net.getSocketByPlace(info_.getPlace(), _common), ref_);
            return;
        }
        if (_action instanceof RefreshingDoneBelote) {
            PlayingCardBelote p_ = new PlayingCardBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(_action.getPlace());
            p_.setPlayedCard(((RefreshingDoneBelote)_action).getCard());
            p_.setDeclaringBeloteRebelote(((RefreshingDoneBelote)_action).isDeclaringBeloteRebelote());
            p_.setDeclaring(((RefreshingDoneBelote)_action).isDeclaring());
            p_.setDeclare(((RefreshingDoneBelote)_action).getDeclare());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                Net.sendObject(Net.getSocketByPlace(p, _common), p_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance, _common)) {
                return;
            }
            byte place_ = _action.getPlace();
            TricksHandsBelote tricksHands_ = new TricksHandsBelote();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            tricksHands_.setRules(game_.getRegles());
            tricksHands_.setDistributionCopy(game_.getDistribution());
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setBid(game_.getBid());
            tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_, _common), tricksHands_);
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TEAMS) {
            if (!Net.isSameTeam(_instance, _common)) {
                return;
            }
            byte place_ = _action.getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameBelote game_ = Net.getGames(_instance).partieBelote();
            teams_.setTeams(game_.playersBelongingToSameTeam());
            Net.sendObject(Net.getSocketByPlace(place_, _common), teams_);
            return;
        }
    }

    private static void processGamePresident(PlayerActionGame _action, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (_action == null) {
            return;
        }
        PlayerActionGameType actionType_ = _action.getActionType();
        if (actionType_ == PlayerActionGameType.DEALT) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GamePresident g_ = Net.getGames(_instance).partiePresident();
                g_.initCartesEchanges();
                g_.donnerMeilleuresCartes();
                if (g_.availableSwitchingCards()) {
                    Bytes pl_ = Net.activePlayers(_instance, _common);
                    Bytes humWin_ = g_.getWinners(pl_);
                    Bytes humLos_ = g_.getLoosers(pl_);
                    if (!humWin_.isEmpty()) {
                        //Display discarding
                        AllowDiscarding allow_ = new AllowDiscarding();
                        for (byte p: humWin_) {
                            byte l_ = g_.getMatchingLoser(p);
                            allow_.setReceivedCards(g_.getSwitchedCards().get(l_));
                            Net.sendObject(Net.getSocketByPlace(p, _common), allow_);
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
                            dis_.setNewHand(g_.getDeal().hand(w_));
                            Net.sendObject(Net.getSocketByPlace(p, _common), dis_);
                        }
                        return;
                    }
                    g_.giveWorstCards();
                }
                //Go playing
                playingPresidentCard(_instance,_fct,_common);
            }
            return;
        }
        if (_action instanceof DiscardedCards) {
            Bytes pl_ = Net.activePlayers(_instance, _common);
            DiscardedCards dis_ = (DiscardedCards) _action;
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
                    disAfter_.setNewHand(g_.getDeal().hand(w_));
                    Net.sendObject(Net.getSocketByPlace(p, _common), disAfter_);
                }
                return;
            }
            playingPresidentCard(_instance,_fct, _common);
            return;
        }
        if (actionType_ == PlayerActionGameType.REFHESHED_HAND_PRESIDENT) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            Bytes pl_ = Net.activePlayers(_instance, _common);
            GamePresident g_ = Net.getGames(_instance).partiePresident();
            if (Net.allReceivedAmong(g_.getLoosers(pl_), _instance)) {
                Net.initAllReceived(_instance, _common);
                //Go playing
                playingPresidentCard(_instance,_fct, _common);
            }
        }
        if (_action instanceof PlayingCardPresident) {
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            PlayingCardPresident pl_ = (PlayingCardPresident) _action;
            byte player_ = pl_.getPlace();
            if (pl_.isPass()) {
                if (!game_.canPass(player_)) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setPassIssue(true);
                    e_.setReason(Games.canPassMess(game_, pl_.getLocale()));
                    e_.setCard(CardPresident.WHITE);
                    Net.sendObject(Net.getSocketByPlace(player_, _common), e_);
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
                    Net.sendObject(Net.getSocketByPlace(player_, _common),cardDto_);
                }
            } else {
                if (!game_.allowPlaying(player_, pl_.getPlayedCard())) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setCard(pl_.getPlayedCard());
                    e_.setReason(Games.autorisePresident(game_,player_, pl_.getPlayedCard(), pl_.getIndex(), pl_.getLocale()).toString());
                    Net.sendObject(Net.getSocketByPlace(player_, _common), e_);
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
                    Net.sendObject(Net.getSocketByPlace(player_, _common),cardDto_);
                }
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                GamePresident game_ = Net.getGames(_instance).partiePresident();
                if (game_.getProgressingTrick().estVide()) {

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(_fct,1000);
                        endGamePresident(_instance, _common);
                        return;
                    }
                    ThreadUtil.sleep(_fct,3000);
                    Net.initAllReceived(_instance, _common);
                    for (byte p: Net.activePlayers(_instance, _common)) {
                        Net.sendObjectPause(Net.getSocketByPlace(p, _common));
                    }
                    return;
                }

                playingPresidentCard(_instance,_fct, _common);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReceived(_instance, _common);
                playingPresidentCard(_instance,_fct, _common);
            }
            return;
        }
        if (_action instanceof RefreshingDonePresident) {
            PlayingCardPresident cardDto_ = new PlayingCardPresident();
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            cardDto_.setPlayedHand(game_.getPlayedCards());
            cardDto_.setPlace(_action.getPlace());
            cardDto_.setNextPlayer(game_.getNextPlayer());
            cardDto_.setStatus(game_.getLastStatus());
            cardDto_.setLocale(Constants.getDefaultLanguage());
            cardDto_.setPlayedCard(CardPresident.WHITE);
            Net.initAllReceived(_instance, _common);
            for (byte p: Net.activePlayers(_instance, _common)) {
                Net.sendObject(Net.getSocketByPlace(p, _common),cardDto_);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.OK) {
            Net.setReceivedForPlayer(_action.getPlace(), _instance);
            if (Net.allReceived(_instance)) {
                Net.initAllReady(_common);
                Net.setProgressingGame(false, _instance);
            }
            return;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            if (!Net.isSameTeam(_instance, _common)) {
                return;
            }
            byte place_ = _action.getPlace();
            TricksHandsPresident tricksHands_ = new TricksHandsPresident();
            GamePresident game_ = Net.getGames(_instance).partiePresident();
            tricksHands_.setReversed(game_.isReversed());
            tricksHands_.setDistributionCopy(game_.getDeal());
            tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
            tricksHands_.setRanks(game_.getRanks());
            tricksHands_.setSwitchedCards(game_.getSwitchedCards());
            tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
            tricksHands_.setDistributionCopy(game_.getDeal());
            Net.sendObject(Net.getSocketByPlace(place_, _common), tricksHands_);
            return;
        }
    }

//    private static void quitProcess(Quit _readObject, Net _instance, AbstractThreadFactory _fct) {
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
//                    playingPresidentCard(_instance,_fct);
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
//                playingTarotCard(_instance,_fct);
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
//            playingTarotCard(_instance,_fct);
//        }
//    }
    private static void endGameBelote(Net _instance, NetCommon _common) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partieBelote().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (_common.getNicknames().contains(i)) {
                players_.add(_common.getNicknames().getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        for (byte p: Net.activePlayers(_instance,_common)) {
            ResultsBelote res_ = new ResultsBelote();
            CustList<Longs> scores_ = Net.getScores(_instance);
            CustList<Longs> list_ = new CustList<Longs>();
            for (Longs v: scores_) {
                list_.add(new Longs(v));
            }
            res_.setGame(Net.getGames(_instance).partieBelote());
            res_.getRes().setUser(p);
            res_.initialize(new StringList(players_), list_);
            String loc_ = Net.getLanguageByPlace(p, _instance,_common);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            Net.sendObject(Net.getSocketByPlace(p, _common), res_);
        }
    }
    private static void endGamePresident(Net _instance, NetCommon _common) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partiePresident().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (_common.getNicknames().contains(i)) {
                players_.add(_common.getNicknames().getVal(i));
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
        for (byte p: Net.activePlayers(_instance, _common)) {
            String loc_ = Net.getLanguageByPlace(p, _instance, _common);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.getRes().setUser(p);
            Net.sendObject(Net.getSocketByPlace(p, _common), res_);
        }
    }
    private static void endGameTarot(Net _instance, NetCommon _common) {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames(_instance).partieTarot().getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlayers_; i++) {
            if (_common.getNicknames().contains(i)) {
                players_.add(_common.getNicknames().getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        for (byte p: Net.activePlayers(_instance, _common)) {
            ResultsTarot res_ = new ResultsTarot();
            res_.setGame(Net.getGames(_instance).partieTarot());
            CustList<Longs> scores_ = Net.getScores(_instance);
            CustList<Longs> list_ = new CustList<Longs>();
            for (Longs v: scores_) {
                list_.add(new Longs(v));
            }
            String loc_ = Net.getLanguageByPlace(p, _instance, _common);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.getRes().setUser(p);
            res_.initialize(new StringList(players_), list_);
            Net.sendObject(Net.getSocketByPlace(p, _common), res_);
        }
    }
    private static void playingBeloteCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
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
            Net.sendObject(Net.getSocketByPlace(place_, _common),decla_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        CardBelote card_ = game_.getCarteJouee();
        boolean declareBeloteRebelote_ = game_.getAnnoncesBeloteRebelote(place_).contient(card_);
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
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common),cardDto_);
        }
    }
    private static void playingPresidentCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        byte place_ = game_.getNextPlayer();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingPresident allow_ = new AllowPlayingPresident();
            allow_.setEnabledPass(!game_.getProgressingTrick().estVide());
            allow_.setStatus(game_.getStatus(place_));
            allow_.setReversed(game_.isReversed());
            Net.sendObject(Net.getSocketByPlace(place_, _common),allow_);
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
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common),cardDto_);
        }
    }
    private static void playingTarotCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingTarot decla_ = new AllowPlayingTarot();
            decla_.setTakerIndex(game_.getPreneur());
            boolean firstRound_ = game_.premierTourNoMisere();
            decla_.setFirstRoundPlaying(game_.premierTour());
            if (firstRound_) {
                IdList<Handfuls> handfuls_ = new IdList<Handfuls>(game_.getRegles().getCurrentAllowedHandfuls());
                decla_.setAllowedHandfuls(new IdList<Handfuls>(handfuls_));
                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>(game_.getRegles().getAllowedHandfuls()));
                decla_.setAllowedMiseres(new IdList<Miseres>(game_.getRegles().getMiseres()));
            } else {
                decla_.setAllowedHandfuls(new IdList<Handfuls>());
                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>());
                decla_.setAllowedMiseres(new IdList<Miseres>());
            }
            Net.sendObject(Net.getSocketByPlace(place_, _common), decla_);
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
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common), cardDto_);
        }
    }
}
