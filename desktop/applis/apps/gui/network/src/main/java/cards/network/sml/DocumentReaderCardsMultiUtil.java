package cards.network.sml;
import cards.belote.ResultsBelote;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
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
import cards.network.common.before.IndexOfArrivingCards;
import cards.network.common.before.NewPlayerCards;
import cards.network.common.before.PlayerActionBeforeGameCards;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.gui.*;
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
import cards.network.tarot.unlock.*;
import cards.president.ResultsPresident;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.tarot.ResultsTarot;
import cards.tarot.sml.DocumentReaderTarotUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.core.StringUtil;

public final class DocumentReaderCardsMultiUtil {
    public static final String TYPE_RULES_BELOTE = "RulesBelote";
    public static final String TYPE_RULES_PRESIDENT = "RulesPresident";
    public static final String TYPE_RULES_TAROT = "RulesTarot";
    public static final String TYPE_PLAY_GAME = "PlayGame";
    public static final String TYPE_TAKE_CARD = "TakeCard";
    public static final String TYPE_DELEGATE_SERVER = "DelegateServer";
    public static final String TYPE_PLAYERS_NAME_PRESENT = "PlayersNamePresent";
    public static final String TYPE_PAUSE = "Pause";
    public static final String TYPE_RESULTS_PRESIDENT = "ResultsPresident";
    public static final String TYPE_RESULTS_TAROT = "ResultsTarot";
    public static final String TYPE_RESULTS_BELOTE = "ResultsBelote";
    public static final String TYPE_DEALT_HAND_TAROT = "DealtHandTarot";
    public static final String TYPE_ALLOW_BIDDING_BELOTE = "AllowBiddingBelote";
    public static final String TYPE_ALLOW_PLAYING_BELOTE = "AllowPlayingBelote";
    public static final String TYPE_ALLOW_DISCARDING = "AllowDiscarding";
    public static final String TYPE_ALLOW_PLAYING_PRESIDENT = "AllowPlayingPresident";
    public static final String TYPE_ALLOW_BIDDING_TAROT = "AllowBiddingTarot";
    public static final String TYPE_ALLOW_PLAYING_TAROT = "AllowPlayingTarot";
    public static final String TYPE_ERROR_BIDDING_BELOTE = "ErrorBiddingBelote";
    public static final String TYPE_ERROR_PLAYING_BELOTE = "ErrorPlayingBelote";
    public static final String TYPE_ERROR_PLAYING_PRESIDENT = "ErrorPlayingPresident";
    public static final String TYPE_ERROR_BIDDING = "ErrorBidding";
    public static final String TYPE_ERROR_DISCARDING = "ErrorDiscarding";
    public static final String TYPE_ERROR_HANDFUL = "ErrorHandful";
    public static final String TYPE_ERROR_PLAYING = "ErrorPlaying";
    public static final String TYPE_CALLABLE_CARDS = "CallableCards";
    public static final String TYPE_DOG = "Dog";
    public static final String TYPE_DISCARDED_TRUMPS = "DiscardedTrumps";
    public static final String TYPE_TRICKS_HANDS_PRESIDENT = "TricksHandsPresident";
    public static final String TYPE_TRICKS_HANDS_TAROT = "TricksHandsTarot";
    public static final String TYPE_TRICKS_HANDS_BELOTE = "TricksHandsBelote";
    public static final String TYPE_TEAMS_PLAYERS = "TeamsPlayers";
    public static final String TYPE_DISPLAY_SLAM_BUTTON = "DisplaySlamButton";
    public static final String TYPE_RECEIVED_GIVEN_CARDS = "ReceivedGivenCards";
    public static final String TYPE_DEALT_HAND_PRESIDENT = "DealtHandPresident";
    public static final String TYPE_DEALT_HAND_BELOTE = "DealtHandBelote";
    public static final String TYPE_ENABLED_QUIT = "EnabledQuit";

    private static final String ATTR_FIELD = "field";
    private static final char DOT = '.';
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
    private static final String TYPE_BIDDING_BELOTE = "BiddingBelote";
    private static final String TYPE_PLAYING_CARD_BELOTE = "PlayingCardBelote";
    private static final String TYPE_COMPLETED_HAND = "CompletedHand";
    private static final String TYPE_REFRESH_HAND_BELOTE = "RefreshHandBelote";
    private static final String TYPE_REFRESH_HAND_PLAYING_BELOTE = "RefreshHandPlayingBelote";
    private static final String TYPE_REFRESHING_DONE_BELOTE = "RefreshingDoneBelote";
    private static final String TYPE_BYE = "Bye";
    private static final String TYPE_DEALT = "Dealt";
    private static final String TYPE_OK = "Ok";
    private static final String TYPE_QUIT = "Quit";
    private static final String TYPE_CHOOSEN_PLACE = "ChoosenPlace";
    private static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    private static final String TYPE_NEW_PLAYER = "NewPlayer";
    private static final String TYPE_READY = "Ready";
    private static final String TYPE_DONE_BIDDING = "DoneBidding";
    private static final String TYPE_DONE_PAUSE = "DonePause";
    private static final String TYPE_DONE_PLAYING = "DonePlaying";
    private static final String TYPE_SELECT_TEAMS = "SelectTeams";
    private static final String TYPE_SELECT_TRICKS_HANDS = "SelectTricksHands";
    private static final String TYPE_DISCARDED_CARDS = "DiscardedCards";
    private static final String TYPE_PLAYING_CARD_PRESIDENT = "PlayingCardPresident";
    private static final String TYPE_REFRESHED_HAND_PRESIDENT = "RefreshedHandPresident";
    private static final String TYPE_REFRESH_HAND_PLAYING_PRESIDENT = "RefreshHandPlayingPresident";
    private static final String TYPE_REFRESHING_DONE_PRESIDENT = "RefreshingDonePresident";
    private static final String TYPE_BIDDING_SLAM_AFTER = "BiddingSlamAfter";
    private static final String TYPE_BIDDING_TAROT = "BiddingTarot";
    private static final String TYPE_CALLED_CARDS = "CalledCards";
    private static final String TYPE_DISCARDED_CARD = "DiscardedCard";
    private static final String TYPE_PLAYING_CARD_TAROT = "PlayingCardTarot";
    private static final String TYPE_VALIDATE_DOG = "ValidateDog";
    private static final String TYPE_CALLED_CARD_KNOWN = "CalledCardKnown";
    private static final String TYPE_DONE_DISPLAY_SLAM = "DoneDisplaySlam";
    private static final String TYPE_REFRESH_HAND = "RefreshHand";
    private static final String TYPE_REFRESHING_DONE = "RefreshingDone";
    private static final String TYPE_SEEN_DISCARDED_TRUMPS = "SeenDiscardedTrumps";
    private static final String TYPE_SHOW_DOG = "ShowDog";
    private static final String TYPE_CALLABLE_CARDS_DISCARD = "CallableCardsDiscard";

    public static Document getDoc(String _input) {
        Document doc_ = DocumentBuilder.parseSax(_input);
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = tagName(elt_);
        if (StringUtil.quickEq(tagName_, TYPE_RESULTS_BELOTE) || StringUtil.quickEq(tagName_, TYPE_TRICKS_HANDS_BELOTE) || StringUtil.quickEq(tagName_, TYPE_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_PLAYING_CARD_BELOTE) || StringUtil.quickEq(tagName_, TYPE_COMPLETED_HAND) || StringUtil.quickEq(tagName_, TYPE_DEALT_HAND_BELOTE) || StringUtil.quickEq(tagName_, TYPE_REFRESH_HAND_BELOTE) || StringUtil.quickEq(tagName_, TYPE_ERROR_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_ERROR_PLAYING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_REFRESH_HAND_PLAYING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_REFRESHING_DONE_BELOTE) || StringUtil.quickEq(tagName_, TYPE_ALLOW_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_ALLOW_PLAYING_BELOTE) || StringUtil.quickEq(tagName_, TYPE_BYE) || StringUtil.quickEq(tagName_, TYPE_DEALT) || StringUtil.quickEq(tagName_, TYPE_DELEGATE_SERVER) || StringUtil.quickEq(tagName_, TYPE_OK) || StringUtil.quickEq(tagName_, TYPE_TAKE_CARD) || StringUtil.quickEq(tagName_, TYPE_DISPLAY_SLAM_BUTTON) || StringUtil.quickEq(tagName_, TYPE_PLAY_GAME) || StringUtil.quickEq(tagName_, TYPE_PAUSE) || StringUtil.quickEq(tagName_, TYPE_QUIT) || StringUtil.quickEq(tagName_, TYPE_CHOOSEN_PLACE) || StringUtil.quickEq(tagName_, TYPE_INDEX_OF_ARRIVING) || StringUtil.quickEq(tagName_, TYPE_NEW_PLAYER) || StringUtil.quickEq(tagName_, TYPE_PLAYERS_NAME_PRESENT) || StringUtil.quickEq(tagName_, TYPE_READY) || StringUtil.quickEq(tagName_, TYPE_DONE_BIDDING) || StringUtil.quickEq(tagName_, TYPE_DONE_PAUSE) || StringUtil.quickEq(tagName_, TYPE_DONE_PLAYING) || StringUtil.quickEq(tagName_, TYPE_SELECT_TEAMS) || StringUtil.quickEq(tagName_, TYPE_SELECT_TRICKS_HANDS) || StringUtil.quickEq(tagName_, TYPE_TEAMS_PLAYERS) || StringUtil.quickEq(tagName_, TYPE_DISCARDED_CARDS) || StringUtil.quickEq(tagName_, TYPE_PLAYING_CARD_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_DEALT_HAND_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_RECEIVED_GIVEN_CARDS) || StringUtil.quickEq(tagName_, TYPE_REFRESHED_HAND_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_ERROR_PLAYING_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_REFRESH_HAND_PLAYING_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_REFRESHING_DONE_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_ALLOW_DISCARDING) || StringUtil.quickEq(tagName_, TYPE_ALLOW_PLAYING_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_DOG) || StringUtil.quickEq(tagName_, TYPE_BIDDING_SLAM_AFTER) || StringUtil.quickEq(tagName_, TYPE_BIDDING_TAROT) || StringUtil.quickEq(tagName_, TYPE_CALLED_CARDS) || StringUtil.quickEq(tagName_, TYPE_DISCARDED_CARD) || StringUtil.quickEq(tagName_, TYPE_DISCARDED_TRUMPS) || StringUtil.quickEq(tagName_, TYPE_PLAYING_CARD_TAROT) || StringUtil.quickEq(tagName_, TYPE_VALIDATE_DOG) || StringUtil.quickEq(tagName_, TYPE_DEALT_HAND_TAROT) || StringUtil.quickEq(tagName_, TYPE_ERROR_BIDDING) || StringUtil.quickEq(tagName_, TYPE_ERROR_DISCARDING) || StringUtil.quickEq(tagName_, TYPE_ERROR_HANDFUL) || StringUtil.quickEq(tagName_, TYPE_ERROR_PLAYING) || StringUtil.quickEq(tagName_, TYPE_CALLED_CARD_KNOWN) || StringUtil.quickEq(tagName_, TYPE_DONE_DISPLAY_SLAM) || StringUtil.quickEq(tagName_, TYPE_REFRESH_HAND) || StringUtil.quickEq(tagName_, TYPE_REFRESHING_DONE) || StringUtil.quickEq(tagName_, TYPE_SEEN_DISCARDED_TRUMPS) || StringUtil.quickEq(tagName_, TYPE_SHOW_DOG) || StringUtil.quickEq(tagName_, TYPE_ALLOW_BIDDING_TAROT) || StringUtil.quickEq(tagName_, TYPE_ALLOW_PLAYING_TAROT) || StringUtil.quickEq(tagName_, TYPE_CALLABLE_CARDS) || StringUtil.quickEq(tagName_, TYPE_CALLABLE_CARDS_DISCARD) || StringUtil.quickEq(tagName_, TYPE_RESULTS_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_TRICKS_HANDS_PRESIDENT) || StringUtil.quickEq(tagName_, TYPE_RESULTS_TAROT) || StringUtil.quickEq(tagName_, TYPE_TRICKS_HANDS_TAROT)) {
            return doc_;
        }
        return null;
    }
    public static Exiting getExiting(Document _input) {
        Element elt_ = _input.getDocumentElement();
        String tagName_ = tagName(elt_);
        if (StringUtil.quickEq(tagName_, TYPE_BYE)) {
            return getBye(elt_);
        }
        return null;
    }

    public static ResultsBelote resultsBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsBelote object_ = new ResultsBelote();
        for (Element c: childElements_) {
            DocumentReaderBeloteUtil.getResultsBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    public static ResultsPresident resultsPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsPresident object_ = new ResultsPresident();
        for (Element c: childElements_) {
            DocumentReaderPresidentUtil.getResultsPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    public static ResultsTarot resultsTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsTarot object_ = new ResultsTarot();
        for (Element c: childElements_) {
            DocumentReaderTarotUtil.getResultsTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    public static String tagName(Element _elt) {
        String tagName_ = _elt.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT) + 1);
        return tagName_;
    }

    private static void getBiddingBelote(BiddingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BID_BELOTE)) {
            _object.setBidBelote(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getPlayingCardBelote(PlayingCardBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandBelote getDealtHandBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandBelote object_ = new DealtHandBelote();
        for (Element c: childElements_) {
            getDealtHandBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandBelote(DealtHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECK)) {
            _object.setDeck(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(DocumentReaderBeloteUtil.getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REP)) {
            _object.setRep(DocumentReaderBeloteUtil.getDealingBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POINTS)) {
            _object.setPoints(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static void getRefreshHandBelote(RefreshHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_REFRESHED_HAND)) {
            _object.setRefreshedHand(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static ErrorBiddingBelote getErrorBiddingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorBiddingBelote object_ = new ErrorBiddingBelote();
        for (Element c: childElements_) {
            getErrorBiddingBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorBiddingBelote(ErrorBiddingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
            return;
        }
    }

    public static ErrorPlayingBelote getErrorPlayingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorPlayingBelote object_ = new ErrorPlayingBelote();
        for (Element c: childElements_) {
            getErrorPlayingBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorPlayingBelote(ErrorPlayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REASON)) {
            _object.setReason(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getRefreshHandPlayingBelote(RefreshHandPlayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDoneBelote(RefreshingDoneBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static AllowBiddingBelote getAllowBiddingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowBiddingBelote object_ = new AllowBiddingBelote();
        for (Element c: childElements_) {
            getAllowBiddingBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowBiddingBelote(AllowBiddingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BIDS)) {
            _object.setBids(DocumentReaderBeloteUtil.getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POINTS)) {
            _object.setPoints(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static AllowPlayingBelote getAllowPlayingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingBelote object_ = new AllowPlayingBelote();
        for (Element c: childElements_) {
            getAllowPlayingBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingBelote(AllowPlayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_FIRST_ROUND_PLAYING)) {
            _object.setFirstRoundPlaying(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARATION)) {
            _object.setDeclaration(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POSSIBLE_BELOTE_REBELOTE)) {
            _object.setPossibleBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_BELOTE_REBELOTE)) {
            _object.setAllowedBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    private static Exiting getBye(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Exiting object_ = new Exiting();
        for (Element c: childElements_) {
            getBye(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getBye(Exiting _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_FORCED)) {
            _object.setForced(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_BUSY)) {
            _object.setBusy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static DelegateServer getDelegateServer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DelegateServer object_ = new DelegateServer();
        for (Element c: childElements_) {
            getDelegateServer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDelegateServer(DelegateServer _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_GAMES)) {
            _object.setGames(DocumentReaderCardsUnionUtil.getGames(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NICKNAMES)) {
            _object.setNicknames(DocumentReaderCoreUtil.getMapIntegerString(_element));
            return;
        }
    }

    public static PlayerActionGame getPlayerActionGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = tagName(_element);
        if (StringUtil.quickEq(tagName_,TYPE_BIDDING_BELOTE)) {
            BiddingBelote object_ = new BiddingBelote();
            for (Element c: childElements_) {
                getBiddingBelote(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_PLAYING_CARD_BELOTE)) {
            PlayingCardBelote object_ = new PlayingCardBelote();
            for (Element c: childElements_) {
                getPlayingCardBelote(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_COMPLETED_HAND)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.COMPLETED_HAND);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESH_HAND_PLAYING_BELOTE)) {
            RefreshHandPlayingBelote object_ = new RefreshHandPlayingBelote();
            for (Element c: childElements_) {
                getRefreshHandPlayingBelote(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESHING_DONE_BELOTE)) {
            RefreshingDoneBelote object_ = new RefreshingDoneBelote();
            for (Element c: childElements_) {
                getRefreshingDoneBelote(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESH_HAND_BELOTE)) {
            RefreshHandBelote object_ = new RefreshHandBelote();
            for (Element c: childElements_) {
                getRefreshHandBelote(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DEALT)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DEALT);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DONE_BIDDING)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_BIDDING);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DONE_PAUSE)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DONE_PLAYING)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_OK)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.OK);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_QUIT)) {
            Quit object_ = new Quit();
            for (Element c: childElements_) {
                getQuit(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_SELECT_TEAMS)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_SELECT_TRICKS_HANDS)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DISCARDED_CARDS)) {
            DiscardedCards object_ = new DiscardedCards();
            for (Element c: childElements_) {
                getDiscardedCards(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_PLAYING_CARD_PRESIDENT)) {
            PlayingCardPresident object_ = new PlayingCardPresident();
            for (Element c: childElements_) {
                getPlayingCardPresident(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESH_HAND_PLAYING_PRESIDENT)) {
            RefreshHandPlayingPresident object_ = new RefreshHandPlayingPresident();
            for (Element c: childElements_) {
                getRefreshHandPlayingPresident(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESHING_DONE_PRESIDENT)) {
            RefreshingDonePresident object_ = new RefreshingDonePresident();
            for (Element c: childElements_) {
                getRefreshingDonePresident(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESHED_HAND_PRESIDENT)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.REFHESHED_HAND_PRESIDENT);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_BIDDING_SLAM_AFTER)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SLAM);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_BIDDING_TAROT)) {
            BiddingTarot object_ = new BiddingTarot();
            for (Element c: childElements_) {
                getBiddingTarot(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_CALLED_CARDS)) {
            CalledCards object_ = new CalledCards();
            for (Element c: childElements_) {
                getCalledCards(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DISCARDED_CARD)) {
            DiscardedCard object_ = new DiscardedCard();
            for (Element c: childElements_) {
                getDiscardedCard(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_PLAYING_CARD_TAROT)) {
            PlayingCardTarot object_ = new PlayingCardTarot();
            for (Element c: childElements_) {
                getPlayingCardTarot(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_VALIDATE_DOG)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.VALIDATE_DOG);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_CALLED_CARD_KNOWN)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.CALLED_CARD_KNOWN);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_DONE_DISPLAY_SLAM)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_DISPLAY_SLAM);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESH_HAND)) {
            RefreshHand object_ = new RefreshHand();
            for (Element c: childElements_) {
                getRefreshHand(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_REFRESHING_DONE)) {
            RefreshingDone object_ = new RefreshingDone();
            for (Element c: childElements_) {
                getRefreshingDone(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_SEEN_DISCARDED_TRUMPS)) {
            SeenDiscardedTrumps object_ = new SeenDiscardedTrumps();
            for (Element c: childElements_) {
                getSeenDiscardedTrumps(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_SHOW_DOG)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionGame(PlayerActionGame _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LOCALE)) {
            _object.setLocale(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getQuit(Quit _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getChoosenPlace(ChoosenPlace _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLACES_PLAYERS)) {
            _object.setPlacesPlayers(DocumentReaderCoreUtil.getMapIntegerByte(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    private static void getNewPlayer(NewPlayerCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ARRIVING)) {
            _object.setArriving(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LANGUAGE)) {
            _object.setLanguage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ACCEPTABLE)) {
            _object.setAcceptable(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static PlayerActionBeforeGameCards getPlayerActionBeforeGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = tagName(_element);
        if (StringUtil.quickEq(tagName_,TYPE_CHOOSEN_PLACE)) {
            ChoosenPlace object_ = new ChoosenPlace();
            for (Element c: childElements_) {
                getChoosenPlace(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_INDEX_OF_ARRIVING)) {
            IndexOfArrivingCards object_ = new IndexOfArrivingCards();
            for (Element c: childElements_) {
                getPlayerActionBeforeGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_NEW_PLAYER)) {
            NewPlayerCards object_ = new NewPlayerCards();
            for (Element c: childElements_) {
                getNewPlayer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_READY)) {
            Ready object_ = new Ready();
            for (Element c: childElements_) {
                getReady(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionBeforeGame(PlayerActionBeforeGameCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static PlayersNamePresent getPlayersNamePresent(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PlayersNamePresent object_ = new PlayersNamePresent();
        for (Element c: childElements_) {
            getPlayersNamePresent(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPlayersNamePresent(PlayersNamePresent _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PSEUDOS)) {
            _object.setPseudos(DocumentReaderCoreUtil.getMapIntegerString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLACES_PLAYERS)) {
            _object.setPlacesPlayers(DocumentReaderCoreUtil.getMapIntegerByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_READY_PLAYERS)) {
            _object.setReadyPlayers(DocumentReaderCoreUtil.getMapIntegerBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_PLAYERS)) {
            _object.setNbPlayers(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_FIRST)) {
            _object.setFirst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RULES_TAROT)) {
            _object.setRulesTarot(DocumentReaderTarotUtil.getRulesTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RULES_PRESIDENT)) {
            _object.setRulesPresident(DocumentReaderPresidentUtil.getRulesPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RULES_BELOTE)) {
            _object.setRulesBelote(DocumentReaderBeloteUtil.getRulesBelote(_element));
            return;
        }
    }

    private static void getReady(Ready _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_READY)) {
            _object.setReady(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static TeamsPlayers getTeamsPlayers(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TeamsPlayers object_ = new TeamsPlayers();
        for (Element c: childElements_) {
            getTeamsPlayers(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTeamsPlayers(TeamsPlayers _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_TEAMS)) {
            _object.setTeams(DocumentReaderCoreUtil.getListListByte(_element));
            return;
        }
    }

    private static void getDiscardedCards(DiscardedCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DISCARDED)) {
            _object.setDiscarded(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getPlayingCardPresident(PlayingCardPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderPresidentUtil.getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandPresident getDealtHandPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandPresident object_ = new DealtHandPresident();
        for (Element c: childElements_) {
            getDealtHandPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandPresident(DealtHandPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_MAX_CARDS)) {
            _object.setMaxCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderPresidentUtil.getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    public static ReceivedGivenCards getReceivedGivenCards(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ReceivedGivenCards object_ = new ReceivedGivenCards();
        for (Element c: childElements_) {
            getReceivedGivenCards(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getReceivedGivenCards(ReceivedGivenCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_RECEIVED)) {
            _object.setReceived(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_GIVEN)) {
            _object.setGiven(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NEW_HAND)) {
            _object.setNewHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
    }

    public static ErrorPlayingPresident getErrorPlayingPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorPlayingPresident object_ = new ErrorPlayingPresident();
        for (Element c: childElements_) {
            getErrorPlayingPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorPlayingPresident(ErrorPlayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REASON)) {
            _object.setReason(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PASS_ISSUE)) {
            _object.setPassIssue(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static void getRefreshHandPlayingPresident(RefreshHandPlayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderPresidentUtil.getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDonePresident(RefreshingDonePresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderPresidentUtil.getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static AllowDiscarding getAllowDiscarding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowDiscarding object_ = new AllowDiscarding();
        for (Element c: childElements_) {
            getAllowDiscarding(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowDiscarding(AllowDiscarding _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_RECEIVED_CARDS)) {
            _object.setReceivedCards(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
    }

    public static AllowPlayingPresident getAllowPlayingPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingPresident object_ = new AllowPlayingPresident();
        for (Element c: childElements_) {
            getAllowPlayingPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingPresident(AllowPlayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_ENABLED_PASS)) {
            _object.setEnabledPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderPresidentUtil.getPlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static Dog getDog(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Dog object_ = new Dog();
        for (Element c: childElements_) {
            getDog(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDog(Dog _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DOG)) {
            _object.setDog(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER)) {
            _object.setTaker(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HUMAN_TAKER)) {
            _object.setHumanTaker(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CALL_AFTER)) {
            _object.setCallAfter(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static void getBiddingTarot(BiddingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(DocumentReaderTarotUtil.getBidTarot(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getCalledCards(CalledCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CALLED_CARDS)) {
            _object.setCalledCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DISCARDING)) {
            _object.setDiscarding(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getDiscardedCard(DiscardedCard _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_IN_HAND)) {
            _object.setInHand(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DiscardedTrumps getDiscardedTrumps(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DiscardedTrumps object_ = new DiscardedTrumps();
        for (Element c: childElements_) {
            getDiscardedTrumps(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDiscardedTrumps(DiscardedTrumps _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_TRUMPS)) {
            _object.setTrumps(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING_SLAM)) {
            _object.setDeclaringSlam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static void getPlayingCardTarot(PlayingCardTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_EXCLUDED_TRUMPS)) {
            _object.setExcludedTrumps(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandTarot getDealtHandTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandTarot object_ = new DealtHandTarot();
        for (Element c: childElements_) {
            getDealtHandTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandTarot(DealtHandTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DOG)) {
            _object.setDog(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(DocumentReaderTarotUtil.getListBidTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REP)) {
            _object.setRep(DocumentReaderTarotUtil.getDealingTarot(_element));
            return;
        }
    }

    public static ErrorBidding getErrorBidding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorBidding object_ = new ErrorBidding();
        for (Element c: childElements_) {
            getErrorBidding(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorBidding(ErrorBidding _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(DocumentReaderTarotUtil.getBidTarot(_element));
            return;
        }
    }

    public static ErrorDiscarding getErrorDiscarding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorDiscarding object_ = new ErrorDiscarding();
        for (Element c: childElements_) {
            getErrorDiscarding(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorDiscarding(ErrorDiscarding _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ERROR_MESSAGE)) {
            _object.setErrorMessage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    public static ErrorHandful getErrorHandful(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorHandful object_ = new ErrorHandful();
        for (Element c: childElements_) {
            getErrorHandful(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorHandful(ErrorHandful _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ERROR)) {
            _object.setError(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    public static ErrorPlaying getErrorPlaying(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ErrorPlaying object_ = new ErrorPlaying();
        for (Element c: childElements_) {
            getErrorPlaying(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getErrorPlaying(ErrorPlaying _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REASON)) {
            _object.setReason(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getRefreshHand(RefreshHand _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDone(RefreshingDone _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getSeenDiscardedTrumps(SeenDiscardedTrumps _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARING_SLAM)) {
            _object.setDeclaringSlam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static AllowBiddingTarot getAllowBiddingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowBiddingTarot object_ = new AllowBiddingTarot();
        for (Element c: childElements_) {
            getAllowBiddingTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowBiddingTarot(AllowBiddingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BIDS)) {
            _object.setBids(DocumentReaderTarotUtil.getListBidTarot(_element));
            return;
        }
    }

    public static AllowPlayingTarot getAllowPlayingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingTarot object_ = new AllowPlayingTarot();
        for (Element c: childElements_) {
            getAllowPlayingTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingTarot(AllowPlayingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_FIRST_ROUND_PLAYING)) {
            _object.setFirstRoundPlaying(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_HANDFULS)) {
            _object.setAllowedHandfuls(DocumentReaderTarotUtil.getListHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_REQUIRED_TRUMPS)) {
            _object.setRequiredTrumps(DocumentReaderTarotUtil.getMapHandfulsInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_MISERES)) {
            _object.setAllowedMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    public static CallableCards getCallableCards(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CallableCards object_ = new CallableCards();
        for (Element c: childElements_) {
            getCallableCards(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getCallableCards(CallableCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CALLABLE_CARDS)) {
            _object.setCallableCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DISCARDING)) {
            _object.setDiscarding(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

}
