package cards.belote.sml;
import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.DeclareHandBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.TrickBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DocumentReaderBeloteUtil {

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final String FIELD_ALLOWED_BIDS = "allowedBids";
    private static final String FIELD_ALLOWED_DECLARES = "allowedDeclares";
    private static final String FIELD_BID = "bid";
    private static final String FIELD_BIDS = "bids";
    private static final String FIELD_CARDS = "cards";
    private static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "cardsHandsAtInitialState";
    private static final String FIELD_CLASSIC_COUNT_POINTS = "classicCountPoints";
    private static final String FIELD_CLOCKWISE = "clockwise";
    private static final String FIELD_DEAL = "deal";
    private static final String FIELD_DEALER = "dealer";
    private static final String FIELD_DEALING = "dealing";
    private static final String FIELD_DECLARE = "declare";
    private static final String FIELD_DECLARES = "declares";
    private static final String FIELD_DECLARES_BELOTE_REBELOTE = "declaresBeloteRebelote";
    private static final String FIELD_DECREASING = "decreasing";
    private static final String FIELD_DISTRIBUTION = "distribution";
    private static final String FIELD_GAME = "game";
    private static final String FIELD_HAND = "hand";
    private static final String FIELD_LOC = "loc";
    private static final String FIELD_MIXED_CARDS = "mixedCards";
    private static final String FIELD_NB_DEALS = "nbDeals";
    private static final String FIELD_NICKNAMES = "nicknames";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_ORDER_BEFORE_BIDS = "orderBeforeBids";
    private static final String FIELD_PLAYER = "player";
    private static final String FIELD_POINTS = "points";
    private static final String FIELD_PRENEUR = "preneur";
    private static final String FIELD_PROGRESSING_TRICK = "progressingTrick";
    private static final String FIELD_RULES = "rules";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_STARTER = "starter";
    private static final String FIELD_SUIT = "suit";
    private static final String FIELD_SUITS = "suits";
    private static final String FIELD_TRICKS = "tricks";
    private static final String FIELD_TRUMP_PARTNER = "trumpPartner";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_UNDER_TRUMP_FOE = "underTrumpFoe";
    private static final String FIELD_USER = "user";
    private static final String FIELD_WON_LAST_TRICK = "wonLastTrick";

    public static BidBeloteSuit getBidBeloteSuit(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        BidBeloteSuit object_ = new BidBeloteSuit();
        for (Element c: childElements_) {
            getBidBeloteSuit(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getBidBeloteSuit(BidBeloteSuit _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(getBidBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SUIT)) {
            _object.setSuit(DocumentReaderCardsCommonUtil.getSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POINTS)) {
            _object.setPoints(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static DealBelote getDealBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealBelote object_ = new DealBelote();
        for (Element c: childElements_) {
            getDealBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealBelote(DealBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getListHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.setNbDeals(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
    }

    public static DeclareHandBelote getDeclareHandBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DeclareHandBelote object_ = new DeclareHandBelote();
        for (Element c: childElements_) {
            getDeclareHandBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDeclareHandBelote(DeclareHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARE)) {
            _object.setDeclare(getDeclaresBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HAND)) {
            _object.setHand(getHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PLAYER)) {
            _object.setPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    public static DisplayingBelote getDisplayingBelote(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new DisplayingBelote();
        }
        return getDisplayingBelote(doc_.getDocumentElement());
    }

    private static DisplayingBelote getDisplayingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingBelote object_ = new DisplayingBelote();
        for (Element c: childElements_) {
            getDisplayingBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingBelote(DisplayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CLOCKWISE)) {
            _object.getDisplaying().setClockwise(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SUITS)) {
            _object.getDisplaying().setSuits(DocumentReaderCardsCommonUtil.getListSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECREASING)) {
            _object.getDisplaying().setDecreasing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ORDER_BEFORE_BIDS)) {
            _object.setOrderBeforeBids(DocumentReaderCardsCommonUtil.getOrder(_element));
            return;
        }
    }

    public static GameBelote getGameBelote(Document _doc) {
        return getGameBelote(_doc.getDocumentElement());
    }

    private static GameBelote getGameBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GameBelote object_ = new GameBelote();
        for (Element c: childElements_) {
            getGameBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGameBelote(GameBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getDealBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARES)) {
            _object.setDeclares(getListDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DECLARES_BELOTE_REBELOTE)) {
            _object.setDeclaresBeloteRebelote(getListHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_WON_LAST_TRICK)) {
            _object.setWonLastTrick(DocumentReaderCoreUtil.getBooleanList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_BIDS)) {
            _object.setBids(getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RULES)) {
            _object.setRules(getRulesBelote(_element));
            return;
        }
    }

    public static HandBelote getHandBelote(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new HandBelote();
        }
        return getHandBelote(doc_.getDocumentElement());
    }

    public static HandBelote getHandBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        HandBelote object_ = new HandBelote();
        for (Element c: childElements_) {
            getHandBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getHandBelote(HandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getListCardBelote(_element));
            return;
        }
    }

    public static void getResultsBelote(ResultsBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_GAME)) {
            _object.setGame(getGameBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NICKNAMES)) {
            _object.getRes().setNicknames(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_USER)) {
            _object.getRes().setUser(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LOC)) {
            _object.getRes().setLoc(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        DocumentReaderCardsResultsUtil.getResultsGame(_object, _fieldName, _element);
    }

    public static RulesBelote getRulesBelote(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new RulesBelote();
        }
        return getRulesBelote(doc_.getDocumentElement());
    }

    public static RulesBelote getRulesBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesBelote object_ = new RulesBelote();
        for (Element c: childElements_) {
            getRulesBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getRulesBelote(RulesBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_MIXED_CARDS)) {
            _object.getCommon().setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_DECLARES)) {
            _object.setAllowedDeclares(getMapDeclaresBeloteBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_UNDER_TRUMP_FOE)) {
            _object.setUnderTrumpFoe(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TRUMP_PARTNER)) {
            _object.setTrumpPartner(getBeloteTrumpPartner(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(getMapBidBeloteBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEALING)) {
            _object.setDealing(getDealingBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CLASSIC_COUNT_POINTS)) {
            _object.setClassicCountPoints(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.getCommon().setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static TrickBelote getTrickBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrickBelote object_ = new TrickBelote();
        for (Element c: childElements_) {
            getTrickBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTrickBelote(TrickBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_STARTER)) {
            _object.setStarter(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getHandBelote(_element));
            return;
        }
    }

    public static TricksHandsBelote getTricksHandsBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsBelote object_ = new TricksHandsBelote();
        for (Element c: childElements_) {
            getTricksHandsBelote(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsBelote(TricksHandsBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_RULES)) {
            _object.setRules(getRulesBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PRENEUR)) {
            _object.setPreneur(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(getBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS_HANDS_AT_INITIAL_STATE)) {
            _object.setCardsHandsAtInitialState(getListHandBelote(_element));
            return;
        }
    }

    private static BeloteTrumpPartner getBeloteTrumpPartner(Element _elt) {
        for (BeloteTrumpPartner e: BeloteTrumpPartner.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    }

    private static BidBelote getBidBelote(Element _elt) {
        for (BidBelote e: BidBelote.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return BidBelote.FOLD;
    }

    public static CardBelote getCardBelote(Element _elt) {
        for (CardBelote e: CardBelote.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return CardBelote.WHITE;
    }

    public static DealingBelote getDealingBelote(Element _elt) {
        for (DealingBelote e: DealingBelote.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return DealingBelote.CLASSIC_2_VS_2;
    }

    private static DeclaresBelote getDeclaresBelote(Element _elt) {
        for (DeclaresBelote e: DeclaresBelote.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return DeclaresBelote.UNDEFINED;
    }

    private static CustList<TrickBelote> getListTrickBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<TrickBelote> list_ = new CustList<TrickBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getTrickBelote(c));
        }
        return list_;
    }

    private static EnumList<CardBelote> getListCardBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<CardBelote> list_ = new EnumList<CardBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getCardBelote(c));
        }
        return list_;
    }

    private static EnumMap<BidBelote, BoolVal> getMapBidBeloteBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<BidBelote,BoolVal> map_ = new EnumMap<BidBelote,BoolVal>(cap_);
        CustList<BidBelote> keys_ = new CustList<BidBelote>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getBidBelote(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolVal(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<DeclaresBelote,Boolean> getMapDeclaresBeloteBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<DeclaresBelote,Boolean> map_ = new EnumMap<DeclaresBelote,Boolean>(cap_);
        CustList<DeclaresBelote> keys_ = new CustList<DeclaresBelote>(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getDeclaresBelote(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static CustList<BidBeloteSuit> getListBidBeloteSuit(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<BidBeloteSuit> list_ = new CustList<BidBeloteSuit>(cap_);
        for (Element c: childElements_) {
            list_.add(getBidBeloteSuit(c));
        }
        return list_;
    }

    private static CustList<DeclareHandBelote> getListDeclareHandBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<DeclareHandBelote> list_ = new CustList<DeclareHandBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getDeclareHandBelote(c));
        }
        return list_;
    }

    private static CustList<HandBelote> getListHandBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<HandBelote> list_ = new CustList<HandBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandBelote(c));
        }
        return list_;
    }

    public static CustList<GameBelote> getListGameBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<GameBelote> list_ = new CustList<GameBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getGameBelote(c));
        }
        return list_;
    }
}