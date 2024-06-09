package cards.network.sml;
import cards.belote.TricksHandsBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.DiscardedCardBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.*;
import cards.network.common.before.*;
import cards.gui.*;
import cards.network.president.actions.DiscardedCardsPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.*;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.DiscardPhaseTarot;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.unlock.*;
import cards.president.TricksHandsPresident;
import cards.president.enumerations.Playing;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.TricksHandsTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.ByteMap;
import code.util.EntryCust;

public final class DocumentWriterCardsMultiUtil {

    public static final String FIELD_ACCEPTABLE = "0";
    public static final String FIELD_ALLOWED_BELOTE_REBELOTE = "1";
    public static final String FIELD_ALLOWED_BIDS = "2";
    public static final String FIELD_ALLOWED_HANDFULS = "3";
    public static final String FIELD_ALLOWED_MISERES = "4";
    public static final String FIELD_ARRIVING = "5";
    public static final String FIELD_BID = "6";
    public static final String FIELD_BIDS = "7";
    public static final String FIELD_BID_BELOTE = "8";
    public static final String FIELD_BUSY = "9";
    public static final String FIELD_CALLABLE_CARDS = "10";
    public static final String FIELD_CALLED_CARD = "11";
    public static final String FIELD_CALLED_CARDS = "12";
    public static final String FIELD_CALL_AFTER = "13";
    public static final String FIELD_CARD = "14";
    public static final String FIELD_CARDS = "15";
    public static final String FIELD_CHOOSEN_HANDFUL = "16";
    public static final String FIELD_CLOSING = "17";
    public static final String FIELD_DEALER = "18";
    public static final String FIELD_DECK = "19";
    public static final String FIELD_DECLARATION = "20";
    public static final String FIELD_DECLARE = "21";
    public static final String FIELD_DECLARING = "22";
    public static final String FIELD_DECLARING_BELOTE_REBELOTE = "23";
    public static final String FIELD_DECLARING_SLAM = "24";
    public static final String FIELD_DISCARDED = "25";
    public static final String FIELD_DISCARDING = "26";
    public static final String FIELD_DOG = "27";
    public static final String FIELD_ENABLED_PASS = "28";
    public static final String FIELD_ERROR = "29";
    public static final String FIELD_ERROR_MESSAGE = "30";
    public static final String FIELD_EXCLUDED_TRUMPS = "31";
    public static final String FIELD_FIRST = "32";
    public static final String FIELD_FIRST_ROUND_PLAYING = "33";
    public static final String FIELD_FORCED = "34";
    public static final String FIELD_GAMES = "35";
    public static final String FIELD_GIVEN = "36";
    public static final String FIELD_HANDFUL = "37";
    public static final String FIELD_HUMAN_TAKER = "38";
    public static final String FIELD_INDEX = "39";
    public static final String FIELD_IN_HAND = "40";
    public static final String FIELD_LANGUAGE = "41";
    public static final String FIELD_LOCALE = "42";
    public static final String FIELD_MAX_CARDS = "43";
    public static final String FIELD_MISERES = "44";
    public static final String FIELD_NB_PLAYERS = "45";
    public static final String FIELD_NEW_HAND = "46";
    public static final String FIELD_NEXT_PLAYER = "47";
    public static final String FIELD_NICKNAMES = "48";
    public static final String FIELD_PASS = "49";
    public static final String FIELD_PASS_ISSUE = "50";
    public static final String FIELD_PLACE = "51";
    public static final String FIELD_PLACES_PLAYERS = "52";
    public static final String FIELD_PLAYED_CARD = "53";
    public static final String FIELD_PLAYED_HAND = "54";
    public static final String FIELD_POINTS = "55";
    public static final String FIELD_POSSIBLE_BELOTE_REBELOTE = "56";
    public static final String FIELD_PSEUDO = "57";
    public static final String FIELD_PSEUDOS = "58";
    public static final String FIELD_READY = "59";
    public static final String FIELD_READY_PLAYERS = "60";
    public static final String FIELD_REASON = "61";
    public static final String FIELD_RECEIVED = "62";
    public static final String FIELD_RECEIVED_CARDS = "63";
    public static final String FIELD_REFRESHED_HAND = "64";
    public static final String FIELD_REP = "65";
    public static final String FIELD_REQUIRED_TRUMPS = "66";
    public static final String FIELD_REVERSED = "67";
    public static final String FIELD_RULES_BELOTE = "68";
    public static final String FIELD_RULES_PRESIDENT = "69";
    public static final String FIELD_RULES_TAROT = "70";
    public static final String FIELD_SERVER = "71";
    public static final String FIELD_STATUS = "72";
    public static final String FIELD_TAKER = "73";
    public static final String FIELD_TAKER_INDEX = "74";
    public static final String FIELD_TEAMS = "75";
    public static final String FIELD_TRUMPS = "76";
    public static final String TYPE_ALLOW_BIDDING_BELOTE = "AllowBiddingBelote";
    public static final String TYPE_ALLOW_BIDDING_TAROT = "AllowBiddingTarot";
    public static final String TYPE_ALLOW_DISCARDING = "AllowDiscarding";
    public static final String TYPE_ALLOW_PLAYING_BELOTE = "AllowPlayingBelote";
    public static final String TYPE_ALLOW_PLAYING_PRESIDENT = "AllowPlayingPresident";
    public static final String TYPE_ALLOW_PLAYING_TAROT = "AllowPlayingTarot";
    public static final String TYPE_BIDDING_BELOTE = "BiddingBelote";
    public static final String TYPE_BIDDING_SLAM_AFTER = "BiddingSlamAfter";
    public static final String TYPE_BIDDING_TAROT = "BiddingTarot";
    public static final String TYPE_BYE = "Bye";
    public static final String TYPE_CALLABLE_CARDS = "CallableCards";
    public static final String TYPE_CALLED_CARD_KNOWN = "CalledCardKnown";
    public static final String TYPE_CALLED_CARDS = "CalledCards";
    public static final String TYPE_CHOOSEN_PLACE = "ChoosenPlace";
    public static final String TYPE_COMPLETED_HAND = "CompletedHand";
    public static final String TYPE_DEALT = "Dealt";
    public static final String TYPE_DEALT_HAND_BELOTE = "DealtHandBelote";
    public static final String TYPE_DEALT_HAND_PRESIDENT = "DealtHandPresident";
    public static final String TYPE_DEALT_HAND_TAROT = "DealtHandTarot";
    public static final String TYPE_DELEGATE_SERVER = "DelegateServer";
    public static final String TYPE_DISCARDED_CARD = "DiscardedCard";
    public static final String TYPE_DISCARDED_CARDS = "DiscardedCards";
//    public static final String TYPE_DISCARDED_TRUMPS = "DiscardedTrumps";
    public static final String TYPE_DOG = "Dog";
    public static final String TYPE_DONE_BIDDING = "DoneBidding";
    public static final String TYPE_DONE_DISPLAY_SLAM = "DoneDisplaySlam";
    public static final String TYPE_DONE_PAUSE = "DonePause";
    public static final String TYPE_DONE_PLAYING = "DonePlaying";
//    public static final String TYPE_ERROR_BIDDING = "ErrorBidding";
    public static final String TYPE_ERROR_BIDDING_BELOTE = "ErrorBiddingBelote";
    public static final String TYPE_ERROR_DISCARDING = "ErrorDiscarding";
    public static final String TYPE_ERROR_HANDFUL = "ErrorHandful";
    public static final String TYPE_ERROR_PLAYING = "ErrorPlaying";
    public static final String TYPE_ERROR_PLAYING_BELOTE = "ErrorPlayingBelote";
    public static final String TYPE_ERROR_PLAYING_PRESIDENT = "ErrorPlayingPresident";
    public static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    public static final String TYPE_NEW_PLAYER = "NewPlayer";
    public static final String TYPE_OLD_PLAYER = "OldPlayer";
    public static final String TYPE_OK = "Ok";
    public static final String TYPE_TAKE_CARD = "TakeCard";
    public static final String TYPE_DISPLAY_SLAM_BUTTON = "DisplaySlamButton";
    public static final String TYPE_PLAY_GAME = "PlayGame";
    public static final String TYPE_PAUSE = "Pause";
    public static final String TYPE_PLAYERS_NAME_PRESENT = "PlayersNamePresent";
    public static final String TYPE_PLAYING_CARD_BELOTE = "PlayingCardBelote";
    public static final String TYPE_PLAYING_CARD_PRESIDENT = "PlayingCardPresident";
    public static final String TYPE_PLAYING_CARD_TAROT = "PlayingCardTarot";
    public static final String TYPE_QUIT = "Quit";
    public static final String TYPE_READY = "Ready";
    public static final String TYPE_RECEIVED_GIVEN_CARDS = "ReceivedGivenCards";
    public static final String TYPE_REFRESH_HAND = "RefreshHand";
    public static final String TYPE_REFRESH_HAND_BELOTE = "RefreshHandBelote";
    public static final String TYPE_REFRESH_HAND_PLAYING_BELOTE = "RefreshHandPlayingBelote";
    public static final String TYPE_REFRESH_HAND_PLAYING_PRESIDENT = "RefreshHandPlayingPresident";
    public static final String TYPE_REFRESHED_HAND_PRESIDENT = "RefreshedHandPresident";
    public static final String TYPE_REFRESHING_DONE = "RefreshingDone";
    public static final String TYPE_REFRESHING_DONE_BELOTE = "RefreshingDoneBelote";
    public static final String TYPE_REFRESHING_DONE_PRESIDENT = "RefreshingDonePresident";
//    public static final String TYPE_SEEN_DISCARDED_TRUMPS = "SeenDiscardedTrumps";
    public static final String TYPE_SELECT_TEAMS = "SelectTeams";
    public static final String TYPE_SELECT_TRICKS_HANDS = "SelectTricksHands";
    public static final String TYPE_SHOW_DOG = "ShowDog";
    public static final String TYPE_TEAMS_PLAYERS = "TeamsPlayers";
    public static final String TYPE_VALIDATE_DOG = "ValidateDog";
    public static final String TYPE_PLAYER_ACTION_BEFORE_GAME = "PlayerActionBeforeGame";
    public static final String TYPE_PLAYER_ACTION_GAME = "PlayerActionGame";
    private DocumentWriterCardsMultiUtil() {
    }

//    public static String takeCard() {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        Element element_ = doc_.createElement(TYPE_TAKE_CARD);
//        doc_.appendChild(element_);
//        return doc_.export();
//    }

    public static String displaySlamButton() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_DISPLAY_SLAM_BUTTON);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static String playGame() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_PLAY_GAME);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static String pause() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_PAUSE);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static String tricksHandsTarot(TricksHandsTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(DocumentWriterTarotUtil.setTricksHandsTarot(_object, "", doc_));
        return doc_.export();
    }

    public static String tricksHandsPresident(TricksHandsPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(DocumentWriterPresidentUtil.setTricksHandsPresident(_object, "", doc_));
        return doc_.export();
    }

    public static String callableCards(CallableCards _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setCallableCards(_object, "", doc_));
        return doc_.export();
    }

    public static String allowPlayingTarot(AllowPlayingTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowPlayingTarot(_object, "", doc_));
        return doc_.export();
    }

    public static String allowBiddingTarot(AllowBiddingTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowBiddingTarot(_object, "", doc_));
        return doc_.export();
    }

//    public static String errorPlaying(ErrorPlaying _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorPlaying(_object, "", doc_));
//        return doc_.export();
//    }

//    public static String errorHandful(ErrorHandful _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorHandful(_object, "", doc_));
//        return doc_.export();
//    }

//    public static String errorDiscarding(ErrorDiscarding _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorDiscarding(_object, "", doc_));
//        return doc_.export();
//    }

//    public static String errorBidding(ErrorBidding _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorBidding(_object, "", doc_));
//        return doc_.export();
//    }

    public static String dealtHandTarot(DealtHandTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDealtHandTarot(_object, "", doc_));
        return doc_.export();
    }

//    public static String discardedTrumps(DiscardedTrumps _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setDiscardedTrumps(_object, "", doc_));
//        return doc_.export();
//    }

    public static String dog(DiscardPhaseTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDog(_object, "", doc_));
        return doc_.export();
    }

    public static String discard(DiscardPhaseBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDiscard(_object, "", doc_));
        return doc_.export();
    }
    public static String allowPlayingPresident(AllowPlayingPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowPlayingPresident(_object, "", doc_));
        return doc_.export();
    }

    public static String allowDiscarding(AllowDiscarding _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowDiscarding(_object, "", doc_));
        return doc_.export();
    }

//    public static String errorPlayingPresident(ErrorPlayingPresident _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorPlayingPresident(_object, "", doc_));
//        return doc_.export();
//    }

    public static String receivedGivenCards(ReceivedGivenCards _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setReceivedGivenCards(_object, "", doc_));
        return doc_.export();
    }

    public static String dealtHandPresident(DealtHandPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDealtHandPresident(_object, "", doc_));
        return doc_.export();
    }

//    public static String playersNamePresent(PlayersNamePresent _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setPlayersNamePresent(_object, "", doc_));
//        return doc_.export();
//    }

//    public static String delegateServer(DelegateServer _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setDelegateServer(_object, "", doc_));
//        return doc_.export();
//    }

    public static String bye(Exiting _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setBye(_object, "", doc_));
        return doc_.export();
    }

    public static String allowPlayingBelote(AllowPlayingBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowPlayingBelote(_object, "", doc_));
        return doc_.export();
    }

    public static String allowBiddingBelote(AllowBiddingBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAllowBiddingBelote(_object, "", doc_));
        return doc_.export();
    }

//    public static String errorPlayingBelote(ErrorPlayingBelote _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorPlayingBelote(_object, "", doc_));
//        return doc_.export();
//    }

//    public static String errorBiddingBelote(ErrorBiddingBelote _object) {
//        Document doc_ = DocumentBuilder.newXmlDocument();
//        doc_.appendChild(setErrorBiddingBelote(_object, "", doc_));
//        return doc_.export();
//    }

    public static String dealtHandBelote(DealtHandBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDealtHandBelote(_object, "", doc_));
        return doc_.export();
    }

    public static String tricksHandsBelote(TricksHandsBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(DocumentWriterBeloteUtil.setTricksHandsBelote(_object, "", doc_));
        return doc_.export();
    }

    public static String playerActionGame(PlayerActionGame _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionGame(_object, "", doc_));
        return doc_.export();
    }

    public static String discardedBelote(DiscardedCardBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_DISCARDED_CARD);
        element_.setAttribute(DocumentReaderCoreUtil.VALUE,"_");
        doc_.appendChild(element_);
        setDiscardedCardBelote(_object, element_, doc_);
        return doc_.export();
    }
    public static String discardedTarot(DiscardedCardTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_DISCARDED_CARD);
        doc_.appendChild(element_);
        setDiscardedCardTarot(_object, element_, doc_);
        return doc_.export();
    }

    public static String teamsPlayers(TeamsPlayers _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setTeamsPlayers(_object, "", doc_));
        return doc_.export();
    }

    public static String playerActionBeforeGameCards(PlayerActionBeforeGameCards _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionBeforeGame(_object, "", doc_));
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
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isRefreshing(),FIELD_REASON,_document));
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

//    private static Element setErrorBiddingBelote(ErrorBiddingBelote _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_BIDDING_BELOTE);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorBiddingBelote(_object,element_,_document);
//        return element_;
//    }
//
//    private static void setErrorBiddingBelote(ErrorBiddingBelote _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterBeloteUtil.setBidBeloteSuit(_object.getBid(),FIELD_BID,_document));
//    }

//    private static Element setErrorPlayingBelote(ErrorPlayingBelote _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_PLAYING_BELOTE);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorPlayingBelote(_object,element_,_document);
//        return element_;
//    }

//    private static void setErrorPlayingBelote(ErrorPlayingBelote _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getCards(),FIELD_CARDS,_document));
//        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
//    }

//    private static void setRefreshHandPlayingBelote(RefreshHandPlayingBelote _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaring(),FIELD_DECLARING,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringBeloteRebelote(),FIELD_DECLARING_BELOTE_REBELOTE,_document));
//        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclare(),FIELD_DECLARE,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }
//
//    private static void setRefreshingDoneBelote(RefreshingDoneBelote _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaring(),FIELD_DECLARING,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringBeloteRebelote(),FIELD_DECLARING_BELOTE_REBELOTE,_document));
//        _element.appendChild(DocumentWriterBeloteUtil.setDeclareHandBelote(_object.getDeclare(),FIELD_DECLARE,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

    private static Element setAllowBiddingBelote(AllowBiddingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_BIDDING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowBiddingBelote(_object,element_,_document);
        return element_;
    }

    private static void setAllowBiddingBelote(AllowBiddingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setListBidBeloteSuit(_object.getBids(),FIELD_BIDS,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setBidBeloteSuit(_object.getBid(),FIELD_POINTS,_document));
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
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getBelReb(),FIELD_ERROR_MESSAGE,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPossibleBeloteRebelote(),FIELD_POSSIBLE_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAllowedBeloteRebelote(),FIELD_ALLOWED_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setBidBeloteSuit(_object.getCurrentBid(),FIELD_BID,_document));
    }

    private static Element setBye(Exiting _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BYE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBye(_object,element_,_document);
        return element_;
    }

    private static void setBye(Exiting _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isForced(),FIELD_FORCED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBusy(),FIELD_BUSY,_document));
    }
//
//    private static Element setDelegateServer(DelegateServer _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_DELEGATE_SERVER);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setDelegateServer(_object,element_,_document);
//        return element_;
//    }
//
//    private static void setDelegateServer(DelegateServer _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterCardsUnionUtil.setGames(_object.getGames(),FIELD_GAMES,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerString(_object.getNicknames(),FIELD_NICKNAMES,_document));
//    }

    private static Element setPlayerActionGame(PlayerActionGame _object, String _fieldName, Document _document) {
        PlayerActionGameType actionType_ = _object.getActionType();
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
        if (actionType_ == PlayerActionGameType.COMPLETED_HAND) {
            Element element_ = _document.createElement(TYPE_COMPLETED_HAND);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
//        if (_object instanceof RefreshHandPlayingBelote) {
//            Element element_ = _document.createElement(TYPE_REFRESH_HAND_PLAYING_BELOTE);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshHandPlayingBelote((RefreshHandPlayingBelote)_object,element_,_document);
//            return element_;
//        }
//        if (_object instanceof RefreshingDoneBelote) {
//            Element element_ = _document.createElement(TYPE_REFRESHING_DONE_BELOTE);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshingDoneBelote((RefreshingDoneBelote)_object,element_,_document);
//            return element_;
//        }
        if (_object instanceof RefreshHandBelote) {
            Element element_ = _document.createElement(TYPE_REFRESH_HAND_BELOTE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRefreshHandBelote((RefreshHandBelote)_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.DEALT) {
            Element element_ = _document.createElement(TYPE_DEALT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.DONE_BIDDING) {
            Element element_ = _document.createElement(TYPE_DONE_BIDDING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.DONE_PAUSE) {
            Element element_ = _document.createElement(TYPE_DONE_PAUSE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.DONE_PLAYING) {
            Element element_ = _document.createElement(TYPE_DONE_PLAYING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.OK) {
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
        if (actionType_ == PlayerActionGameType.SELECT_TEAMS) {
            Element element_ = _document.createElement(TYPE_SELECT_TEAMS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (actionType_ == PlayerActionGameType.SELECT_TRICKS_HANDS) {
            Element element_ = _document.createElement(TYPE_SELECT_TRICKS_HANDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof DiscardedCardsPresident) {
            Element element_ = _document.createElement(TYPE_DISCARDED_CARDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDiscardedCards((DiscardedCardsPresident)_object,element_,_document);
            return element_;
        }
        if (_object instanceof PlayingCardPresident) {
            Element element_ = _document.createElement(TYPE_PLAYING_CARD_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayingCardPresident((PlayingCardPresident)_object,element_,_document);
            return element_;
        }
//        if (_object instanceof RefreshHandPlayingPresident) {
//            Element element_ = _document.createElement(TYPE_REFRESH_HAND_PLAYING_PRESIDENT);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshHandPlayingPresident((RefreshHandPlayingPresident)_object,element_,_document);
//            return element_;
//        }
//        if (_object instanceof RefreshingDonePresident) {
//            Element element_ = _document.createElement(TYPE_REFRESHING_DONE_PRESIDENT);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshingDonePresident((RefreshingDonePresident)_object,element_,_document);
//            return element_;
//        }
        if (actionType_ == PlayerActionGameType.REFHESHED_HAND_PRESIDENT) {
            Element element_ = _document.createElement(TYPE_REFRESHED_HAND_PRESIDENT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof CallAfterDiscardTarot && actionType_ == PlayerActionGameType.SLAM) {
            Element element_ = _document.createElement(TYPE_BIDDING_SLAM_AFTER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCalledCards((CallAfterDiscardTarot)_object,element_,_document);
            return element_;
        }
        if (_object instanceof BiddingTarot) {
            Element element_ = _document.createElement(TYPE_BIDDING_TAROT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBiddingTarot((BiddingTarot)_object,element_,_document);
            return element_;
        }
        if (_object instanceof CallAfterDiscardTarot && actionType_ == PlayerActionGameType.VALIDATE_DOG) {
            Element element_ = _document.createElement(TYPE_VALIDATE_DOG);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCalledCards((CallAfterDiscardTarot) _object,element_,_document);
            return element_;
        }
        if (_object instanceof CallAfterDiscardTarot) {
            Element element_ = _document.createElement(TYPE_CALLED_CARDS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCalledCards((CallAfterDiscardTarot)_object,element_,_document);
            return element_;
        }
//        if (_object instanceof DiscardedCard) {
//            Element element_ = _document.createElement(TYPE_DISCARDED_CARD);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setDiscardedCard((DiscardedCard)_object,element_,_document);
//            return element_;
//        }
        if (_object instanceof PlayingCardTarot) {
            Element element_ = _document.createElement(TYPE_PLAYING_CARD_TAROT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayingCardTarot((PlayingCardTarot)_object,element_,_document);
            return element_;
        }
//        if (actionType_ == PlayerActionGameType.CALLED_CARD_KNOWN) {
//            Element element_ = _document.createElement(TYPE_CALLED_CARD_KNOWN);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setPlayerActionGame(_object,element_,_document);
//            return element_;
//        }
//        if (actionType_ == PlayerActionGameType.DONE_DISPLAY_SLAM) {
//            Element element_ = _document.createElement(TYPE_DONE_DISPLAY_SLAM);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setPlayerActionGame(_object,element_,_document);
//            return element_;
//        }
//        if (_object instanceof RefreshHand) {
//            Element element_ = _document.createElement(TYPE_REFRESH_HAND);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshHand((RefreshHand)_object,element_,_document);
//            return element_;
//        }
//        if (_object instanceof RefreshingDone) {
//            Element element_ = _document.createElement(TYPE_REFRESHING_DONE);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setRefreshingDone((RefreshingDone)_object,element_,_document);
//            return element_;
//        }
//        if (_object instanceof SeenDiscardedTrumps) {
//            Element element_ = _document.createElement(TYPE_SEEN_DISCARDED_TRUMPS);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setSeenDiscardedTrumps((SeenDiscardedTrumps)_object,element_,_document);
//            return element_;
//        }
//        if (actionType_ == PlayerActionGameType.SHOW_DOG) {
//            Element element_ = _document.createElement(TYPE_SHOW_DOG);
//            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//            setPlayerActionGame(_object,element_,_document);
//            return element_;
//        }
        return _document.createElement(TYPE_PLAYER_ACTION_GAME);
    }

    private static void setPlayerActionGame(PlayerActionGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlace(),FIELD_PLACE,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLocale(),FIELD_LOCALE,_document));
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

    private static void setNewPlayer(NewPlayerCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerByte(_object.getPlacesPlayers(),FIELD_PLACES_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerBoolean(_object.getReadyPlayers(),FIELD_READY_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isArriving(),FIELD_ARRIVING,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLanguage(),FIELD_LANGUAGE,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAcceptable(),FIELD_ACCEPTABLE,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static void setOldPlayer(OldPlayerCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getTarget(),FIELD_PSEUDOS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isArriving(),FIELD_ARRIVING,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLanguage(),FIELD_LANGUAGE,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAcceptable(),FIELD_ACCEPTABLE,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setPlayerActionBeforeGame(PlayerActionBeforeGameCards _object, String _fieldName, Document _document) {
        if (_object instanceof ChoosenPlace) {
            Element element_ = _document.createElement(TYPE_CHOOSEN_PLACE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setChoosenPlace((ChoosenPlace)_object,element_,_document);
            return element_;
        }
        if (_object instanceof IndexOfArrivingCards) {
            IndexOfArrivingCards ind_ = (IndexOfArrivingCards) _object;
            Element element_ = _document.createElement(TYPE_INDEX_OF_ARRIVING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            element_.appendChild(DocumentWriterCoreUtil.setMapIntegerByte(ind_.getPlacesPlayers(),FIELD_PLACES_PLAYERS,_document));
            element_.appendChild(DocumentWriterCoreUtil.setMapIntegerBoolean(ind_.getReadyPlayers(),FIELD_READY_PLAYERS,_document));
            element_.appendChild(DocumentWriterCoreUtil.setInteger(ind_.getNbPlayers(),FIELD_NB_PLAYERS,_document));
//            _element.appendChild(DocumentWriterCoreUtil.setBoolean(ind_.isFirst(),FIELD_FIRST,_document));
            element_.appendChild(DocumentWriterTarotUtil.setRulesTarot(ind_.getRulesTarot(),FIELD_RULES_TAROT,_document));
            element_.appendChild(DocumentWriterPresidentUtil.setRulesPresident(ind_.getRulesPresident(),FIELD_RULES_PRESIDENT,_document));
            element_.appendChild(DocumentWriterBeloteUtil.setRulesBelote(ind_.getRulesBelote(),FIELD_RULES_BELOTE,_document));
//            element_.appendChild(DocumentWriterCoreUtil.setInteger(((IndexOfArrivingCards) _object).getNbPlayers(),FIELD_NB_PLAYERS,_document));
            setPlayerActionBeforeGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof NewPlayerCards) {
            Element element_ = _document.createElement(TYPE_NEW_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setNewPlayer((NewPlayerCards)_object,element_,_document);
            return element_;
        }
        if (_object instanceof OldPlayerCards) {
            Element element_ = _document.createElement(TYPE_OLD_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setOldPlayer((OldPlayerCards)_object,element_,_document);
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

    private static void setPlayerActionBeforeGame(PlayerActionBeforeGameCards _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndex(),FIELD_INDEX,_document));
    }

//    private static Element setPlayersNamePresent(PlayersNamePresent _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_PLAYERS_NAME_PRESENT);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setPlayersNamePresent(_object,element_,_document);
//        return element_;
//    }
//
//    private static void setPlayersNamePresent(PlayersNamePresent _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerString(_object.getPseudos(),FIELD_PSEUDOS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerByte(_object.getPlacesPlayers(),FIELD_PLACES_PLAYERS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setMapIntegerBoolean(_object.getReadyPlayers(),FIELD_READY_PLAYERS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbPlayers(),FIELD_NB_PLAYERS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isFirst(),FIELD_FIRST,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setRulesTarot(_object.getRulesTarot(),FIELD_RULES_TAROT,_document));
//        _element.appendChild(DocumentWriterPresidentUtil.setRulesPresident(_object.getRulesPresident(),FIELD_RULES_PRESIDENT,_document));
//        _element.appendChild(DocumentWriterBeloteUtil.setRulesBelote(_object.getRulesBelote(),FIELD_RULES_BELOTE,_document));
//    }

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

    private static void setDiscardedCards(DiscardedCardsPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getDiscarded(),FIELD_DISCARDED,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setPlayingCardPresident(PlayingCardPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
        _element.appendChild(setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isRefreshing(),FIELD_REASON,_document));
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
        _element.appendChild(setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
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

//    private static Element setErrorPlayingPresident(ErrorPlayingPresident _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_PLAYING_PRESIDENT);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorPlayingPresident(_object,element_,_document);
//        return element_;
//    }
//
//    private static void setErrorPlayingPresident(ErrorPlayingPresident _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPassIssue(),FIELD_PASS_ISSUE,_document));
//    }

//    private static void setRefreshHandPlayingPresident(RefreshHandPlayingPresident _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
//        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
//        _element.appendChild(setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNextPlayer(),FIELD_NEXT_PLAYER,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReversed(),FIELD_REVERSED,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

//    private static void setRefreshingDonePresident(RefreshingDonePresident _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterPresidentUtil.setCardPresident(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPass(),FIELD_PASS,_document));
//        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getPlayedHand(),FIELD_PLAYED_HAND,_document));
//        _element.appendChild(setMapBytePlaying(_object.getStatus(),FIELD_STATUS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNextPlayer(),FIELD_NEXT_PLAYER,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

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
        _element.appendChild(setPlaying(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setHandPresident(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReversed(),FIELD_REVERSED,_document));
    }

    private static Element setDog(DiscardPhaseTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DOG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDog(_object,element_,_document);
        return element_;
    }

    private static void setDog(DiscardPhaseTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getDiscardCard(),FIELD_DOG,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCallableCards(),FIELD_CALLABLE_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDiscardPhase().getTaker(),FIELD_TAKER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDiscardPhase().getTakerIndex(),FIELD_TAKER_INDEX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCallAfter(),FIELD_CALL_AFTER,_document));
    }

    private static Element setDiscard(DiscardPhaseBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DOG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDiscard(_object,element_,_document);
        return element_;
    }

    private static void setDiscard(DiscardPhaseBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setHandBelote(_object.getDiscard(),FIELD_DOG,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDiscardPhase().getTaker(),FIELD_TAKER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDiscardPhase().getTakerIndex(),FIELD_TAKER_INDEX,_document));
    }
    private static void setBiddingTarot(BiddingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getBid(),FIELD_BID,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setCalledCards(CallAfterDiscardTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCalledCards(),FIELD_CALLED_CARDS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDiscarding(),FIELD_DISCARDING,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setDiscardedCardBelote(DiscardedCardBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setCardBelote(_object.getCard(),FIELD_CARD,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setDiscardedCardTarot(DiscardedCardTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
        setPlayerActionGame(_object, _element, _document);
    }
//    private static Element setDiscardedTrumps(DiscardedTrumps _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_DISCARDED_TRUMPS);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setDiscardedTrumps(_object,element_,_document);
//        return element_;
//    }
//
//    private static void setDiscardedTrumps(DiscardedTrumps _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getTrumps(),FIELD_TRUMPS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringSlam(),FIELD_DECLARING_SLAM,_document));
//    }

    private static void setPlayingCardTarot(PlayingCardTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getPlayedCard(),FIELD_PLAYED_CARD,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getExcludedTrumps(),FIELD_EXCLUDED_TRUMPS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isRefreshing(),FIELD_REASON,_document));
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

//    private static Element setErrorBidding(ErrorBidding _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_BIDDING);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorBidding(_object,element_,_document);
//        return element_;
//    }

//    private static void setErrorBidding(ErrorBidding _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getBid(),FIELD_BID,_document));
//    }

//    private static Element setErrorDiscarding(ErrorDiscarding _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_DISCARDING);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorDiscarding(_object,element_,_document);
//        return element_;
//    }

//    private static void setErrorDiscarding(ErrorDiscarding _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getErrorMessage(),FIELD_ERROR_MESSAGE,_document));
//    }

//    private static Element setErrorHandful(ErrorHandful _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_HANDFUL);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorHandful(_object,element_,_document);
//        return element_;
//    }

//    private static void setErrorHandful(ErrorHandful _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getHandful(),FIELD_HANDFUL,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getError(),FIELD_ERROR,_document));
//    }

//    private static Element setErrorPlaying(ErrorPlaying _object, String _fieldName, Document _document) {
//        Element element_ = _document.createElement(TYPE_ERROR_PLAYING);
//        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
//        setErrorPlaying(_object,element_,_document);
//        return element_;
//    }

//    private static void setErrorPlaying(ErrorPlaying _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getReason(),FIELD_REASON,_document));
//    }

//    private static void setRefreshHand(RefreshHand _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCalledCard(),FIELD_CALLED_CARD,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

//    private static void setRefreshingDone(RefreshingDone _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterTarotUtil.setCardTarot(_object.getCard(),FIELD_CARD,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setHandfuls(_object.getChoosenHandful(),FIELD_CHOOSEN_HANDFUL,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getHandful(),FIELD_HANDFUL,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCalledCard(),FIELD_CALLED_CARD,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

//    private static void setSeenDiscardedTrumps(SeenDiscardedTrumps _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDeclaringSlam(),FIELD_DECLARING_SLAM,_document));
//        setPlayerActionGame(_object, _element, _document);
//    }

    private static Element setAllowBiddingTarot(AllowBiddingTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_BIDDING_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowBiddingTarot(_object,element_,_document);
        return element_;
    }

    private static void setAllowBiddingTarot(AllowBiddingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterTarotUtil.setListBidTarot(_object.getBids(),FIELD_BIDS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getMaxBid(),FIELD_BID,_document));
    }

    private static Element setAllowPlayingTarot(AllowPlayingTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLOW_PLAYING_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAllowPlayingTarot(_object,element_,_document);
        return element_;
    }

    private static void setAllowPlayingTarot(AllowPlayingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isFirstRoundPlaying(),FIELD_FIRST_ROUND_PLAYING,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setListHandfuls(_object.getAllowedHandfuls(),FIELD_ALLOWED_HANDFULS,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setMapHandfulsInteger(_object.getRequiredTrumps(),FIELD_REQUIRED_TRUMPS,_document));
//        _element.appendChild(DocumentWriterTarotUtil.setListMiseres(_object.getAllowedMiseres(),FIELD_ALLOWED_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getTakerIndex(),FIELD_TAKER_INDEX,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getDiscardedTrumps(),FIELD_TRUMPS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCalledCards(),FIELD_CALLED_CARDS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setHandTarot(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterTarotUtil.setBidTarot(_object.getCurrentBid(),FIELD_BID,_document));
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

    public static Element setPlaying(Playing _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getPlay());
        return elt_;
    }

    public static Element setMapBytePlaying(ByteMap<Playing> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, Playing> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), DocumentWriterPresidentUtil.EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setPlaying(s.getValue(), DocumentWriterPresidentUtil.EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }
}
