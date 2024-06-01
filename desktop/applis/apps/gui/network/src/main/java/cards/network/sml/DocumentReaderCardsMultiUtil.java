package cards.network.sml;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.DiscardedCardBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
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
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.displaying.players.RefreshingDonePresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.players.*;
import cards.network.tarot.unlock.*;
import cards.president.enumerations.Playing;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.tarot.sml.DocumentReaderTarotUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.ByteMap;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderCardsMultiUtil {
//    public static final String TYPE_CALLABLE_CARDS_DISCARD = "CallableCardsDiscard";
    public static final String TYPE_ENABLED_QUIT = "EnabledQuit";
    private DocumentReaderCardsMultiUtil() {
    }

    public static Document getDoc(String _input) {
        return DocumentBuilder.parseNoTextDocument(_input);
//        Element elt_ = doc_.getDocumentElement();
//        String tagName_ = tagName(elt_);
//        if (StringUtil.quickEq(tagName_, DocumentWriterBeloteUtil.TYPE_RESULTS_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterBeloteUtil.TYPE_RULES_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterBeloteUtil.TYPE_TRICKS_HANDS_BELOTE)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_COMPLETED_HAND) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DEALT_HAND_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_PLAYING_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_PLAYING_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_BIDDING_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_PLAYING_BELOTE)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_BYE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DEALT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DELEGATE_SERVER) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_OK)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_TAKE_CARD) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DISPLAY_SLAM_BUTTON) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PLAY_GAME) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PAUSE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_QUIT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_CHOOSEN_PLACE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_INDEX_OF_ARRIVING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_NEW_PLAYER) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PLAYERS_NAME_PRESENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_READY)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DONE_BIDDING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DONE_PAUSE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DONE_PLAYING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_SELECT_TEAMS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_SELECT_TRICKS_HANDS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_TEAMS_PLAYERS)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DISCARDED_CARDS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DEALT_HAND_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_RECEIVED_GIVEN_CARDS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESHED_HAND_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_PLAYING_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_PLAYING_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_DISCARDING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_PLAYING_PRESIDENT)
//                || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DOG) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_BIDDING_SLAM_AFTER) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_BIDDING_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_CALLED_CARDS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DISCARDED_CARD) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DISCARDED_TRUMPS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_VALIDATE_DOG) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DEALT_HAND_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_BIDDING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_DISCARDING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_HANDFUL) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ERROR_PLAYING) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_CALLED_CARD_KNOWN) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_DONE_DISPLAY_SLAM) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_SEEN_DISCARDED_TRUMPS) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_SHOW_DOG) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_BIDDING_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_ALLOW_PLAYING_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_CALLABLE_CARDS) || StringUtil.quickEq(tagName_, TYPE_CALLABLE_CARDS_DISCARD)
//                || StringUtil.quickEq(tagName_, DocumentWriterPresidentUtil.TYPE_RESULTS_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterPresidentUtil.TYPE_RULES_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterPresidentUtil.TYPE_TRICKS_HANDS_PRESIDENT)
//                || StringUtil.quickEq(tagName_, DocumentWriterTarotUtil.TYPE_RESULTS_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterTarotUtil.TYPE_RULES_TAROT) || StringUtil.quickEq(tagName_, DocumentWriterTarotUtil.TYPE_TRICKS_HANDS_TAROT)) {
//            return doc_;
//        }
//        return null;
    }
    public static Exiting getExiting(Document _input) {
        Element elt_ = _input.getDocumentElement();
        String tagName_ = tagName(elt_);
        if (StringUtil.quickEq(tagName_, DocumentWriterCardsMultiUtil.TYPE_BYE)) {
            return getBye(elt_);
        }
        return null;
    }

    public static String tagName(Element _elt) {
        return _elt.getTagName();
    }

    private static void getBiddingBelote(BiddingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BID_BELOTE)) {
            _object.setBidBelote(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getPlayingCardBelote(PlayingCardBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandBelote getDealtHandBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandBelote object_ = new DealtHandBelote();
        for (Element c: childElements_) {
            getDealtHandBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandBelote(DealtHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
            _object.setCards(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECK)) {
            _object.setDeck(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(DocumentReaderBeloteUtil.getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REP)) {
            _object.setRep(DocumentReaderBeloteUtil.getDealingBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_POINTS)) {
            _object.setPoints(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static void getRefreshHandBelote(RefreshHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REFRESHED_HAND)) {
            _object.setRefreshedHand(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

//    public static ErrorBiddingBelote getErrorBiddingBelote(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorBiddingBelote object_ = new ErrorBiddingBelote();
//        for (Element c: childElements_) {
//            getErrorBiddingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorBiddingBelote(ErrorBiddingBelote _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BID)) {
//            _object.setBid(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
//            return;
//        }
//    }

//    public static ErrorPlayingBelote getErrorPlayingBelote(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorPlayingBelote object_ = new ErrorPlayingBelote();
//        for (Element c: childElements_) {
//            getErrorPlayingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorPlayingBelote(ErrorPlayingBelote _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
//            _object.setCards(DocumentReaderBeloteUtil.getHandBelote(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
//            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REASON)) {
//            _object.setReason(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
//    }

    private static void getRefreshHandPlayingBelote(RefreshHandPlayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDoneBelote(RefreshingDoneBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING)) {
            _object.setDeclaring(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING_BELOTE_REBELOTE)) {
            _object.setDeclaringBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARE)) {
            _object.setDeclare(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static AllowBiddingBelote getAllowBiddingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowBiddingBelote object_ = new AllowBiddingBelote();
        for (Element c: childElements_) {
            getAllowBiddingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowBiddingBelote(AllowBiddingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BIDS)) {
            _object.setBids(DocumentReaderBeloteUtil.getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_POINTS)) {
            _object.setBid(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
            return;
        }
    }

    public static AllowPlayingBelote getAllowPlayingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingBelote object_ = new AllowPlayingBelote();
        for (Element c: childElements_) {
            getAllowPlayingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingBelote(AllowPlayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_FIRST_ROUND_PLAYING)) {
            _object.setFirstRoundPlaying(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARATION)) {
            _object.setDeclaration(DocumentReaderBeloteUtil.getDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_POSSIBLE_BELOTE_REBELOTE)) {
            _object.setPossibleBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ALLOWED_BELOTE_REBELOTE)) {
            _object.setAllowedBeloteRebelote(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ERROR_MESSAGE)) {
            _object.setBelReb(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
            _object.setCards(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        _object.setCurrentBid(DocumentReaderBeloteUtil.getBidBeloteSuit(_element));
    }

    private static Exiting getBye(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Exiting object_ = new Exiting();
        for (Element c: childElements_) {
            getBye(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getBye(Exiting _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_FORCED)) {
            _object.setForced(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BUSY)) {
            _object.setBusy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static DelegateServer getDelegateServer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DelegateServer object_ = new DelegateServer();
        for (Element c: childElements_) {
            getDelegateServer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDelegateServer(DelegateServer _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_GAMES)) {
            _object.setGames(DocumentReaderCardsUnionUtil.getGames(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NICKNAMES)) {
            _object.setNicknames(DocumentReaderCoreUtil.getMapIntegerString(_element));
            return;
        }
    }

    public static PlayerActionGame getPlayerActionGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = tagName(_element);
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_BIDDING_BELOTE)) {
            BiddingBelote object_ = new BiddingBelote();
            for (Element c: childElements_) {
                getBiddingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_BELOTE)) {
            PlayingCardBelote object_ = new PlayingCardBelote();
            for (Element c: childElements_) {
                getPlayingCardBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_COMPLETED_HAND)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.COMPLETED_HAND);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_PLAYING_BELOTE)) {
            RefreshHandPlayingBelote object_ = new RefreshHandPlayingBelote();
            for (Element c: childElements_) {
                getRefreshHandPlayingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE_BELOTE)) {
            RefreshingDoneBelote object_ = new RefreshingDoneBelote();
            for (Element c: childElements_) {
                getRefreshingDoneBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_BELOTE)) {
            RefreshHandBelote object_ = new RefreshHandBelote();
            for (Element c: childElements_) {
                getRefreshHandBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DEALT)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DEALT);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DONE_BIDDING)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_BIDDING);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DONE_PAUSE)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DONE_PLAYING)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_OK)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.OK);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_QUIT)) {
            Quit object_ = new Quit();
            for (Element c: childElements_) {
                getQuit(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_SELECT_TEAMS)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_SELECT_TRICKS_HANDS)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DISCARDED_CARDS)) {
            DiscardedCards object_ = new DiscardedCards();
            for (Element c: childElements_) {
                getDiscardedCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_PRESIDENT)) {
            PlayingCardPresident object_ = new PlayingCardPresident();
            for (Element c: childElements_) {
                getPlayingCardPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND_PLAYING_PRESIDENT)) {
            RefreshHandPlayingPresident object_ = new RefreshHandPlayingPresident();
            for (Element c: childElements_) {
                getRefreshHandPlayingPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE_PRESIDENT)) {
            RefreshingDonePresident object_ = new RefreshingDonePresident();
            for (Element c: childElements_) {
                getRefreshingDonePresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESHED_HAND_PRESIDENT)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.REFHESHED_HAND_PRESIDENT);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_BIDDING_SLAM_AFTER)) {
            CallAfterDiscardTarot object_ = new CallAfterDiscardTarot(PlayerActionGameType.SLAM);
            for (Element c: childElements_) {
                getCalledCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_BIDDING_TAROT)) {
            BiddingTarot object_ = new BiddingTarot();
            for (Element c: childElements_) {
                getBiddingTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_CALLED_CARDS)) {
            CallAfterDiscardTarot object_ = new CallAfterDiscardTarot(PlayerActionGameType.SIMPLE);
            for (Element c: childElements_) {
                getCalledCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DISCARDED_CARD)) {
            return dis(childElements_,_element);
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_PLAYING_CARD_TAROT)) {
            PlayingCardTarot object_ = new PlayingCardTarot();
            for (Element c: childElements_) {
                getPlayingCardTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_VALIDATE_DOG)) {
            CallAfterDiscardTarot object_ = new CallAfterDiscardTarot(PlayerActionGameType.VALIDATE_DOG);
            for (Element c: childElements_) {
                getCalledCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
//        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_CALLED_CARD_KNOWN)) {
//            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.CALLED_CARD_KNOWN);
//            for (Element c: childElements_) {
//                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//            }
//            return object_;
//        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_DONE_DISPLAY_SLAM)) {
            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.DONE_DISPLAY_SLAM);
            for (Element c: childElements_) {
                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESH_HAND)) {
            RefreshHand object_ = new RefreshHand();
            for (Element c: childElements_) {
                getRefreshHand(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_REFRESHING_DONE)) {
            RefreshingDone object_ = new RefreshingDone();
            for (Element c: childElements_) {
                getRefreshingDone(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
//        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_SEEN_DISCARDED_TRUMPS)) {
//            SeenDiscardedTrumps object_ = new SeenDiscardedTrumps();
//            for (Element c: childElements_) {
//                getSeenDiscardedTrumps(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//            }
//            return object_;
//        }
//        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_SHOW_DOG)) {
//            PlayerActionGame object_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
//            for (Element c: childElements_) {
//                getPlayerActionGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//            }
//            return object_;
//        }
        return null;
    }

    private static PlayerActionGame dis(ElementList _childElements, Element _element) {
        String val_ = _element.getAttribute(DocumentReaderCoreUtil.VALUE);
        boolean tarot_ = val_.isEmpty();
        if (tarot_) {
            DiscardedCardTarot tarotCard_ = new DiscardedCardTarot();
            for (Element c: _childElements) {
                getDiscardedCardTarot(tarotCard_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return tarotCard_;
        }
        DiscardedCardBelote beloteCard_ = new DiscardedCardBelote();
        for (Element c: _childElements) {
            getDiscardedCardBelote(beloteCard_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return beloteCard_;
    }

    private static void getPlayerActionGame(PlayerActionGame _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getByte(_element));
//            return;
        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_LOCALE)) {
//            _object.setLocale(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
    }

    private static void getQuit(Quit _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getChoosenPlace(ChoosenPlace _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLACES_PLAYERS)) {
            _object.setPlacesPlayers(DocumentReaderCoreUtil.getMapIntegerByte(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    private static void getNewPlayer(NewPlayerCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ARRIVING)) {
            _object.setArriving(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_LANGUAGE)) {
//            _object.setLanguage(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ACCEPTABLE)) {
            _object.setAcceptable(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static PlayerActionBeforeGameCards getPlayerActionBeforeGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = tagName(_element);
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_CHOOSEN_PLACE)) {
            ChoosenPlace object_ = new ChoosenPlace();
            for (Element c: childElements_) {
                getChoosenPlace(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_INDEX_OF_ARRIVING)) {
            IndexOfArrivingCards object_ = new IndexOfArrivingCards();
            for (Element c: childElements_) {
                getPlayerActionBeforeGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_NEW_PLAYER)) {
            NewPlayerCards object_ = new NewPlayerCards();
            for (Element c: childElements_) {
                getNewPlayer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterCardsMultiUtil.TYPE_READY)) {
            Ready object_ = new Ready();
            for (Element c: childElements_) {
                getReady(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionBeforeGame(PlayerActionBeforeGameCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static PlayersNamePresent getPlayersNamePresent(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PlayersNamePresent object_ = new PlayersNamePresent();
        for (Element c: childElements_) {
            getPlayersNamePresent(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPlayersNamePresent(PlayersNamePresent _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PSEUDOS)) {
            _object.setPseudos(DocumentReaderCoreUtil.getMapIntegerString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLACES_PLAYERS)) {
            _object.setPlacesPlayers(DocumentReaderCoreUtil.getMapIntegerByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_READY_PLAYERS)) {
            _object.setReadyPlayers(DocumentReaderCoreUtil.getMapIntegerBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NB_PLAYERS)) {
            _object.setNbPlayers(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_FIRST)) {
            _object.setFirst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_RULES_TAROT)) {
            _object.setRulesTarot(DocumentReaderTarotUtil.getRulesTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_RULES_PRESIDENT)) {
            _object.setRulesPresident(DocumentReaderPresidentUtil.getRulesPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_RULES_BELOTE)) {
            _object.setRulesBelote(DocumentReaderBeloteUtil.getRulesBelote(_element));
            return;
        }
    }

    private static void getReady(Ready _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_READY)) {
            _object.setReady(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static TeamsPlayers getTeamsPlayers(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TeamsPlayers object_ = new TeamsPlayers();
        for (Element c: childElements_) {
            getTeamsPlayers(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTeamsPlayers(TeamsPlayers _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TEAMS)) {
            _object.setTeams(DocumentReaderCoreUtil.getListListByte(_element));
            return;
        }
    }

    private static void getDiscardedCards(DiscardedCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DISCARDED)) {
            _object.setDiscarded(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getPlayingCardPresident(PlayingCardPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_STATUS)) {
            _object.setStatus(getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandPresident getDealtHandPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandPresident object_ = new DealtHandPresident();
        for (Element c: childElements_) {
            getDealtHandPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandPresident(DealtHandPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
            _object.setCards(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_MAX_CARDS)) {
            _object.setMaxCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_STATUS)) {
            _object.setStatus(getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    public static ReceivedGivenCards getReceivedGivenCards(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ReceivedGivenCards object_ = new ReceivedGivenCards();
        for (Element c: childElements_) {
            getReceivedGivenCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getReceivedGivenCards(ReceivedGivenCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_RECEIVED)) {
            _object.setReceived(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_GIVEN)) {
            _object.setGiven(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NEW_HAND)) {
            _object.setNewHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
    }

//    public static ErrorPlayingPresident getErrorPlayingPresident(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorPlayingPresident object_ = new ErrorPlayingPresident();
//        for (Element c: childElements_) {
//            getErrorPlayingPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorPlayingPresident(ErrorPlayingPresident _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
//            _object.setCard(DocumentReaderPresidentUtil.getCardPresident(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REASON)) {
//            _object.setReason(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PASS_ISSUE)) {
//            _object.setPassIssue(DocumentReaderCoreUtil.getBoolean(_element));
//            return;
//        }
//    }

    private static void getRefreshHandPlayingPresident(RefreshHandPlayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_STATUS)) {
            _object.setStatus(getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDonePresident(RefreshingDonePresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderPresidentUtil.getCardPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PASS)) {
            _object.setPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_HAND)) {
            _object.setPlayedHand(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_STATUS)) {
            _object.setStatus(getMapBytePlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_NEXT_PLAYER)) {
            _object.setNextPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static AllowDiscarding getAllowDiscarding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowDiscarding object_ = new AllowDiscarding();
        for (Element c: childElements_) {
            getAllowDiscarding(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowDiscarding(AllowDiscarding _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_RECEIVED_CARDS)) {
            _object.setReceivedCards(DocumentReaderPresidentUtil.getHandPresident(_element));
            return;
        }
    }

    public static AllowPlayingPresident getAllowPlayingPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingPresident object_ = new AllowPlayingPresident();
        for (Element c: childElements_) {
            getAllowPlayingPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingPresident(AllowPlayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ENABLED_PASS)) {
            _object.setEnabledPass(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_STATUS)) {
            _object.setStatus(getPlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setCards(DocumentReaderPresidentUtil.getHandPresident(_element));
    }

    public static Dog getDog(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Dog object_ = new Dog();
        for (Element c: childElements_) {
            getDog(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDog(Dog _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DOG)) {
            _object.setDiscardCard(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER)) {
            _object.getDiscardPhase().setTaker(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.getDiscardPhase().setTakerIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALL_AFTER)) {
            _object.setCallAfter(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setCallableCards(DocumentReaderTarotUtil.getHandTarot(_element));
    }

    public static DiscardPhaseBelote getDiscard(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DiscardPhaseBelote object_ = new DiscardPhaseBelote();
        for (Element c: childElements_) {
            getDiscard(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDiscard(DiscardPhaseBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DOG)) {
            _object.setDiscard(DocumentReaderBeloteUtil.getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER)) {
            _object.getDiscardPhase().setTaker(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        _object.getDiscardPhase().setTakerIndex(DocumentReaderCoreUtil.getInteger(_element));
    }
    private static void getBiddingTarot(BiddingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BID)) {
            _object.setBid(DocumentReaderTarotUtil.getBidTarot(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getCalledCards(CallAfterDiscardTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLED_CARDS)) {
            _object.setCalledCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DISCARDING)) {
//            _object.setDiscarding(DocumentReaderCoreUtil.getBoolean(_element));
//            return;
//        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getDiscardedCardBelote(DiscardedCardBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderBeloteUtil.getCardBelote(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }
    private static void getDiscardedCardTarot(DiscardedCardTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

//    public static DiscardedTrumps getDiscardedTrumps(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        DiscardedTrumps object_ = new DiscardedTrumps();
//        for (Element c: childElements_) {
//            getDiscardedTrumps(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getDiscardedTrumps(DiscardedTrumps _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TRUMPS)) {
//            _object.setTrumps(DocumentReaderTarotUtil.getHandTarot(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING_SLAM)) {
//            _object.setDeclaringSlam(DocumentReaderCoreUtil.getBoolean(_element));
//            return;
//        }
//    }

    private static void getPlayingCardTarot(PlayingCardTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_PLAYED_CARD)) {
            _object.setPlayedCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_EXCLUDED_TRUMPS)) {
            _object.setExcludedTrumps(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    public static DealtHandTarot getDealtHandTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealtHandTarot object_ = new DealtHandTarot();
        for (Element c: childElements_) {
            getDealtHandTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealtHandTarot(DealtHandTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
            _object.setCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DOG)) {
            _object.setDog(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(DocumentReaderTarotUtil.getListBidTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REP)) {
            _object.setRep(DocumentReaderTarotUtil.getDealingTarot(_element));
            return;
        }
    }

//    public static ErrorBidding getErrorBidding(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorBidding object_ = new ErrorBidding();
//        for (Element c: childElements_) {
//            getErrorBidding(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorBidding(ErrorBidding _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BID)) {
//            _object.setBid(DocumentReaderTarotUtil.getBidTarot(_element));
//            return;
//        }
//    }

//    public static ErrorDiscarding getErrorDiscarding(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorDiscarding object_ = new ErrorDiscarding();
//        for (Element c: childElements_) {
//            getErrorDiscarding(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorDiscarding(ErrorDiscarding _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
//            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ERROR_MESSAGE)) {
//            _object.setErrorMessage(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
//    }

//    public static ErrorHandful getErrorHandful(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorHandful object_ = new ErrorHandful();
//        for (Element c: childElements_) {
//            getErrorHandful(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorHandful(ErrorHandful _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_HANDFUL)) {
//            _object.setHandful(DocumentReaderTarotUtil.getHandfuls(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ERROR)) {
//            _object.setError(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
//    }
//
//    public static ErrorPlaying getErrorPlaying(Element _element) {
//        ElementList childElements_ = _element.getChildElements();
//        ErrorPlaying object_ = new ErrorPlaying();
//        for (Element c: childElements_) {
//            getErrorPlaying(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
//        }
//        return object_;
//    }

//    private static void getErrorPlaying(ErrorPlaying _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
//            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
//            return;
//        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REASON)) {
//            _object.setReason(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
//    }

    private static void getRefreshHand(RefreshHand _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getRefreshingDone(RefreshingDone _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARD)) {
            _object.setCard(DocumentReaderTarotUtil.getCardTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CHOOSEN_HANDFUL)) {
            _object.setChoosenHandful(DocumentReaderTarotUtil.getHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_HANDFUL)) {
            _object.setHandful(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_MISERES)) {
            _object.setMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLED_CARD)) {
            _object.setCalledCard(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

//    private static void getSeenDiscardedTrumps(SeenDiscardedTrumps _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DECLARING_SLAM)) {
//            _object.setDeclaringSlam(DocumentReaderCoreUtil.getBoolean(_element));
//            return;
//        }
//        getPlayerActionGame(_object, _fieldName, _element);
//    }

    public static AllowBiddingTarot getAllowBiddingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowBiddingTarot object_ = new AllowBiddingTarot();
        for (Element c: childElements_) {
            getAllowBiddingTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowBiddingTarot(AllowBiddingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BIDS)) {
            _object.setBids(DocumentReaderTarotUtil.getListBidTarot(_element));
            return;
        }
        _object.setMaxBid(DocumentReaderTarotUtil.getBidTarot(_element));
    }

    public static AllowPlayingTarot getAllowPlayingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AllowPlayingTarot object_ = new AllowPlayingTarot();
        for (Element c: childElements_) {
            getAllowPlayingTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAllowPlayingTarot(AllowPlayingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_FIRST_ROUND_PLAYING)) {
            _object.setFirstRoundPlaying(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ALLOWED_HANDFULS)) {
            _object.setAllowedHandfuls(DocumentReaderTarotUtil.getListHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_REQUIRED_TRUMPS)) {
            _object.setRequiredTrumps(DocumentReaderTarotUtil.getMapHandfulsInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_ALLOWED_MISERES)) {
            _object.setAllowedMiseres(DocumentReaderTarotUtil.getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TRUMPS)) {
            _object.setDiscardedTrumps(DocumentReaderTarotUtil.getHandTarot(_element));
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLED_CARDS)) {
            _object.setCalledCards(DocumentReaderTarotUtil.getHandTarot(_element));
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CARDS)) {
            _object.setCards(DocumentReaderTarotUtil.getHandTarot(_element));
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_BID)) {
            _object.setCurrentBid(DocumentReaderTarotUtil.getBidTarot(_element));
        }
    }

    public static CallableCards getCallableCards(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CallableCards object_ = new CallableCards();
        for (Element c: childElements_) {
            getCallableCards(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getCallableCards(CallableCards _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_CALLABLE_CARDS)) {
            _object.setCallableCards(DocumentReaderTarotUtil.getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_DISCARDING)) {
            _object.setDiscarding(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsMultiUtil.FIELD_TAKER_INDEX)) {
            _object.setTakerIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    public static Playing getPlaying(Element _elt) {
        return Playing.retrieve(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static ByteMap<Playing> getMapBytePlaying(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Playing> map_ = new ByteMap<Playing>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Playing> values_ = new CustList<Playing>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getPlaying(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
}
