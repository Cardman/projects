package cards.network.sml;
import cards.belote.ResultsBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
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
import cards.network.common.DelegateServer;
import cards.network.common.Ok;
import cards.network.common.PlayerActionGame;
import cards.network.common.Quit;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.IndexOfArriving;
import cards.network.common.before.NewPlayer;
import cards.network.common.before.PlayerActionBeforeGame;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.DoneBidding;
import cards.network.common.displaying.DonePause;
import cards.network.common.displaying.DonePlaying;
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
import cards.network.tarot.unlock.CallableCardsDiscard;
import cards.president.ResultsPresident;
import cards.president.TricksHandsPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.ResultsTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
public final class DocumentWriterCardsMultiUtil {

    private static final String FIELD_ACCEPTABLE = "acceptable";
    private static final String FIELD_ALLOWED_BELOTE_REBELOTE = "allowedBeloteRebelote";
    private static final String FIELD_ALLOWED_BIDS = "allowedBids";
    private static final String FIELD_ALLOWED_HANDFULS = "allowedHandfuls";
    private static final String FIELD_ALLOWED_MISERES = "allowedMiseres";
    private static final String FIELD_ARRIVING = "arriving";
    private static final String FIELD_BID = "bid";
    private static final String FIELD_BID_BELOTE = "bidBelote";
    private static final String FIELD_BIDS = "bids";
    private static final String FIELD_BUSY = "busy";
    private static final String FIELD_CALL_AFTER = "callAfter";
    private static final String FIELD_CALLABLE_CARDS = "callableCards";
    private static final String FIELD_CALLED_CARD = "calledCard";
    private static final String FIELD_CALLED_CARDS = "calledCards";
    private static final String FIELD_CARD = "card";
    private static final String FIELD_CARDS = "cards";
    private static final String FIELD_CHOOSEN_HANDFUL = "choosenHandful";
    private static final String FIELD_CLOSING = "closing";
    private static final String FIELD_DEALER = "dealer";
    private static final String FIELD_DECK = "deck";
    private static final String FIELD_DECLARATION = "declaration";
    private static final String FIELD_DECLARE = "declare";
    private static final String FIELD_DECLARING = "declaring";
    private static final String FIELD_DECLARING_BELOTE_REBELOTE = "declaringBeloteRebelote";
    private static final String FIELD_DECLARING_SLAM = "declaringSlam";
    private static final String FIELD_DISCARDED = "discarded";
    private static final String FIELD_DISCARDING = "discarding";
    private static final String FIELD_DOG = "dog";
    private static final String FIELD_ENABLED_PASS = "enabledPass";
    private static final String FIELD_ERROR = "error";
    private static final String FIELD_ERROR_MESSAGE = "errorMessage";
    private static final String FIELD_EXCLUDED_TRUMPS = "excludedTrumps";
    private static final String FIELD_FIRST = "first";
    private static final String FIELD_FIRST_ROUND_PLAYING = "firstRoundPlaying";
    private static final String FIELD_FORCED = "forced";
    private static final String FIELD_GAMES = "games";
    private static final String FIELD_GIVEN = "given";
    private static final String FIELD_HANDFUL = "handful";
    private static final String FIELD_HUMAN_TAKER = "humanTaker";
    private static final String FIELD_IN_HAND = "inHand";
    private static final String FIELD_INDEX = "index";
    private static final String FIELD_LANGUAGE = "language";
    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_MAX_CARDS = "maxCards";
    private static final String FIELD_MISERES = "miseres";
    private static final String FIELD_NB_PLAYERS = "nbPlayers";
    private static final String FIELD_NEW_HAND = "newHand";
    private static final String FIELD_NEXT_PLAYER = "nextPlayer";
    private static final String FIELD_NICKNAMES = "nicknames";
    private static final String FIELD_PASS = "pass";
    private static final String FIELD_PASS_ISSUE = "passIssue";
    private static final String FIELD_PLACE = "place";
    private static final String FIELD_PLACES_PLAYERS = "placesPlayers";
    private static final String FIELD_PLAYED_CARD = "playedCard";
    private static final String FIELD_PLAYED_HAND = "playedHand";
    private static final String FIELD_POINTS = "points";
    private static final String FIELD_POSSIBLE_BELOTE_REBELOTE = "possibleBeloteRebelote";
    private static final String FIELD_PSEUDO = "pseudo";
    private static final String FIELD_PSEUDOS = "pseudos";
    private static final String FIELD_READY = "ready";
    private static final String FIELD_READY_PLAYERS = "readyPlayers";
    private static final String FIELD_REASON = "reason";
    private static final String FIELD_RECEIVED = "received";
    private static final String FIELD_RECEIVED_CARDS = "receivedCards";
    private static final String FIELD_REFRESHED_HAND = "refreshedHand";
    private static final String FIELD_REP = "rep";
    private static final String FIELD_REQUIRED_TRUMPS = "requiredTrumps";
    private static final String FIELD_REVERSED = "reversed";
    private static final String FIELD_RULES_BELOTE = "rulesBelote";
    private static final String FIELD_RULES_PRESIDENT = "rulesPresident";
    private static final String FIELD_RULES_TAROT = "rulesTarot";
    private static final String FIELD_SERVER = "server";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_TAKER = "taker";
    private static final String FIELD_TAKER_INDEX = "takerIndex";
    private static final String FIELD_TEAMS = "teams";
    private static final String FIELD_TRUMPS = "trumps";
    private static final String TYPE_ALLOW_BIDDING_BELOTE = "AllowBiddingBelote";
    private static final String TYPE_ALLOW_BIDDING_TAROT = "AllowBiddingTarot";
    private static final String TYPE_ALLOW_DISCARDING = "AllowDiscarding";
    private static final String TYPE_ALLOW_PLAYING_BELOTE = "AllowPlayingBelote";
    private static final String TYPE_ALLOW_PLAYING_PRESIDENT = "AllowPlayingPresident";
    private static final String TYPE_ALLOW_PLAYING_TAROT = "AllowPlayingTarot";
    private static final String TYPE_BIDDING_BELOTE = "BiddingBelote";
    private static final String TYPE_BIDDING_SLAM_AFTER = "BiddingSlamAfter";
    private static final String TYPE_BIDDING_TAROT = "BiddingTarot";
    private static final String TYPE_BYE = "Bye";
    private static final String TYPE_CALLABLE_CARDS = "CallableCards";
    private static final String TYPE_CALLABLE_CARDS_DISCARD = "CallableCardsDiscard";
    private static final String TYPE_CALLED_CARD_KNOWN = "CalledCardKnown";
    private static final String TYPE_CALLED_CARDS = "CalledCards";
    private static final String TYPE_CHOOSEN_PLACE = "ChoosenPlace";
    private static final String TYPE_COMPLETED_HAND = "CompletedHand";
    private static final String TYPE_DEALT = "Dealt";
    private static final String TYPE_DEALT_HAND_BELOTE = "DealtHandBelote";
    private static final String TYPE_DEALT_HAND_PRESIDENT = "DealtHandPresident";
    private static final String TYPE_DEALT_HAND_TAROT = "DealtHandTarot";
    private static final String TYPE_DELEGATE_SERVER = "DelegateServer";
    private static final String TYPE_DISCARDED_CARD = "DiscardedCard";
    private static final String TYPE_DISCARDED_CARDS = "DiscardedCards";
    private static final String TYPE_DISCARDED_TRUMPS = "DiscardedTrumps";
    private static final String TYPE_DOG = "Dog";
    private static final String TYPE_DONE_BIDDING = "DoneBidding";
    private static final String TYPE_DONE_DISPLAY_SLAM = "DoneDisplaySlam";
    private static final String TYPE_DONE_PAUSE = "DonePause";
    private static final String TYPE_DONE_PLAYING = "DonePlaying";
    private static final String TYPE_ERROR_BIDDING = "ErrorBidding";
    private static final String TYPE_ERROR_BIDDING_BELOTE = "ErrorBiddingBelote";
    private static final String TYPE_ERROR_DISCARDING = "ErrorDiscarding";
    private static final String TYPE_ERROR_HANDFUL = "ErrorHandful";
    private static final String TYPE_ERROR_PLAYING = "ErrorPlaying";
    private static final String TYPE_ERROR_PLAYING_BELOTE = "ErrorPlayingBelote";
    private static final String TYPE_ERROR_PLAYING_PRESIDENT = "ErrorPlayingPresident";
    private static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    private static final String TYPE_NEW_PLAYER = "NewPlayer";
    private static final String TYPE_OK = "Ok";
    private static final String TYPE_PLAYERS_NAME_PRESENT = "PlayersNamePresent";
    private static final String TYPE_PLAYING_CARD_BELOTE = "PlayingCardBelote";
    private static final String TYPE_PLAYING_CARD_PRESIDENT = "PlayingCardPresident";
    private static final String TYPE_PLAYING_CARD_TAROT = "PlayingCardTarot";
    private static final String TYPE_QUIT = "Quit";
    private static final String TYPE_READY = "Ready";
    private static final String TYPE_RECEIVED_GIVEN_CARDS = "ReceivedGivenCards";
    private static final String TYPE_REFRESH_HAND = "RefreshHand";
    private static final String TYPE_REFRESH_HAND_BELOTE = "RefreshHandBelote";
    private static final String TYPE_REFRESH_HAND_PLAYING_BELOTE = "RefreshHandPlayingBelote";
    private static final String TYPE_REFRESH_HAND_PLAYING_PRESIDENT = "RefreshHandPlayingPresident";
    private static final String TYPE_REFRESHED_HAND_PRESIDENT = "RefreshedHandPresident";
    private static final String TYPE_REFRESHING_DONE = "RefreshingDone";
    private static final String TYPE_REFRESHING_DONE_BELOTE = "RefreshingDoneBelote";
    private static final String TYPE_REFRESHING_DONE_PRESIDENT = "RefreshingDonePresident";
    private static final String TYPE_SEEN_DISCARDED_TRUMPS = "SeenDiscardedTrumps";
    private static final String TYPE_SELECT_TEAMS = "SelectTeams";
    private static final String TYPE_SELECT_TRICKS_HANDS = "SelectTricksHands";
    private static final String TYPE_SHOW_DOG = "ShowDog";
    private static final String TYPE_TEAMS_PLAYERS = "TeamsPlayers";
    private static final String TYPE_VALIDATE_DOG = "ValidateDog";
    private static final String TYPE_PLAYER_ACTION_BEFORE_GAME = "PlayerActionBeforeGame";
    private static final String TYPE_PLAYER_ACTION_GAME = "PlayerActionGame";

    private static final String TYPE_RESULTS_BELOTE = "ResultsBelote";
    private static final String TYPE_RESULTS_PRESIDENT = "ResultsPresident";
    private static final String TYPE_RESULTS_TAROT = "ResultsTarot";
    public static String setObject(Object _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        if (_object instanceof ResultsBelote) {
            Element element_ = doc_.createElement(TYPE_RESULTS_BELOTE);
            DocumentWriterBeloteUtil.setResultsBelote((ResultsBelote)_object,element_,doc_);
            doc_.appendChild(element_);
            return doc_.export();
        }
        if (_object instanceof TricksHandsBelote) {
            doc_.appendChild(DocumentWriterBeloteUtil.setTricksHandsBelote((TricksHandsBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof BiddingBelote) {
            doc_.appendChild(setPlayerActionGame((BiddingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayingCardBelote) {
            doc_.appendChild(setPlayerActionGame((PlayingCardBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CompletedHand) {
            doc_.appendChild(setPlayerActionGame((CompletedHand)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DealtHandBelote) {
            doc_.appendChild(setDealtHandBelote((DealtHandBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshHandBelote) {
            doc_.appendChild(setPlayerActionGame((RefreshHandBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorBiddingBelote) {
            doc_.appendChild(setErrorBiddingBelote((ErrorBiddingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorPlayingBelote) {
            doc_.appendChild(setErrorPlayingBelote((ErrorPlayingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshHandPlayingBelote) {
            doc_.appendChild(setPlayerActionGame((RefreshHandPlayingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshingDoneBelote) {
            doc_.appendChild(setPlayerActionGame((RefreshingDoneBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowBiddingBelote) {
            doc_.appendChild(setAllowBiddingBelote((AllowBiddingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowPlayingBelote) {
            doc_.appendChild(setAllowPlayingBelote((AllowPlayingBelote)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Bye) {
            doc_.appendChild(setBye((Bye)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Dealt) {
            doc_.appendChild(setPlayerActionGame((Dealt)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DelegateServer) {
            doc_.appendChild(setDelegateServer((DelegateServer)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Ok) {
            doc_.appendChild(setPlayerActionGame((Ok)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Quit) {
            doc_.appendChild(setPlayerActionGame((Quit)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ChoosenPlace) {
            doc_.appendChild(setPlayerActionBeforeGame((ChoosenPlace)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof IndexOfArriving) {
            doc_.appendChild(setPlayerActionBeforeGame((IndexOfArriving)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof NewPlayer) {
            doc_.appendChild(setPlayerActionBeforeGame((NewPlayer)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayersNamePresent) {
            doc_.appendChild(setPlayersNamePresent((PlayersNamePresent)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Ready) {
            doc_.appendChild(setPlayerActionBeforeGame((Ready)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DoneBidding) {
            doc_.appendChild(setPlayerActionGame((DoneBidding)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DonePause) {
            doc_.appendChild(setPlayerActionGame((DonePause)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DonePlaying) {
            doc_.appendChild(setPlayerActionGame((DonePlaying)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof SelectTeams) {
            doc_.appendChild(setPlayerActionGame((SelectTeams)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof SelectTricksHands) {
            doc_.appendChild(setPlayerActionGame((SelectTricksHands)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof TeamsPlayers) {
            doc_.appendChild(setTeamsPlayers((TeamsPlayers)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DiscardedCards) {
            doc_.appendChild(setPlayerActionGame((DiscardedCards)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayingCardPresident) {
            doc_.appendChild(setPlayerActionGame((PlayingCardPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DealtHandPresident) {
            doc_.appendChild(setDealtHandPresident((DealtHandPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ReceivedGivenCards) {
            doc_.appendChild(setReceivedGivenCards((ReceivedGivenCards)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshedHandPresident) {
            doc_.appendChild(setPlayerActionGame((RefreshedHandPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorPlayingPresident) {
            doc_.appendChild(setErrorPlayingPresident((ErrorPlayingPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshHandPlayingPresident) {
            doc_.appendChild(setPlayerActionGame((RefreshHandPlayingPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshingDonePresident) {
            doc_.appendChild(setPlayerActionGame((RefreshingDonePresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowDiscarding) {
            doc_.appendChild(setAllowDiscarding((AllowDiscarding)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowPlayingPresident) {
            doc_.appendChild(setAllowPlayingPresident((AllowPlayingPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Dog) {
            doc_.appendChild(setDog((Dog)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof BiddingSlamAfter) {
            doc_.appendChild(setPlayerActionGame((BiddingSlamAfter)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof BiddingTarot) {
            doc_.appendChild(setPlayerActionGame((BiddingTarot)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CalledCards) {
            doc_.appendChild(setPlayerActionGame((CalledCards)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DiscardedCard) {
            doc_.appendChild(setPlayerActionGame((DiscardedCard)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DiscardedTrumps) {
            doc_.appendChild(setDiscardedTrumps((DiscardedTrumps)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayingCardTarot) {
            doc_.appendChild(setPlayerActionGame((PlayingCardTarot)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ValidateDog) {
            doc_.appendChild(setPlayerActionGame((ValidateDog)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DealtHandTarot) {
            doc_.appendChild(setDealtHandTarot((DealtHandTarot)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorBidding) {
            doc_.appendChild(setErrorBidding((ErrorBidding)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorDiscarding) {
            doc_.appendChild(setErrorDiscarding((ErrorDiscarding)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorHandful) {
            doc_.appendChild(setErrorHandful((ErrorHandful)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ErrorPlaying) {
            doc_.appendChild(setErrorPlaying((ErrorPlaying)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CalledCardKnown) {
            doc_.appendChild(setPlayerActionGame((CalledCardKnown)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof DoneDisplaySlam) {
            doc_.appendChild(setPlayerActionGame((DoneDisplaySlam)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshHand) {
            doc_.appendChild(setPlayerActionGame((RefreshHand)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof RefreshingDone) {
            doc_.appendChild(setPlayerActionGame((RefreshingDone)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof SeenDiscardedTrumps) {
            doc_.appendChild(setPlayerActionGame((SeenDiscardedTrumps)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ShowDog) {
            doc_.appendChild(setPlayerActionGame((ShowDog)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowBiddingTarot) {
            doc_.appendChild(setAllowBiddingTarot((AllowBiddingTarot)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof AllowPlayingTarot) {
            doc_.appendChild(setAllowPlayingTarot((AllowPlayingTarot)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CallableCards) {
            doc_.appendChild(setCallableCards((CallableCards)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CallableCardsDiscard) {
            doc_.appendChild(setCallableCardsDiscard((CallableCardsDiscard)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ResultsPresident) {
            Element element_ = doc_.createElement(TYPE_RESULTS_PRESIDENT);
            DocumentWriterPresidentUtil.setResultsPresident((ResultsPresident)_object,element_,doc_);
            doc_.appendChild(element_);
            return doc_.export();
        }
        if (_object instanceof TricksHandsPresident) {
            doc_.appendChild(DocumentWriterPresidentUtil.setTricksHandsPresident((TricksHandsPresident)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ResultsTarot) {
            Element element_ = doc_.createElement(TYPE_RESULTS_TAROT);
            DocumentWriterTarotUtil.setResultsTarot((ResultsTarot)_object,element_,doc_);
            doc_.appendChild(element_);
            return doc_.export();
        }
        if (_object instanceof TricksHandsTarot) {
            doc_.appendChild(DocumentWriterTarotUtil.setTricksHandsTarot((TricksHandsTarot)_object, "", doc_));
            return doc_.export();
        }
        return doc_.export();
    }

    private static void setBiddingBelote(BiddingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setBidBeloteSuit(_object.getBidBelote(),FIELD_BID_BELOTE,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setPlayingCardBelote(PlayingCardBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaring(),FIELD_DECLARING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringBeloteRebelote(),FIELD_DECLARING_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclare(),FIELD_DECLARE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setDealtHandBelote(DealtHandBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DEALT_HAND_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealtHandBelote(_object,element_,_document);
        return element_;
    }

    private static void setDealtHandBelote(DealtHandBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getDeck(),FIELD_DECK,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setListBidBeloteSuit(_object.getAllowedBids(),FIELD_ALLOWED_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setDealingBelote(_object.getRep(),FIELD_REP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getPoints(),FIELD_POINTS,_document));
    }

    private static void setRefreshHandBelote(RefreshHandBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getRefreshedHand(),FIELD_REFRESHED_HAND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setErrorBiddingBelote(ErrorBiddingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_BIDDING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorBiddingBelote(_object,element_,_document);
        return element_;
    }

    private static void setErrorBiddingBelote(ErrorBiddingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setBidBeloteSuit(_object.getBid(),FIELD_BID,_document));
    }

    private static Element setErrorPlayingBelote(ErrorPlayingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_PLAYING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorPlayingBelote(_object,element_,_document);
        return element_;
    }

    private static void setErrorPlayingBelote(ErrorPlayingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
    }

    private static void setRefreshHandPlayingBelote(RefreshHandPlayingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaring(),FIELD_DECLARING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringBeloteRebelote(),FIELD_DECLARING_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclare(),FIELD_DECLARE,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setRefreshingDoneBelote(RefreshingDoneBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaring(),FIELD_DECLARING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringBeloteRebelote(),FIELD_DECLARING_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclare(),FIELD_DECLARE,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setAllowBiddingBelote(AllowBiddingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_BIDDING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowBiddingBelote(_object,element_,_document);
        return element_;
    }

    private static void setAllowBiddingBelote(AllowBiddingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setListBidBeloteSuit(_object.getBids(),FIELD_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getPoints(),FIELD_POINTS,_document));
    }

    private static Element setAllowPlayingBelote(AllowPlayingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_PLAYING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowPlayingBelote(_object,element_,_document);
        return element_;
    }

    private static void setAllowPlayingBelote(AllowPlayingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isFirstRoundPlaying(),FIELD_FIRST_ROUND_PLAYING,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclaration(),FIELD_DECLARATION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPossibleBeloteRebelote(),FIELD_POSSIBLE_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAllowedBeloteRebelote(),FIELD_ALLOWED_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
    }

    private static Element setBye(Bye _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BYE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBye(_object,element_,_document);
        return element_;
    }

    private static void setBye(Bye _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isForced(),FIELD_FORCED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBusy(),FIELD_BUSY,_document));
    }

    private static Element setDelegateServer(DelegateServer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DELEGATE_SERVER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDelegateServer(_object,element_,_document);
        return element_;
    }

    private static void setDelegateServer(DelegateServer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsUnionUtil.setGames(_object.getGames(),FIELD_GAMES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerString(_object.getNicknames(),FIELD_NICKNAMES,_document));
    }

    private static Element setPlayerActionGame(PlayerActionGame _object, String _fieldName, Document _document) {
        if (_object instanceof BiddingBelote) {
            Element element_ = _document.createElement(TYPE_BIDDING_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBiddingBelote((BiddingBelote)_object,element_,_document);
            return element_;
        }
        if (_object instanceof PlayingCardBelote) {
            Element element_ = _document.createElement(TYPE_PLAYING_CARD_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayingCardBelote((PlayingCardBelote)_object,element_,_document);
            return element_;
        }
        if (_object instanceof CompletedHand) {
            Element element_ = _document.createElement(TYPE_COMPLETED_HAND);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshHandPlayingBelote) {
            Element element_ = _document.createElement(TYPE_REFRESH_HAND_PLAYING_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshHandPlayingBelote((RefreshHandPlayingBelote)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshingDoneBelote) {
            Element element_ = _document.createElement(TYPE_REFRESHING_DONE_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshingDoneBelote((RefreshingDoneBelote)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshHandBelote) {
            Element element_ = _document.createElement(TYPE_REFRESH_HAND_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshHandBelote((RefreshHandBelote)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Dealt) {
            Element element_ = _document.createElement(TYPE_DEALT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DoneBidding) {
            Element element_ = _document.createElement(TYPE_DONE_BIDDING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DonePause) {
            Element element_ = _document.createElement(TYPE_DONE_PAUSE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DonePlaying) {
            Element element_ = _document.createElement(TYPE_DONE_PLAYING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof Ok) {
            Element element_ = _document.createElement(TYPE_OK);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof Quit) {
            Element element_ = _document.createElement(TYPE_QUIT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setQuit((Quit)_object,element_,_document);
            return element_;
        }
        if (_object instanceof SelectTeams) {
            Element element_ = _document.createElement(TYPE_SELECT_TEAMS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof SelectTricksHands) {
            Element element_ = _document.createElement(TYPE_SELECT_TRICKS_HANDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DiscardedCards) {
            Element element_ = _document.createElement(TYPE_DISCARDED_CARDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDiscardedCards((DiscardedCards)_object,element_,_document);
            return element_;
        }
        if (_object instanceof PlayingCardPresident) {
            Element element_ = _document.createElement(TYPE_PLAYING_CARD_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayingCardPresident((PlayingCardPresident)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshHandPlayingPresident) {
            Element element_ = _document.createElement(TYPE_REFRESH_HAND_PLAYING_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshHandPlayingPresident((RefreshHandPlayingPresident)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshingDonePresident) {
            Element element_ = _document.createElement(TYPE_REFRESHING_DONE_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshingDonePresident((RefreshingDonePresident)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshedHandPresident) {
            Element element_ = _document.createElement(TYPE_REFRESHED_HAND_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof BiddingSlamAfter) {
            Element element_ = _document.createElement(TYPE_BIDDING_SLAM_AFTER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof BiddingTarot) {
            Element element_ = _document.createElement(TYPE_BIDDING_TAROT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBiddingTarot((BiddingTarot)_object,element_,_document);
            return element_;
        }
        if (_object instanceof CalledCards) {
            Element element_ = _document.createElement(TYPE_CALLED_CARDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCalledCards((CalledCards)_object,element_,_document);
            return element_;
        }
        if (_object instanceof DiscardedCard) {
            Element element_ = _document.createElement(TYPE_DISCARDED_CARD);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDiscardedCard((DiscardedCard)_object,element_,_document);
            return element_;
        }
        if (_object instanceof PlayingCardTarot) {
            Element element_ = _document.createElement(TYPE_PLAYING_CARD_TAROT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayingCardTarot((PlayingCardTarot)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ValidateDog) {
            Element element_ = _document.createElement(TYPE_VALIDATE_DOG);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof CalledCardKnown) {
            Element element_ = _document.createElement(TYPE_CALLED_CARD_KNOWN);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DoneDisplaySlam) {
            Element element_ = _document.createElement(TYPE_DONE_DISPLAY_SLAM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshHand) {
            Element element_ = _document.createElement(TYPE_REFRESH_HAND);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshHand((RefreshHand)_object,element_,_document);
            return element_;
        }
        if (_object instanceof RefreshingDone) {
            Element element_ = _document.createElement(TYPE_REFRESHING_DONE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshingDone((RefreshingDone)_object,element_,_document);
            return element_;
        }
        if (_object instanceof SeenDiscardedTrumps) {
            Element element_ = _document.createElement(TYPE_SEEN_DISCARDED_TRUMPS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setSeenDiscardedTrumps((SeenDiscardedTrumps)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ShowDog) {
            Element element_ = _document.createElement(TYPE_SHOW_DOG);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_GAME);
    }

    private static void setPlayerActionGame(PlayerActionGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlace(),FIELD_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLocale(),FIELD_LOCALE,_document));
    }

    private static void setQuit(Quit _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setChoosenPlace(ChoosenPlace _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getPlace(),FIELD_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerByte(_object.getPlacesPlayers(),FIELD_PLACES_PLAYERS,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static void setNewPlayer(NewPlayer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isArriving(),FIELD_ARRIVING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLanguage(),FIELD_LANGUAGE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAcceptable(),FIELD_ACCEPTABLE,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setPlayerActionBeforeGame(PlayerActionBeforeGame _object, String _fieldName, Document _document) {
        if (_object instanceof ChoosenPlace) {
            Element element_ = _document.createElement(TYPE_CHOOSEN_PLACE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setChoosenPlace((ChoosenPlace)_object,element_,_document);
            return element_;
        }
        if (_object instanceof IndexOfArriving) {
            Element element_ = _document.createElement(TYPE_INDEX_OF_ARRIVING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionBeforeGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof NewPlayer) {
            Element element_ = _document.createElement(TYPE_NEW_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setNewPlayer((NewPlayer)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Ready) {
            Element element_ = _document.createElement(TYPE_READY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setReady((Ready)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_BEFORE_GAME);
    }

    private static void setPlayerActionBeforeGame(PlayerActionBeforeGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndex(),FIELD_INDEX,_document));
    }

    private static Element setPlayersNamePresent(PlayersNamePresent _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_PLAYERS_NAME_PRESENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPlayersNamePresent(_object,element_,_document);
        return element_;
    }

    private static void setPlayersNamePresent(PlayersNamePresent _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerString(_object.getPseudos(),FIELD_PSEUDOS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerByte(_object.getPlacesPlayers(),FIELD_PLACES_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerBoolean(_object.getReadyPlayers(),FIELD_READY_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbPlayers(),FIELD_NB_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isFirst(),FIELD_FIRST,_document));
        _element.appendChild(DocumentWriterTarotUtil.setRulesTarot(_object.getRulesTarot(),FIELD_RULES_TAROT,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setRulesPresident(_object.getRulesPresident(),FIELD_RULES_PRESIDENT,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setRulesBelote(_object.getRulesBelote(),FIELD_RULES_BELOTE,_document));
    }

    private static void setReady(Ready _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReady(),FIELD_READY,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setTeamsPlayers(TeamsPlayers _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TEAMS_PLAYERS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTeamsPlayers(_object,element_,_document);
        return element_;
    }

    private static void setTeamsPlayers(TeamsPlayers _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setListListByte(_object.getTeams(),FIELD_TEAMS,_document));
    }

    private static void setDiscardedCards(DiscardedCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getDiscarded(),FIELD_DISCARDED,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setPlayingCardPresident(PlayingCardPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNextPlayer(),FIELD_NEXT_PLAYER,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setDealtHandPresident(DealtHandPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DEALT_HAND_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealtHandPresident(_object,element_,_document);
        return element_;
    }

    private static void setDealtHandPresident(DealtHandPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getMaxCards(),FIELD_MAX_CARDS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
    }

    private static Element setReceivedGivenCards(ReceivedGivenCards _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_RECEIVED_GIVEN_CARDS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setReceivedGivenCards(_object,element_,_document);
        return element_;
    }

    private static void setReceivedGivenCards(ReceivedGivenCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getReceived(),FIELD_RECEIVED,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getGiven(),FIELD_GIVEN,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getNewHand(),FIELD_NEW_HAND,_document));
    }

    private static Element setErrorPlayingPresident(ErrorPlayingPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_PLAYING_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorPlayingPresident(_object,element_,_document);
        return element_;
    }

    private static void setErrorPlayingPresident(ErrorPlayingPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPassIssue(),FIELD_PASS_ISSUE,_document));
    }

    private static void setRefreshHandPlayingPresident(RefreshHandPlayingPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNextPlayer(),FIELD_NEXT_PLAYER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReversed(),FIELD_REVERSED,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setRefreshingDonePresident(RefreshingDonePresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNextPlayer(),FIELD_NEXT_PLAYER,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setAllowDiscarding(AllowDiscarding _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_DISCARDING);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowDiscarding(_object,element_,_document);
        return element_;
    }

    private static void setAllowDiscarding(AllowDiscarding _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getReceivedCards(),FIELD_RECEIVED_CARDS,_document));
    }

    private static Element setAllowPlayingPresident(AllowPlayingPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_PLAYING_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowPlayingPresident(_object,element_,_document);
        return element_;
    }

    private static void setAllowPlayingPresident(AllowPlayingPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isEnabledPass(),FIELD_ENABLED_PASS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setPlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReversed(),FIELD_REVERSED,_document));
    }

    private static Element setDog(Dog _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DOG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDog(_object,element_,_document);
        return element_;
    }

    private static void setDog(Dog _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getDog(),FIELD_DOG,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTaker(),FIELD_TAKER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHumanTaker(),FIELD_HUMAN_TAKER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCallAfter(),FIELD_CALL_AFTER,_document));
    }

    private static void setBiddingTarot(BiddingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getBid(),FIELD_BID,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setCalledCards(CalledCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCalledCards(),FIELD_CALLED_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDiscarding(),FIELD_DISCARDING,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setDiscardedCard(DiscardedCard _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isInHand(),FIELD_IN_HAND,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setDiscardedTrumps(DiscardedTrumps _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DISCARDED_TRUMPS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDiscardedTrumps(_object,element_,_document);
        return element_;
    }

    private static void setDiscardedTrumps(DiscardedTrumps _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getTrumps(),FIELD_TRUMPS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringSlam(),FIELD_DECLARING_SLAM,_document));
    }

    private static void setPlayingCardTarot(PlayingCardTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getExcludedTrumps(),FIELD_EXCLUDED_TRUMPS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCalledCard(),FIELD_CALLED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setDealtHandTarot(DealtHandTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DEALT_HAND_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealtHandTarot(_object,element_,_document);
        return element_;
    }

    private static void setDealtHandTarot(DealtHandTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getDog(),FIELD_DOG,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListBidTarot(_object.getAllowedBids(),FIELD_ALLOWED_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
        _element.appendChild(DocumentWriterTarotUtil.setDealingTarot(_object.getRep(),FIELD_REP,_document));
    }

    private static Element setErrorBidding(ErrorBidding _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_BIDDING);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorBidding(_object,element_,_document);
        return element_;
    }

    private static void setErrorBidding(ErrorBidding _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getBid(),FIELD_BID,_document));
    }

    private static Element setErrorDiscarding(ErrorDiscarding _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_DISCARDING);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorDiscarding(_object,element_,_document);
        return element_;
    }

    private static void setErrorDiscarding(ErrorDiscarding _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getErrorMessage(),FIELD_ERROR_MESSAGE,_document));
    }

    private static Element setErrorHandful(ErrorHandful _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_HANDFUL);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorHandful(_object,element_,_document);
        return element_;
    }

    private static void setErrorHandful(ErrorHandful _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getHandful(),FIELD_HANDFUL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getError(),FIELD_ERROR,_document));
    }

    private static Element setErrorPlaying(ErrorPlaying _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ERROR_PLAYING);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setErrorPlaying(_object,element_,_document);
        return element_;
    }

    private static void setErrorPlaying(ErrorPlaying _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
    }

    private static void setRefreshHand(RefreshHand _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCalledCard(),FIELD_CALLED_CARD,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setRefreshingDone(RefreshingDone _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCalledCard(),FIELD_CALLED_CARD,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setSeenDiscardedTrumps(SeenDiscardedTrumps _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringSlam(),FIELD_DECLARING_SLAM,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static Element setAllowBiddingTarot(AllowBiddingTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_BIDDING_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowBiddingTarot(_object,element_,_document);
        return element_;
    }

    private static void setAllowBiddingTarot(AllowBiddingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setListBidTarot(_object.getBids(),FIELD_BIDS,_document));
    }

    private static Element setAllowPlayingTarot(AllowPlayingTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_PLAYING_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowPlayingTarot(_object,element_,_document);
        return element_;
    }

    private static void setAllowPlayingTarot(AllowPlayingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isFirstRoundPlaying(),FIELD_FIRST_ROUND_PLAYING,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListHandfuls(_object.getAllowedHandfuls(),FIELD_ALLOWED_HANDFULS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setMapHandfulsInteger(_object.getRequiredTrumps(),FIELD_REQUIRED_TRUMPS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getAllowedMiseres(),FIELD_ALLOWED_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
    }

    private static Element setCallableCards(CallableCards _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_CALLABLE_CARDS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setCallableCards(_object,element_,_document);
        return element_;
    }

    private static void setCallableCards(CallableCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCallableCards(),FIELD_CALLABLE_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDiscarding(),FIELD_DISCARDING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
    }

    private static Element setCallableCardsDiscard(CallableCardsDiscard _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_CALLABLE_CARDS_DISCARD);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setCallableCardsDiscard(_object,element_,_document);
        return element_;
    }

    private static void setCallableCardsDiscard(CallableCardsDiscard _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCallableCards(),FIELD_CALLABLE_CARDS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getDiscarded(),FIELD_DISCARDED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
    }

}
