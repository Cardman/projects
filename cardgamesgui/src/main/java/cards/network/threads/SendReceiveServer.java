package cards.network.threads;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

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
import cards.network.belote.displaying.CompletedHand;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.belote.displaying.players.RefreshingDoneBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.Bye;
import cards.network.common.Dealt;
import cards.network.common.Ok;
import cards.network.common.PlayGame;
import cards.network.common.Quit;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.NewPlayer;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.DoneBidding;
import cards.network.common.displaying.DonePause;
import cards.network.common.displaying.DonePlaying;
import cards.network.common.displaying.Pause;
import cards.network.common.select.SelectTeams;
import cards.network.common.select.SelectTricksHands;
import cards.network.common.select.TeamsPlayers;
import cards.network.president.actions.DiscardedCards;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.displaying.RefreshedHandPresident;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.displaying.players.RefreshingDonePresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.BiddingSlamAfter;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.actions.CalledCards;
import cards.network.tarot.actions.DiscardedCard;
import cards.network.tarot.actions.DiscardedTrumps;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.actions.TakeCard;
import cards.network.tarot.actions.ValidateDog;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.displaying.players.CalledCardKnown;
import cards.network.tarot.displaying.players.DoneDisplaySlam;
import cards.network.tarot.displaying.players.RefreshHand;
import cards.network.tarot.displaying.players.RefreshingDone;
import cards.network.tarot.displaying.players.SeenDiscardedTrumps;
import cards.network.tarot.displaying.players.ShowDog;
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
import cards.tarot.comparators.AllowedHandfulComparator;
import cards.tarot.enumerations.*;
import code.gui.ThreadUtil;
import code.network.AddingPlayer;
import code.network.BasicServer;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServer extends BasicServer {

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final String EMPTY_STRING = "";

    /**This class thread is independant from EDT*/
    public SendReceiveServer(Socket _socket, NetGroupFrame _net) {
        super(_socket, _net);
    }

    @Override
    public void loopServer(String _input, Object _object) {
        LOCK.lock();
        loop(_input, _object);
        LOCK.unlock();
    }

    private static void loop(String _input, Object _readObject) {
        if (_readObject instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)_readObject;
            if (!newPlayer_.isAcceptable()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setBusy(true);
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_);
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
            if (Net.getNbPlayers() == Net.getNicknames().size()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            if (Net.isProgressingGame()) {
                Bye forcedBye_ = new Bye();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                Net.removePlayer(newPlayer_.getIndex(), forcedBye_);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                return;
            }
            Net.getPlayersLocales().put(newPlayer_.getIndex(), newPlayer_.getLanguage());
            Net.getNicknames().put(newPlayer_.getIndex(),newPlayer_.getPseudo());
            PlayersNamePresent pl_ = new PlayersNamePresent();
            if (Net.getGames().getRulesBelote() != null) {
                pl_.setRulesBelote(Net.getGames().getRulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            } else if (Net.getGames().getRulesPresident() != null) {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(Net.getGames().getRulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            } else if (Net.getGames().getRulesTarot() != null) {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(Net.getGames().getRulesTarot());
            } else {
                pl_.setRulesBelote(new RulesBelote());
                pl_.setRulesPresident(new RulesPresident());
                pl_.setRulesTarot(new RulesTarot());
            }
            pl_.setNbPlayers(Net.getNbPlayers());
            pl_.setPseudos(new NumberMap<Integer,String>(Net.getNicknames()));
            pl_.setPlacesPlayers(Net.getPlacesPlayers());
            pl_.setReadyPlayers(new NumberMap<Integer,Boolean>(Net.getReadyPlayers()));
            for (int p:Net.getSockets().getKeys()) {
                pl_.setFirst(p == newPlayer_.getIndex());
                Net.sendObject(Net.getSockets().getVal(p),pl_);
            }
            return;
        }
        if (_readObject instanceof ChoosenPlace) {
            int noClient_ = ((ChoosenPlace)_readObject).getIndex();
            Net.getPlacesPlayers().put(noClient_, (byte)((ChoosenPlace)_readObject).getPlace());
            ((ChoosenPlace) _readObject).setPlacesPlayers(Net.getPlacesPlayers());
            for(Socket so_:Net.getSockets().values()) {
                Net.sendObject(so_,_readObject);
            }
            return;
        }
        if (_readObject instanceof RulesBelote) {
            for(Socket so_:Net.getSockets().values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof RulesPresident) {
            for(Socket so_:Net.getSockets().values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof RulesTarot) {
            for(Socket so_:Net.getSockets().values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof Ready) {
            int noClient_ = ((Ready)_readObject).getIndex();
            Net.getReadyPlayers().put(noClient_, (( Ready)_readObject).isReady());
            for(Socket so_:Net.getSockets().values()) {
                Net.sendText(so_,_input);
            }
            return;
        }
        if (_readObject instanceof Quit) {
            quitProcess(_readObject);
            return;
        }
        if (_readObject instanceof PlayGame) {
            Net.initAllPresent();
            Net.initAllReceived();
            if (Net.getGames().enCoursDePartieBelote()) {
                Net.setProgressingGame(true);
                DealBelote deal_ = Net.getGames().partieBelote().getDistribution();
                DealtHandBelote hand_ = new DealtHandBelote();
                hand_.setDeck(deal_.derniereMain());
                hand_.setDealer(Net.getGames().partieBelote().playerAfter(deal_.getDonneur()));
                hand_.setAllowedBids(Net.getGames().partieBelote().allowedBids());
                hand_.setRep(Net.getGames().partieBelote().getRegles().getRepartition());
                hand_.setPoints(Net.getGames().partieBelote().getContrat().getPoints());
                for (byte i:Net.activePlayers()) {
                    hand_.setCards(deal_.main(i));
                    Net.sendObject(Net.getSocketByPlace(i), hand_);
                }
            } else if (Net.getGames().enCoursDePartiePresident()) {
                Net.setProgressingGame(true);
                int nbSuits_ = Suit.couleursOrdinaires().size();
                RulesPresident rules_ = Net.getGames().partiePresident().getRegles();
                int nbStacks_ = rules_.getNbStacks();
                DealPresident deal_ = Net.getGames().partiePresident().getDistribution();
                DealtHandPresident hand_ = new DealtHandPresident();
                hand_.setDealer(Net.getGames().partiePresident().getDistribution().getDonneur());
                hand_.setMaxCards(Math.min(nbSuits_ * nbStacks_, rules_.getNbMaxCardsPerPlayer()));
                hand_.setStatus(Net.getGames().partiePresident().getLastStatus());
                for (byte i:Net.activePlayers()) {
                    hand_.setCards(deal_.main(i));
                    Net.sendObject(Net.getSocketByPlace(i), hand_);
                }
            } else if (Net.getGames().enCoursDePartieTarot()) {
                Net.setProgressingGame(true);
                DealTarot deal_ = Net.getGames().partieTarot().getDistribution();
                DealtHandTarot hand_ = new DealtHandTarot();
                hand_.setDog(deal_.derniereMain());
                hand_.setDealer(Net.getGames().partieTarot().playerAfter(deal_.getDonneur()));
                hand_.setAllowedBids(Net.getGames().partieTarot().allowedBids());
                hand_.setRep(Net.getGames().partieTarot().getRegles().getRepartition());
                for (byte i:Net.activePlayers()) {
                    hand_.setCards(deal_.main(i));
                    Net.sendObject(Net.getSocketByPlace(i), hand_);
                }
            }
            return;
        }
        if (Net.getGames().enCoursDePartieBelote()) {
            processGameBelote(_input, _readObject);
            return;
        }
        if (Net.getGames().enCoursDePartiePresident()) {
            processGamePresident(_readObject);
            return;
        }
        if (Net.getGames().enCoursDePartieTarot()) {
            processGameTarot(_input, _readObject);
            return;
        }
        NewPlayer newPlayer_ = (NewPlayer)_readObject;
        if (Net.getNbPlayers() == Net.getNicknames().size()) {
            Bye forcedBye_ = new Bye();
            forcedBye_.setForced(true);
            forcedBye_.setClosing(false);
            Net.removePlayer(newPlayer_.getIndex(), forcedBye_);
//            Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//            Net.getSockets().removeKey(newPlayer_.getIndex());
//            Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//            Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//            Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//            Net.sendObject(socket_,forcedBye_);
            return;
        }
        if (Net.isProgressingGame()) {
            Bye forcedBye_ = new Bye();
            forcedBye_.setForced(true);
            forcedBye_.setClosing(false);
            Net.removePlayer(newPlayer_.getIndex(), forcedBye_);
//            Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//            Net.getSockets().removeKey(newPlayer_.getIndex());
//            Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//            Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//            Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//            Net.sendObject(socket_,forcedBye_);
            return;
        }
        Net.getPlayersLocales().put(newPlayer_.getIndex(), newPlayer_.getLanguage());
        Net.getNicknames().put(newPlayer_.getIndex(),newPlayer_.getPseudo());
        PlayersNamePresent pl_ = new PlayersNamePresent();
        if (Net.getGames().getRulesBelote() != null) {
            pl_.setRulesBelote(Net.getGames().getRulesBelote());
        } else if (Net.getGames().getRulesPresident() != null) {
            pl_.setRulesPresident(Net.getGames().getRulesPresident());
        } else if (Net.getGames().getRulesTarot() != null) {
            pl_.setRulesTarot(Net.getGames().getRulesTarot());
        }
        pl_.setNbPlayers(Net.getNbPlayers());
        pl_.setPseudos(new NumberMap<Integer,String>(Net.getNicknames()));
        pl_.setPlacesPlayers(Net.getPlacesPlayers());
        pl_.setReadyPlayers(new NumberMap<Integer,Boolean>(Net.getReadyPlayers()));
        for (int p:Net.getSockets().getKeys()) {
            pl_.setFirst(p == newPlayer_.getIndex());
            Net.sendObject(Net.getSockets().getVal(p),pl_);
        }
    }

    private static void processGameTarot(String _input, Object _readObject) {
        if (_readObject instanceof Dealt) {
            processDealtTarot(_readObject);
            return;
        }
        if (_readObject instanceof DoneBidding) {
            processBetweenBidsTarot(_readObject);
            return;
        }
        if (_readObject instanceof BiddingTarot) {
            BiddingTarot bid_ = (BiddingTarot)_readObject;
            BidTarot b_ = bid_.getBid();
            GameTarot game_ = Net.getGames().partieTarot();
            if (!b_.estDemandable(game_.getContrat())) {
                ErrorBidding error_ = new ErrorBidding();
                error_.setBid(game_.getContrat());
                Net.sendObject(Net.getSocketByPlace(bid_.getPlace()), error_);
                return;
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendText(Net.getSocketByPlace(p), _input);
            }
            return;
        }
        if (_readObject instanceof CalledCards) {
            processCallingTarot(_readObject);
            return;
        }
        if (_readObject instanceof DiscardedCard) {
            GameTarot game_ = Net.getGames().partieTarot();
            DiscardedCard discarded_ = (DiscardedCard) _readObject;
            if (!discarded_.isInHand()) {
                game_.retirerUneCarteDuChien(discarded_.getCard());
                game_.addCard(discarded_.getPlace(),discarded_.getCard());
                Net.sendText(Net.getSocketByPlace(discarded_.getPlace()), _input);
                return;
            }
            ReasonDiscard reason_ = game_.autoriseEcartDe(discarded_.getCard());
            if (reason_ != ReasonDiscard.NOTHING) {
                ErrorDiscarding error_ = new ErrorDiscarding();
                error_.setErrorMessage(Games.autoriseMessEcartDe(game_,reason_,discarded_.getCard(), discarded_.getLocale()).toString());
                error_.setCard(discarded_.getCard());
                Net.sendObject(Net.getSocketByPlace(discarded_.getPlace()), error_);
                return;
            }
            game_.ajouterUneCarteDansPliEnCours(discarded_.getPlace(),discarded_.getCard());
            Net.sendText(Net.getSocketByPlace(discarded_.getPlace()), _input);
            return;
        }
        if (_readObject instanceof CalledCardKnown) {
            Net.setReceivedForPlayer(((CalledCardKnown)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameTarot game_ = Net.getGames().partieTarot();
                if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog show_ = new Dog();
                    show_.setDog(game_.getDistribution().derniereMain());
                    show_.setTaker(false);
                    show_.setHumanTaker(false);
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), show_);
                    }
                    return;
                }
                game_.gererChienInconnu();
                if (!game_.getContrat().isFaireTousPlis()) {
                    game_.slam();
                    if (game_.chelemAnnonce()) {
                        Net.initAllReceived();
                        BiddingSlamAfter bid_ = new BiddingSlamAfter();
                        bid_.setPlace(game_.getPreneur());
                        bid_.setLocale(Constants.getDefaultLanguage());
                        for (byte p: Net.activePlayers()) {
                            Net.sendObject(Net.getSocketByPlace(p), bid_);
                        }
                        return;
                    }
                }
                game_.setPliEnCours(true);
                playingTarotCard();
            }
            return;
        }
        if (_readObject instanceof SeenDiscardedTrumps) {
            processDiscardingTrumps(_readObject);
            return;
        }
        if (_readObject instanceof ShowDog) {
            processShowDogTarot(_readObject);
            return;
        }
        if (_readObject instanceof TakeCard) {
            GameTarot game_ = Net.getGames().partieTarot();
            game_.ajouterCartesUtilisateur();
            if (!game_.getRegles().getDiscardAfterCall()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setDiscarding(true);
                callableCardsDto_.setCallableCards(game_.callableCards());
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur()), callableCardsDto_);
            }
            return;
        }
        if (_readObject instanceof ValidateDog) {
            GameTarot game_ = Net.getGames().partieTarot();
            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                game_.addCurTrick();
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), discarded_);
                    }
                    return;
                }
            }
            byte donneur_=game_.getDistribution().getDonneur();
            if(!game_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                game_.setEntameur(game_.playerAfter(donneur_));
            }
            game_.setPliEnCours(true);
            playingTarotCard();
            return;
        }
        if (_readObject instanceof BiddingSlamAfter) {
            GameTarot game_ = Net.getGames().partieTarot();
            if(!game_.chelemAnnonce()) {
                game_.ajouterChelemUtilisateur();
            }
            if (!game_.getRegles().getDiscardAfterCall()) {
                game_.addCurTrick();
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setDeclaringSlam(true);
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), discarded_);
                    }
                    return;
                }
            }
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendText(Net.getSocketByPlace(p), _input);
            }
            return;
        }
        if (_readObject instanceof DoneDisplaySlam) {
            Net.setReceivedForPlayer(((DoneDisplaySlam)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameTarot game_ = Net.getGames().partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
            return;
        }
        if (_readObject instanceof DonePlaying) {
            processBetweenPlayedCardsTarot(_readObject);
            return;
        }
        if (_readObject instanceof DonePause) {
            Net.setReceivedForPlayer(((DonePause)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameTarot game_ = Net.getGames().partieTarot();
                game_.setPliEnCours(true);
                playingTarotCard();
            }
            return;
        }
        if (_readObject instanceof PlayingCardTarot) {
            processPlayingTarot(_readObject);
            return;
        }
        if (_readObject instanceof RefreshingDone) {
            PlayingCardTarot p_ = new PlayingCardTarot();
            GameTarot game_ = Net.getGames().partieTarot();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(((RefreshingDone)_readObject).getPlace());
            p_.setPlayedCard(((RefreshingDone)_readObject).getCard());
            p_.setChoosenHandful(((RefreshingDone)_readObject).getChoosenHandful());
            p_.setHandful(((RefreshingDone)_readObject).getHandful());
            p_.setMiseres(((RefreshingDone)_readObject).getMiseres());
            p_.setCalledCard(((RefreshingDone)_readObject).isCalledCard());
            p_.setExcludedTrumps(new HandTarot());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendObject(Net.getSocketByPlace(p), p_);
            }
            return;
        }
        if (_readObject instanceof Ok) {
            Net.setReceivedForPlayer(((Ok)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.setProgressingGame(false);
            }
            return;
        }
        processShowTarot(_readObject);
    }

    private static void processBetweenBidsTarot(Object _readObject) {
        Net.setReceivedForPlayer(((DoneBidding)_readObject).getPlace());
        if (Net.allReceived()) {
            Net.initAllReceived();

            GameTarot game_ = Net.getGames().partieTarot();
            if (!game_.keepBidding()) {
                processAfterBidTarot(game_);
                return;
            }
            byte place_ = game_.playerHavingToBid();
            if (Net.isHumanPlayer(place_)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(game_.allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_), allowedBids_);
                return;
            }
            //Les "robots" precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(1000);
            if (Net.getGames().partieTarot().playerHasAlreadyBidded(place_)) {
                return;
            }
            BiddingTarot bid_ = new BiddingTarot();
            bid_.setPlace(place_);
            bid_.setBid(Net.getGames().partieTarot().getLastBid());
            bid_.setLocale(Constants.getDefaultLanguage());
            for (byte p: Net.activePlayers()) {
                Net.sendObject(Net.getSocketByPlace(p), bid_);
            }
        }
        return;
    }

    private static void processBetweenPlayedCardsTarot(Object _readObject) {
        Net.setReceivedForPlayer(((DonePlaying)_readObject).getPlace());
        if (Net.allReceived()) {
            Net.initAllReceived();
            GameTarot game_ = Net.getGames().partieTarot();
            if (!game_.keepPlayingCurrentTrick()) {
                game_.ajouterPetitAuBoutPliEnCours();

                if (!game_.keepPlayingCurrentGame()) {
                    ThreadUtil.sleep(1000);
                    endGameTarot();
                    return;
                }
                ThreadUtil.sleep(3000);
                Net.initAllReceived();
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), new Pause());
                }
                return;
            }

            playingTarotCard();
        }
        return;
    }

    private static void processDealtTarot(Object _readObject) {
        Net.setReceivedForPlayer(((Dealt)_readObject).getPlace());
        if (Net.allReceived()) {
            Net.initAllReceived();
            if (!Net.getGames().partieTarot().avecContrat()) {
                GameTarot game_ = Net.getGames().partieTarot();
                game_.setEntameur(game_.playerAfter(game_.getDistribution().getDonneur()));
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
            byte place_ = Net.getGames().partieTarot().playerHavingToBid();
            if (Net.isHumanPlayer(place_)) {
                AllowBiddingTarot allowedBids_ = new AllowBiddingTarot();
                allowedBids_.setBids(Net.getGames().partieTarot().allowedBids());
                Net.sendObject(Net.getSocketByPlace(place_), allowedBids_);
                return;
            }
            //Les "robots" precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(1000);
            if (Net.getGames().partieTarot().playerHasAlreadyBidded(place_)) {
                return;
            }
            BiddingTarot bid_ = new BiddingTarot();
            bid_.setPlace(place_);
            bid_.setBid(Net.getGames().partieTarot().getLastBid());
            bid_.setLocale(Constants.getDefaultLanguage());
            for (byte p: Net.activePlayers()) {
                Net.sendObject(Net.getSocketByPlace(p), bid_);
            }
        }
    }

    private static void processShowDogTarot(Object _readObject) {
        Net.setReceivedForPlayer(((ShowDog)_readObject).getPlace());
        if (Net.allReceived()) {
            Net.initAllReceived();
            GameTarot game_ = Net.getGames().partieTarot();
            if (game_.getRegles().getDiscardAfterCall()) {
                game_.ecarter(true);
            }
            ThreadUtil.sleep(5000);
            if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                DiscardedTrumps discarded_ = new DiscardedTrumps();
                discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                Net.initAllReceived();
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), discarded_);
                }
                return;
            }
            if (game_.chelemAnnonce()) {
                BiddingSlamAfter bid_ = new BiddingSlamAfter();
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), bid_);
                }
                return;
            }
            byte dealer_=game_.getDistribution().getDonneur();
            if(!game_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                game_.setEntameur(game_.playerAfter(dealer_));
            }
            game_.setPliEnCours(true);
            playingTarotCard();
            return;
        }
        return;
    }

    private static void processPlayingTarot(Object _readObject) {
        PlayingCardTarot info_ = (PlayingCardTarot) _readObject;
        CardTarot card_ = info_.getPlayedCard();
        GameTarot game_ = Net.getGames().partieTarot();
        if (info_.getChoosenHandful() != Handfuls.NO) {
            String messErr_ = Games.isValidHandfulMessage(game_, info_.getChoosenHandful(),
                    info_.getHandful(), info_.getExcludedTrumps(), info_.getLocale());
            if (!game_.isValidHandful(info_.getChoosenHandful(),
                    info_.getHandful(), info_.getExcludedTrumps())) {
                ErrorHandful error_ = new ErrorHandful();
                error_.setHandful(info_.getChoosenHandful());
                error_.setError(messErr_);
                Net.sendObject(Net.getSocketByPlace(info_.getPlace()), error_);
                return;
            }
        }
        if (!game_.autorise(card_)) {
            ErrorPlaying error_ = new ErrorPlaying();
            error_.setCard(card_);
            error_.setReason(Games.autoriseTarot(game_, info_.getLocale()));
            Net.sendObject(Net.getSocketByPlace(info_.getPlace()), error_);
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
        game_.ajouterAnnoncesMiseres(info_.getPlace(), declaredMiseres_);
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
        Net.sendObject(Net.getSocketByPlace(info_.getPlace()), ref_);
    }

    private static void processShowTarot(Object _readObject) {
        if (_readObject instanceof SelectTricksHands) {
            if (!Net.isSameTeam()) {
                return;
            }
            byte place_ = ((SelectTricksHands)_readObject).getPlace();
            TricksHandsTarot tricksHands_ = new TricksHandsTarot();
            GameTarot game_ = Net.getGames().partieTarot();
            tricksHands_.setDistribution(game_.getDistribution(), false);
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setBid(game_.getContrat());
            tricksHands_.setTricks(game_.unionPlis(true), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_), tricksHands_);
            return;
        }
        if (_readObject instanceof SelectTeams) {
            if (!Net.isSameTeam()) {
                return;
            }
            byte place_ = ((SelectTeams)_readObject).getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameTarot game_ = Net.getGames().partieTarot();
            teams_.setTeams(game_.getTeamsRelation().playersBelongingToSameTeam());
            Net.sendObject(Net.getSocketByPlace(place_), teams_);
        }
    }

    private static void processDiscardingTrumps(Object _readObject) {
        Net.setReceivedForPlayer(((SeenDiscardedTrumps)_readObject).getPlace());
        if (Net.allReceived()) {
            GameTarot game_ = Net.getGames().partieTarot();
            if (Net.isHumanPlayer(game_.getPreneur())) {
                if (((SeenDiscardedTrumps)_readObject).isDeclaringSlam()) {
                    Net.initAllReceived();
                    BiddingSlamAfter bid_ = new BiddingSlamAfter();
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(game_.getPreneur());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                    return;
                }
                byte donneur_=game_.getDistribution().getDonneur();
                if(!game_.chelemAnnonce()) {
                    /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                    game_.setEntameur(game_.playerAfter(donneur_));
                }
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
            if (game_.chelemAnnonce()) {
                BiddingSlamAfter bid_ = new BiddingSlamAfter();
                bid_.setPlace(game_.getPreneur());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), bid_);
                }
                return;
            }
            byte donneur_=game_.getDistribution().getDonneur();
            if(!game_.chelemAnnonce()) {
                /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                game_.setEntameur(game_.playerAfter(donneur_));
            }
            game_.setPliEnCours(true);
            playingTarotCard();
            return;
        }
        return;
    }

    private static void processCallingTarot(Object _readObject) {
        //called cards by a human player
        GameTarot game_ = Net.getGames().partieTarot();
        CalledCards calledCards_ = (CalledCards) _readObject;
        game_.initConfianceAppeleUtilisateur(calledCards_.getCalledCards());
        if (!game_.getRegles().getDiscardAfterCall()) {
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur()), new DisplaySlamButton());
            } else {
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
        }else if(game_.getContrat().getJeuChien() == PlayingDog.WITH) {
            //before taking cards of the dog
            Dog dog_ = new Dog();
            dog_.setHumanTaker(true);
            dog_.setDog(game_.getDistribution().derniereMain());
            for (byte p: Net.activePlayers()) {
                dog_.setTaker(p == game_.getPreneur());
                Net.sendObject(Net.getSocketByPlace(p), dog_);
            }
        } else {
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                Net.sendObject(Net.getSocketByPlace(game_.getPreneur()), new DisplaySlamButton());
            } else {
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
        }
        return;
    }

    private static void processAfterBidTarot(GameTarot _game) {
        //Call, dog or play
        if (!_game.getContrat().isJouerDonne()) {
            if (_game.pasJeuApresPasse()) {
                endGameTarot();
            } else {
                _game.setEntameur(_game.playerAfter(_game.getDistribution().getDonneur()));
                _game.setPliEnCours(true);
                playingTarotCard();
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
        if (Net.isHumanPlayer(_game.getPreneur())) {
            HandTarot callableCards_ = _game.callableCards();
            if (callableCards_.estVide()) {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Dog dog_ = new Dog();
                    dog_.setHumanTaker(true);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setDog(_game.getDistribution().derniereMain());
                    for (byte p: Net.activePlayers()) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                if (!_game.getContrat().isFaireTousPlis()) {
                    Net.sendObject(Net.getSocketByPlace(_game.getPreneur()), new DisplaySlamButton());
                } else {
                    playingTarotCard();
                    return;
                }
            } else if (_game.getRegles().getDiscardAfterCall()) {
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers()) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p), callableCardsDto_);
                }
            } else {
                if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                    Net.initAllReceived();
                    Dog dog_ = new Dog();
                    dog_.setDog(_game.getDistribution().derniereMain());
                    dog_.setTaker(false);
                    dog_.setTakerIndex(_game.getPreneur());
                    dog_.setHumanTaker(true);
                    dog_.setCallAfter(true);
                    for (byte p: Net.activePlayers()) {
                        dog_.setTaker(p == _game.getPreneur());
                        Net.sendObject(Net.getSocketByPlace(p), dog_);
                    }
                    return;
                }
                _game.gererChienInconnu();
                CallableCards callableCardsDto_ = new CallableCards();
                callableCardsDto_.setTakerIndex(_game.getPreneur());
                for (byte p: Net.activePlayers()) {
                    if (p == _game.getPreneur()) {
                        callableCardsDto_.setCallableCards(_game.callableCards());
                    } else {
                        callableCardsDto_.setCallableCards(new HandTarot());
                    }
                    Net.sendObject(Net.getSocketByPlace(p), callableCardsDto_);
                }
                return;
            }
            return;
        }
        HandTarot callableCards_ = _game.callableCards();
        if (callableCards_.estVide()) {
            if (_game.getContrat().getJeuChien() == PlayingDog.WITH) {
                Net.initAllReceived();
                Dog show_ = new Dog();
                show_.setDog(_game.getDistribution().derniereMain());
                show_.setTaker(false);
                show_.setTakerIndex(_game.getPreneur());
                show_.setHumanTaker(false);
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), show_);
                }
                return;
            }
            _game.gererChienInconnu();
            if (!_game.getContrat().isFaireTousPlis()) {
                _game.slam();
                if (_game.chelemAnnonce()) {
                    Net.initAllReceived();
                    BiddingSlamAfter bid_ = new BiddingSlamAfter();
                    bid_.setLocale(Constants.getDefaultLanguage());
                    bid_.setPlace(_game.getPreneur());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                    return;
                }
            }
            playingTarotCard();
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
        Net.initAllReceived();
        for (byte p: Net.activePlayers()) {
            Net.sendObject(Net.getSocketByPlace(p), calledCards_);
        }
        return;
    }

    private static void processGameBelote(String _input, Object _readObject) {
        if (_readObject instanceof Dealt) {
            Net.setReceivedForPlayer(((Dealt)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                byte place_ = Net.getGames().partieBelote().playerHavingToBid();
                if (Net.isHumanPlayer(place_)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(Net.getGames().partieBelote().allowedBids());
                    allowedBids_.setPoints(Net.getGames().partieBelote().getContrat().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_), allowedBids_);
                    return;
                }
                //Les "robots" precedant l'utilisateur annoncent leur contrat
                ThreadUtil.sleep(1000);
                if (Net.getGames().partieBelote().playerHasAlreadyBidded(place_)) {
                    return;
                }
                BiddingBelote bid_ = new BiddingBelote();
                bid_.setPlace(place_);
                bid_.setBidBelote(Net.getGames().partieBelote().getLastBid());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), bid_);
                }
            }
            return;
        }
        if (_readObject instanceof DoneBidding) {
            Net.setReceivedForPlayer(((DoneBidding)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameBelote game_ = Net.getGames().partieBelote();
                if (!game_.keepBidding()) {
                    if (!game_.getContrat().jouerDonne()) {
                        endGameBelote();
                    } else {
                        if (game_.completedDeal()) {
                            return;
                        }
                        for (byte p: Net.activePlayers()) {
                            RefreshHandBelote hand_ = new RefreshHandBelote();
                            hand_.setRefreshedHand(game_.getDistribution().main(p));
                            hand_.setLocale(Constants.getDefaultLanguage());
                            Net.sendObject(Net.getSocketByPlace(p), hand_);
                        }
                    }
                    return;
                }
                byte place_ = game_.playerHavingToBid();
                if (Net.isHumanPlayer(place_)) {
                    AllowBiddingBelote allowedBids_ = new AllowBiddingBelote();
                    allowedBids_.setBids(game_.allowedBids());
                    allowedBids_.setPoints(game_.getContrat().getPoints());
                    Net.sendObject(Net.getSocketByPlace(place_), allowedBids_);
                    return;
                }
                //Les "robots" precedant l'utilisateur annoncent leur contrat
                ThreadUtil.sleep(1000);
                if (Net.getGames().partieBelote().playerHasAlreadyBidded(place_)) {
                    return;
                }
                BiddingBelote bid_ = new BiddingBelote();
                bid_.setPlace(place_);
                bid_.setBidBelote(Net.getGames().partieBelote().getLastBid());
                bid_.setLocale(Constants.getDefaultLanguage());
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), bid_);
                }
            }
            return;
        }
        if (_readObject instanceof BiddingBelote) {
            BiddingBelote bid_ = (BiddingBelote)_readObject;
            BidBeloteSuit b_ = bid_.getBidBelote();
            GameBelote game_ = Net.getGames().partieBelote();
            if (!game_.getRegles().dealAll()) {
                if (!b_.estDemandable(game_.getContrat())) {
                    ErrorBiddingBelote error_ = new ErrorBiddingBelote();
                    error_.setBid(game_.getContrat());
                    Net.sendObject(Net.getSocketByPlace(bid_.getPlace()), error_);
                    return;
                }
            }
            game_.ajouterContrat(b_, bid_.getPlace());
            if (!game_.getRegles().dealAll()) {
                if (game_.tailleContrats() == game_.getNombreDeJoueurs()) {
                    game_.finEncherePremierTour();
                }
            }
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendText(Net.getSocketByPlace(p), _input);
            }
            return;
        }
        if (_readObject instanceof CompletedHand) {
            Net.setReceivedForPlayer(((CompletedHand)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameBelote game_ = Net.getGames().partieBelote();
                byte donneur_=game_.getDistribution().getDonneur();
                game_.setEntameur(game_.playerAfter(donneur_));
                if (game_.getRegles().dealAll()) {
                    int pts_ = game_.getContrat().getPoints();
                    if (pts_ >= HandBelote.pointsTotauxDixDeDer(game_.getContrat())) {
                        game_.setEntameur(game_.getPreneur());
                    }
                }
                game_.setPliEnCours();
                playingBeloteCard();
            }
            return;
        }
        if (_readObject instanceof Ok) {
            Net.setReceivedForPlayer(((Ok)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.setProgressingGame(false);
            }
            return;
        }
        if (_readObject instanceof DonePlaying) {
            Net.setReceivedForPlayer(((DonePlaying)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameBelote game_ = Net.getGames().partieBelote();
                if (!game_.keepPlayingCurrentTrick()) {
                    game_.ajouterDixDeDerPliEnCours();

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(1000);
                        endGameBelote();
                        return;
                    }
                    ThreadUtil.sleep(3000);
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), new Pause());
                    }
                    return;
                }

                playingBeloteCard();
            }
            return;
        }
        if (_readObject instanceof DonePause) {
            Net.setReceivedForPlayer(((DonePause)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GameBelote game_ = Net.getGames().partieBelote();
                game_.setPliEnCours();
                playingBeloteCard();
            }
            return;
        }
        if (_readObject instanceof PlayingCardBelote) {
            PlayingCardBelote info_ = (PlayingCardBelote) _readObject;
            CardBelote card_ = info_.getPlayedCard();
            GameBelote game_ = Net.getGames().partieBelote();
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
                    Net.sendObject(Net.getSocketByPlace(info_.getPlace()), error_);
                    return;
                }
            }
            if (!autorise_) {
                ErrorPlayingBelote error_ = new ErrorPlayingBelote();
                error_.setCard(card_);
                error_.setCards(new HandBelote());
                error_.setReason(Games.autoriseBelote(game_,info_.getLocale()));
                Net.sendObject(Net.getSocketByPlace(info_.getPlace()), error_);
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
            Net.sendObject(Net.getSocketByPlace(info_.getPlace()), ref_);
            return;
        }
        if (_readObject instanceof RefreshingDoneBelote) {
            PlayingCardBelote p_ = new PlayingCardBelote();
            GameBelote game_ = Net.getGames().partieBelote();
            p_.setTakerIndex(game_.getPreneur());
            p_.setPlace(((RefreshingDoneBelote)_readObject).getPlace());
            p_.setPlayedCard(((RefreshingDoneBelote)_readObject).getCard());
            p_.setDeclaringBeloteRebelote(((RefreshingDoneBelote)_readObject).isDeclaringBeloteRebelote());
            p_.setDeclaring(((RefreshingDoneBelote)_readObject).isDeclaring());
            p_.setDeclare(((RefreshingDoneBelote)_readObject).getDeclare());
            p_.setLocale(Constants.getDefaultLanguage());
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendObject(Net.getSocketByPlace(p), p_);
            }
            return;
        }
        if (_readObject instanceof SelectTricksHands) {
            if (!Net.isSameTeam()) {
                return;
            }
            byte place_ = ((SelectTricksHands)_readObject).getPlace();
            TricksHandsBelote tricksHands_ = new TricksHandsBelote();
            GameBelote game_ = Net.getGames().partieBelote();
            tricksHands_.setRules(game_.getRegles());
            tricksHands_.setDistribution(game_.getDistribution(), false);
            tricksHands_.setPreneur(game_.getPreneur());
            tricksHands_.setBid(game_.getContrat());
            tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
            Net.sendObject(Net.getSocketByPlace(place_), tricksHands_);
            return;
        }
        if (_readObject instanceof SelectTeams) {
            if (!Net.isSameTeam()) {
                return;
            }
            byte place_ = ((SelectTeams)_readObject).getPlace();
            TeamsPlayers teams_ = new TeamsPlayers();
            GameBelote game_ = Net.getGames().partieBelote();
            teams_.setTeams(game_.playersBelongingToSameTeam());
            Net.sendObject(Net.getSocketByPlace(place_), teams_);
            return;
        }
    }

    private static void processGamePresident(Object _readObject) {
        if (_readObject instanceof Dealt) {
            Net.setReceivedForPlayer(((Dealt)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GamePresident g_ = Net.getGames().partiePresident();
                g_.initCartesEchanges();
                g_.donnerMeilleuresCartes();
                if (g_.availableSwitchingCards()) {
                    Numbers<Byte> pl_ = Net.activePlayers();
                    Numbers<Byte> humWin_ = g_.getWinners(pl_);
                    Numbers<Byte> humLos_ = g_.getLoosers(pl_);
                    if (!humWin_.isEmpty()) {
                        //Display discarding
                        AllowDiscarding allow_ = new AllowDiscarding();
                        for (byte p: humWin_) {
                            byte l_ = g_.getMatchingLoser(p);
                            allow_.setReceivedCards(g_.getSwitchedCards().getVal(l_));
                            Net.sendObject(Net.getSocketByPlace(p), allow_);
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
                            dis_.setReceived(g_.getSwitchedCards().getVal(w_));
                            dis_.setGiven(g_.getSwitchedCards().getVal(p));
                            dis_.setNewHand(g_.getDistribution().main(w_));
                            Net.sendObject(Net.getSocketByPlace(p), dis_);
                        }
                        return;
                    }
                    g_.giveWorstCards();
                }
                //Go playing
                playingPresidentCard();
            }
            return;
        }
        if (_readObject instanceof DiscardedCards) {
            Numbers<Byte> pl_ = Net.activePlayers();
            DiscardedCards dis_ = (DiscardedCards) _readObject;
            HandPresident cards_ = dis_.getDiscarded();
            byte player_ = dis_.getPlace();
            GamePresident g_ = Net.getGames().partiePresident();
            if (!g_.giveWorstCards(pl_, player_, cards_)) {
                return;
            }
            Numbers<Byte> humWin_ = g_.getWinners(pl_);
            Numbers<Byte> humLos_ = g_.getLoosers(pl_);
            Numbers<Byte> humLosReceiving_ = new Numbers<Byte>();
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
                    disAfter_.setReceived(g_.getSwitchedCards().getVal(w_));
                    disAfter_.setGiven(g_.getSwitchedCards().getVal(p));
                    disAfter_.setNewHand(g_.getDistribution().main(w_));
                    Net.sendObject(Net.getSocketByPlace(p), disAfter_);
                }
                return;
            }
            playingPresidentCard();
            return;
        }
        if (_readObject instanceof RefreshedHandPresident) {
            Net.setReceivedForPlayer(((Dealt)_readObject).getPlace());
            Numbers<Byte> pl_ = Net.activePlayers();
            GamePresident g_ = Net.getGames().partiePresident();
            if (Net.allReceivedAmong(g_.getLoosers(pl_))) {
                Net.initAllReceived();
                //Go playing
                playingPresidentCard();
            }
        }
        if (_readObject instanceof PlayingCardPresident) {
            GamePresident game_ = Net.getGames().partiePresident();
            PlayingCardPresident pl_ = (PlayingCardPresident) _readObject;
            byte player_ = pl_.getPlace();
            if (pl_.isPass()) {
                if (!game_.canPass(player_)) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setPassIssue(true);
                    e_.setReason(Games.canPassMess(game_, pl_.getLocale()));
                    e_.setCard(CardPresident.WHITE);
                    Net.sendObject(Net.getSocketByPlace(player_), e_);
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
                    Net.sendObject(Net.getSocketByPlace(player_),cardDto_);
                }
            } else {
                if (!game_.allowPlaying(player_, pl_.getPlayedCard(), pl_.getIndex())) {
                    ErrorPlayingPresident e_ = new ErrorPlayingPresident();
                    e_.setCard(pl_.getPlayedCard());
                    e_.setReason(Games.autorisePresident(game_,player_, pl_.getPlayedCard(), pl_.getIndex(), pl_.getLocale()).toString());
                    Net.sendObject(Net.getSocketByPlace(player_), e_);
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
                    Net.sendObject(Net.getSocketByPlace(player_),cardDto_);
                }
            }
            return;
        }
        if (_readObject instanceof DonePlaying) {
            Net.setReceivedForPlayer(((DonePlaying)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                GamePresident game_ = Net.getGames().partiePresident();
                if (game_.getProgressingTrick().estVide()) {

                    if (!game_.keepPlayingCurrentGame()) {
                        ThreadUtil.sleep(1000);
                        endGamePresident();
                        return;
                    }
                    ThreadUtil.sleep(3000);
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), new Pause());
                    }
                    return;
                }

                playingPresidentCard();
            }
            return;
        }
        if (_readObject instanceof DonePause) {
            Net.setReceivedForPlayer(((DonePause)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.initAllReceived();
                playingPresidentCard();
            }
            return;
        }
        if (_readObject instanceof RefreshingDonePresident) {
            PlayingCardPresident cardDto_ = new PlayingCardPresident();
            GamePresident game_ = Net.getGames().partiePresident();
            cardDto_.setPlayedHand(game_.getPlayedCards());
            cardDto_.setPlace(((RefreshingDonePresident)_readObject).getPlace());
            cardDto_.setNextPlayer(game_.getNextPlayer());
            cardDto_.setStatus(game_.getLastStatus());
            cardDto_.setLocale(Constants.getDefaultLanguage());
            cardDto_.setPlayedCard(CardPresident.WHITE);
            Net.initAllReceived();
            for (byte p: Net.activePlayers()) {
                Net.sendObject(Net.getSocketByPlace(p),cardDto_);
            }
            return;
        }
        if (_readObject instanceof Ok) {
            Net.setReceivedForPlayer(((Ok)_readObject).getPlace());
            if (Net.allReceived()) {
                Net.setProgressingGame(false);
            }
            return;
        }
        if (_readObject instanceof SelectTricksHands) {
            if (!Net.isSameTeam()) {
                return;
            }
            byte place_ = ((SelectTricksHands)_readObject).getPlace();
            TricksHandsPresident tricksHands_ = new TricksHandsPresident();
            GamePresident game_ = Net.getGames().partiePresident();
            tricksHands_.setReversed(game_.isReversed());
            tricksHands_.setDistribution(game_.getDistribution(), true);
            tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
            tricksHands_.setRanks(game_.getRanks());
            tricksHands_.setSwitchedCards(game_.getSwitchedCards());
            tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
            tricksHands_.setDistribution(game_.getDistribution(), false);
            Net.sendObject(Net.getSocketByPlace(place_), tricksHands_);
            return;
        }
    }

    private static void quitProcess(Object _readObject) {
        Quit bye_ = (Quit)_readObject;
        if (bye_.isServer()) {
            if (!Net.delegateServer(bye_)) {
                return;
            }
        }
        Net.quit(bye_.getPlace());
        Bye forcedBye_ = new Bye();
        forcedBye_.setForced(false);
        forcedBye_.setServer(false);
        forcedBye_.setClosing(bye_.isClosing());
        for (int i: Net.getPlacesPlayersByValue(bye_.getPlace())) {
            Net.removePlayer(i, forcedBye_);
            break;
        }
        if (Net.getGames().enCoursDePartieBelote()) {
            GameBelote game_ = Net.getGames().partieBelote();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            if (game_.keepBidding()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(1000);
                    if (game_.playerHasAlreadyBidded(place_)) {
                        return;
                    }
                    BiddingBelote bid_ = new BiddingBelote();
                    bid_.setPlace(place_);
                    bid_.setBidBelote(game_.getLastBid());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                }
            } else {
                byte place_ = game_.playerHavingToPlay();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(800);
                    if (game_.currentPlayerHasPlayed(place_)) {
                        return;
                    }
                    CardBelote card_ = game_.getCarteJouee();
                    boolean declareBeloteRebelote_ = false;
                    if(game_.annoncerBeloteRebelote(place_,card_)) {
                        game_.setAnnoncesBeloteRebelote(place_,card_);
                        declareBeloteRebelote_ = true;
                    }
                    PlayingCardBelote cardDto_ = new PlayingCardBelote();
                    cardDto_.setPlace(place_);
                    cardDto_.setPlayedCard(card_);
                    cardDto_.setDeclaringBeloteRebelote(declareBeloteRebelote_);
                    cardDto_.setDeclare(game_.getAnnonce(place_));
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), cardDto_);
                    }
                }
            }
            return;
        }
        if (Net.getGames().enCoursDePartiePresident()) {
            GamePresident game_ = Net.getGames().partiePresident();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            byte player_ = bye_.getPlace();
            if (game_.availableSwitchingCards() && !game_.readyToPlayMulti()) {
                Numbers<Byte> pl_ = Net.activePlayers();
                Numbers<Byte> humWin_ = game_.getWinners(new Numbers<Byte>(player_));
                if (!humWin_.isEmpty()) {
                    HandPresident h_ = game_.strategieEchange(player_);
                    if (!game_.giveWorstCards(pl_, player_, h_)) {
                        return;
                    }
                    humWin_ = game_.getWinners(pl_);
                    Numbers<Byte> humLos_ = game_.getLoosers(pl_);
                    Numbers<Byte> humLosReceiving_ = new Numbers<Byte>();
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
                            disAfter_.setReceived(game_.getSwitchedCards().getVal(w_));
                            disAfter_.setGiven(game_.getSwitchedCards().getVal(p));
                            disAfter_.setNewHand(game_.getDistribution().main(w_));
                            Net.sendObject(Net.getSocketByPlace(p), disAfter_);
                        }
                        return;
                    }
                    playingPresidentCard();
                }
                return;
            }
            if (game_.getNextPlayer() == player_) {
                ThreadUtil.sleep(800);
                if (game_.currentPlayerHasPlayed(player_,Constants.getDefaultLanguage())) {
                    return;
                }
                PlayingCardPresident cardDto_ = new PlayingCardPresident();
                cardDto_.setPlayedHand(game_.getPlayedCards());
                cardDto_.setPlace(player_);
                cardDto_.setNextPlayer(game_.getNextPlayer());
                cardDto_.setStatus(game_.getLastStatus());
                cardDto_.setPlayedCard(CardPresident.WHITE);
                cardDto_.setLocale(Constants.getDefaultLanguage());
                Net.initAllReceived();
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p),cardDto_);
                }
            }
            return;
        }
        if (Net.getGames().enCoursDePartieTarot()) {
            GameTarot game_ = Net.getGames().partieTarot();
            if (!game_.keepPlayingCurrentGame()) {
                return;
            }
            if (game_.keepBidding()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(1000);
                    if (game_.playerHasAlreadyBidded(place_)) {
                        return;
                    }
                    BiddingTarot bid_ = new BiddingTarot();
                    bid_.setPlace(place_);
                    bid_.setBid(game_.getLastBid());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                }
                return;
            }
            if (!game_.unionPlis(true).isEmpty() && game_.getPliEnCours().getVuParToutJoueur() && game_.keepPlayingCurrentTrick()) {
                byte place_ = game_.playerHavingToBid();
                if (place_ == bye_.getPlace()) {
                    ThreadUtil.sleep(800);
                    if (game_.currentPlayerHasPlayed(place_)) {
                        return;
                    }
                    CardTarot card_ = game_.getCarteJoueee();
                    PlayingCardTarot cardDto_ = new PlayingCardTarot();
                    cardDto_.setPlace(place_);
                    cardDto_.setPlayedCard(card_);
                    EnumList<Handfuls> annoncesPoignees_ = game_.strategieAnnoncesPoignees(place_);
                    game_.setAnnoncesPoignees(place_, annoncesPoignees_);
                    EnumList<Miseres> annoncesMiseres_ = game_.strategieAnnoncesMiseres(place_);
                    game_.setAnnoncesMiseres(place_, annoncesMiseres_);
                    HandTarot poignee_=game_.strategiePoignee(place_);
                    game_.ajouterPoignee(poignee_,place_);
                    if (!annoncesPoignees_.isEmpty()) {
                        cardDto_.setChoosenHandful(annoncesPoignees_.first());
                    } else {
                        cardDto_.setChoosenHandful(Handfuls.NO);
                    }
                    cardDto_.setHandful(poignee_);
                    cardDto_.setMiseres(annoncesMiseres_);
                    cardDto_.setExcludedTrumps(new HandTarot());
                    cardDto_.setLocale(Constants.getDefaultLanguage());
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), cardDto_);
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
                Net.initAllReceived();
                for (byte p: Net.activePlayers()) {
                    Net.sendObject(Net.getSocketByPlace(p), calledCards_);
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
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), calledCards_);
                    }
                    return;
                }
            }

            if (game_.getContrat().getJeuChien() == PlayingDog.WITH) {
                if (!game_.unionPlis(true).isEmpty()) {
                    game_.ecarter(false);
                } else {
                    game_.ecarter(true);
                }
                if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
                    DiscardedTrumps discarded_ = new DiscardedTrumps();
                    discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
                    Net.initAllReceived();
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), discarded_);
                    }
                    return;
                }
                if (game_.chelemAnnonce()) {
                    BiddingSlamAfter bid_ = new BiddingSlamAfter();
                    bid_.setPlace(game_.getPreneur());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                    return;
                }
                byte dealer_=game_.getDistribution().getDonneur();
                if(!game_.chelemAnnonce()) {
                    /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
                    game_.setEntameur(game_.playerAfter(dealer_));
                }
                game_.setPliEnCours(true);
                playingTarotCard();
                return;
            }
            game_.gererChienInconnu();
            if (!game_.getContrat().isFaireTousPlis()) {
                game_.slam();
                if (game_.chelemAnnonce()) {
                    Net.initAllReceived();
                    BiddingSlamAfter bid_ = new BiddingSlamAfter();
                    bid_.setPlace(game_.getPreneur());
                    bid_.setLocale(Constants.getDefaultLanguage());
                    for (byte p: Net.activePlayers()) {
                        Net.sendObject(Net.getSocketByPlace(p), bid_);
                    }
                    return;
                }
            }
            playingTarotCard();
        }
    }
    private static void endGameBelote() {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames().partieBelote().getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames().contains(i)) {
                players_.add(Net.getNicknames().getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        ResultsBelote res_ = new ResultsBelote();
        CustList<Numbers<Long>> scores_ = Net.getScores();
        CustList<Numbers<Long>> list_ = new CustList<Numbers<Long>>();
        for (Numbers<Long> v: scores_) {
            list_.add(new Numbers<Long>(v));
        }
        res_.setGame(Net.getGames().partieBelote());
        res_.initialize(new StringList(players_), list_);
        for (byte p: Net.activePlayers()) {
            String loc_ = Net.getLanguageByPlace(p);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.setUser(p);
            Net.sendObject(Net.getSocketByPlace(p), res_);
        }
    }
    private static void endGamePresident() {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames().partiePresident().getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames().contains(i)) {
                players_.add(Net.getNicknames().getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(Net.getGames().partiePresident());
        CustList<Numbers<Long>> scores_ = Net.getScores();
        CustList<Numbers<Long>> list_ = new CustList<Numbers<Long>>();
        for (Numbers<Long> v: scores_) {
            list_.add(new Numbers<Long>(v));
        }
        res_.initialize(new StringList(players_), list_);
        for (byte p: Net.activePlayers()) {
            String loc_ = Net.getLanguageByPlace(p);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.setUser(p);
            Net.sendObject(Net.getSocketByPlace(p), res_);
        }
    }
    private static void endGameTarot() {
        StringList players_ = new StringList();
        int nbPlayers_ = Net.getGames().partieTarot().getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nbPlayers_; i++) {
            if (Net.getNicknames().contains(i)) {
                players_.add(Net.getNicknames().getVal(i));
            } else {
                players_.add(EMPTY_STRING);
            }
        }
        ResultsTarot res_ = new ResultsTarot();
        res_.setGame(Net.getGames().partieTarot());
        CustList<Numbers<Long>> scores_ = Net.getScores();
        CustList<Numbers<Long>> list_ = new CustList<Numbers<Long>>();
        for (Numbers<Long> v: scores_) {
            list_.add(new Numbers<Long>(v));
        }
        res_.initialize(new StringList(players_), list_);
        for (byte p: Net.activePlayers()) {
            String loc_ = Net.getLanguageByPlace(p);
            DocumentReaderCardsResultsUtil.setMessages(res_,loc_);
            res_.setUser(p);
            Net.sendObject(Net.getSocketByPlace(p), res_);
        }
    }
    private static void playingBeloteCard() {
        GameBelote game_ = Net.getGames().partieBelote();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_)) {
            AllowPlayingBelote decla_ = new AllowPlayingBelote();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setFirstRoundPlaying(game_.premierTour());
            if (game_.premierTour()) {
                decla_.setDeclaration(game_.strategieAnnonces(place_));
            } else {
                decla_.setDeclaration(new DeclareHandBelote());
            }
            decla_.setPossibleBeloteRebelote(!game_.cartesBeloteRebelote().estVide());
            decla_.setAllowedBeloteRebelote(game_.autoriseBeloteRebelote(place_));
            Net.sendObject(Net.getSocketByPlace(place_),decla_);
            return;
        }
        ThreadUtil.sleep(800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        CardBelote card_ = game_.getCarteJouee();
        boolean declareBeloteRebelote_ = false;
        if(game_.annoncerBeloteRebelote(place_,card_)) {
            game_.setAnnoncesBeloteRebelote(place_,card_);
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
        Net.initAllReceived();
        for (byte p: Net.activePlayers()) {
            Net.sendObject(Net.getSocketByPlace(p),cardDto_);
        }
    }
    private static void playingPresidentCard() {
        GamePresident game_ = Net.getGames().partiePresident();
        byte place_ = game_.getNextPlayer();
        if (Net.isHumanPlayer(place_)) {
            AllowPlayingPresident allow_ = new AllowPlayingPresident();
            allow_.setEnabledPass(!game_.getProgressingTrick().estVide());
            allow_.setStatus(game_.getStatus(place_));
            allow_.setReversed(game_.isReversed());
            Net.sendObject(Net.getSocketByPlace(place_),allow_);
            return;
        }
        ThreadUtil.sleep(800);
        if (game_.currentPlayerHasPlayed(place_, Constants.getDefaultLanguage())) {
            return;
        }
        PlayingCardPresident cardDto_ = new PlayingCardPresident();
        cardDto_.setPlayedHand(game_.getPlayedCards());
        cardDto_.setPlace(place_);
        cardDto_.setNextPlayer(game_.getNextPlayer());
        cardDto_.setStatus(game_.getLastStatus());
        cardDto_.setPlayedCard(CardPresident.WHITE);
        cardDto_.setLocale(Constants.getDefaultLanguage());
        Net.initAllReceived();
        for (byte p: Net.activePlayers()) {
            Net.sendObject(Net.getSocketByPlace(p),cardDto_);
        }
    }
    private static void playingTarotCard() {
        GameTarot game_ = Net.getGames().partieTarot();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_)) {
            AllowPlayingTarot decla_ = new AllowPlayingTarot();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setFirstRoundPlaying(game_.premierTour());
            if (game_.pasJeuMisere() && game_.premierTour()) {
                EnumList<Handfuls> handfuls_ = new EnumList<Handfuls>(game_.getRegles().getCurrentAllowedHandfuls());
                EnumMap<Handfuls,Integer> allowedHanduls_ = game_.getRegles().getPoigneesAutorisees();
                handfuls_.sortElts(new AllowedHandfulComparator(allowedHanduls_));
                decla_.setAllowedHandfuls(new EnumList<Handfuls>(handfuls_));
                decla_.setRequiredTrumps(new EnumMap<Handfuls,Integer>(game_.getRegles().getPoigneesAutorisees()));
                decla_.setAllowedMiseres(new EnumList<Miseres>(game_.getRegles().getMiseres()));
            } else {
                decla_.setAllowedHandfuls(new EnumList<Handfuls>());
                decla_.setRequiredTrumps(new EnumMap<Handfuls,Integer>());
                decla_.setAllowedMiseres(new EnumList<Miseres>());
            }
            Net.sendObject(Net.getSocketByPlace(place_), decla_);
            return;
        }
        ThreadUtil.sleep(800);
        if (game_.currentPlayerHasPlayed(place_)) {
            return;
        }
        CardTarot card_ = game_.getCarteJoueee();
        PlayingCardTarot cardDto_ = new PlayingCardTarot();
        cardDto_.setTakerIndex(game_.getPreneur());
        cardDto_.setPlace(place_);
        cardDto_.setPlayedCard(card_);
        cardDto_.setLocale(Constants.getDefaultLanguage());
        if (game_.premierTour() && !game_.pasJeuMisere()) {
            EnumList<Handfuls> annoncesPoignees_ = game_.strategieAnnoncesPoignees(place_);
            game_.setAnnoncesPoignees(place_, annoncesPoignees_);
            EnumList<Miseres> annoncesMiseres_ = game_.strategieAnnoncesMiseres(place_);
            game_.setAnnoncesMiseres(place_, annoncesMiseres_);
            HandTarot poignee_=game_.strategiePoignee(place_);
            game_.ajouterPoignee(poignee_,place_);
            if (!annoncesPoignees_.isEmpty()) {
                cardDto_.setChoosenHandful(annoncesPoignees_.first());
            } else {
                cardDto_.setChoosenHandful(Handfuls.NO);
            }
            cardDto_.setHandful(poignee_);
            cardDto_.setMiseres(annoncesMiseres_);
        } else {
            cardDto_.setChoosenHandful(Handfuls.NO);
            cardDto_.setMiseres(new EnumList<Miseres>());
            cardDto_.setHandful(game_.getPoignee(place_));
        }
        cardDto_.setExcludedTrumps(new HandTarot());
        Net.initAllReceived();
        for (byte p: Net.activePlayers()) {
            Net.sendObject(Net.getSocketByPlace(p), cardDto_);
        }
    }
}
