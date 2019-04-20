package cards.tarot.sml;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.tarot.DealTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TrickTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.EndDealTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.ModeTarot;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.StringList;
public final class DocumentReaderTarotUtil {

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final String FIELD_ALLOWED_BIDS = "allowedBids";
    private static final String FIELD_ALLOWED_HANDFULS = "allowedHandfuls";
    private static final String FIELD_BID = "bid";
    private static final String FIELD_BIDS = "bids";
    private static final String FIELD_CALLED_CARDS = "calledCards";
    private static final String FIELD_CARDS = "cards";
    private static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "cardsHandsAtInitialState";
    private static final String FIELD_CLOCKWISE = "clockwise";
    private static final String FIELD_CONFIDENCE = "confidence";
    private static final String FIELD_DEAL = "deal";
    private static final String FIELD_DEALER = "dealer";
    private static final String FIELD_DEALING = "dealing";
    private static final String FIELD_DECLARES_HANDFULS = "declaresHandfuls";
    private static final String FIELD_DECLARES_MISERES = "declaresMiseres";
    private static final String FIELD_DECLARES_SLAM = "declaresSlam";
    private static final String FIELD_DECREASING = "decreasing";
    private static final String FIELD_DISCARD_AFTER_CALL = "discardAfterCall";
    private static final String FIELD_DISTRIBUTION = "distribution";
    private static final String FIELD_END_DEAL_TAROT = "endDealTarot";
    private static final String FIELD_GAME = "game";
    private static final String FIELD_HANDFULS = "handfuls";
    private static final String FIELD_LOC = "loc";
    private static final String FIELD_MISERES = "miseres";
    private static final String FIELD_MIXED_CARDS = "mixedCards";
    private static final String FIELD_MODE = "mode";
    private static final String FIELD_NB_DEALS = "nbDeals";
    private static final String FIELD_NICKNAMES = "nicknames";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_PRENEUR = "preneur";
    private static final String FIELD_PROGRESSING_TRICK = "progressingTrick";
    private static final String FIELD_RULES = "rules";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_SEEN_BY_ALL_PLAYERS = "seenByAllPlayers";
    private static final String FIELD_SMALL_BOUND = "smallBound";
    private static final String FIELD_STARTER = "starter";
    private static final String FIELD_SUITS = "suits";
    private static final String FIELD_TRICKS = "tricks";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_USER = "user";

    private static DealTarot getDealTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealTarot object_ = new DealTarot();
        for (Element c: childElements_) {
            getDealTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealTarot(DealTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getListHandTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.setNbDeals(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
    }

    public static DisplayingTarot getDisplayingTarot(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new DisplayingTarot();
        }
        return getDisplayingTarot(doc_.getDocumentElement());
    }

    private static DisplayingTarot getDisplayingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingTarot object_ = new DisplayingTarot();
        for (Element c: childElements_) {
            getDisplayingTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingTarot(DisplayingTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CLOCKWISE)) {
            _object.setClockwise(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUITS)) {
            _object.setSuits(DocumentReaderCardsCommonUtil.getListSuit(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DECREASING)) {
            _object.setDecreasing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static GameTarot getGameTarot(Document _doc) {
        return getGameTarot(_doc.getDocumentElement());
    }

    private static GameTarot getGameTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GameTarot object_ = new GameTarot();
        for (Element c: childElements_) {
            getGameTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGameTarot(GameTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getDealTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DECLARES_HANDFULS)) {
            _object.setDeclaresHandfuls(getListListHandfuls(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DECLARES_MISERES)) {
            _object.setDeclaresMiseres(getListListMiseres(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DECLARES_SLAM)) {
            _object.setDeclaresSlam(DocumentReaderCoreUtil.getBooleanList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SMALL_BOUND)) {
            _object.setSmallBound(DocumentReaderCoreUtil.getBooleanList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HANDFULS)) {
            _object.setHandfuls(getListHandTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONFIDENCE)) {
            _object.setConfidence(DocumentReaderCoreUtil.getListBooleanList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CALLED_CARDS)) {
            _object.setCalledCards(getHandTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BIDS)) {
            _object.setBids(getListBidTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RULES)) {
            _object.setRules(getRulesTarot(_element));
            return;
        }
    }

    public static HandTarot getHandTarot(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new HandTarot();
        }
        return getHandTarot(doc_.getDocumentElement());
    }

    public static HandTarot getHandTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        HandTarot object_ = new HandTarot();
        for (Element c: childElements_) {
            getHandTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getHandTarot(HandTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getListCardTarot(_element));
            return;
        }
    }

    public static void getResultsTarot(ResultsTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GAME)) {
            _object.setGame(getGameTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NICKNAMES)) {
            _object.setNicknames(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USER)) {
            _object.setUser(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOC)) {
            _object.setLoc(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        DocumentReaderCardsResultsUtil.getResultsGame(_object, _fieldName, _element);
    }

    public static RulesTarot getRulesTarot(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new RulesTarot();
        }
        return getRulesTarot(doc_.getDocumentElement());
    }

    public static RulesTarot getRulesTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesTarot object_ = new RulesTarot();
        for (Element c: childElements_) {
            getRulesTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getRulesTarot(RulesTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MIXED_CARDS)) {
            _object.setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MISERES)) {
            _object.setMiseres(getListMiseres(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(getMapBidTarotBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MODE)) {
            _object.setMode(getModeTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DEALING)) {
            _object.setDealing(getDealingTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ALLOWED_HANDFULS)) {
            _object.setAllowedHandfuls(getMapHandfulsInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_END_DEAL_TAROT)) {
            _object.setEndDealTarot(getEndDealTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISCARD_AFTER_CALL)) {
            _object.setDiscardAfterCall(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static TrickTarot getTrickTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrickTarot object_ = new TrickTarot();
        for (Element c: childElements_) {
            getTrickTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTrickTarot(TrickTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_STARTER)) {
            _object.setStarter(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getHandTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SEEN_BY_ALL_PLAYERS)) {
            _object.setSeenByAllPlayers(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static TricksHandsTarot getTricksHandsTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsTarot object_ = new TricksHandsTarot();
        for (Element c: childElements_) {
            getTricksHandsTarot(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsTarot(TricksHandsTarot _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PRENEUR)) {
            _object.setPreneur(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BID)) {
            _object.setBid(getBidTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CARDS_HANDS_AT_INITIAL_STATE)) {
            _object.setCardsHandsAtInitialState(getListHandTarot(_element));
            return;
        }
    }

    public static BidTarot getBidTarot(Element _elt) {
        for (BidTarot e: BidTarot.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    public static CardTarot getCardTarot(Element _elt) {
        for (CardTarot e: CardTarot.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    public static DealingTarot getDealingTarot(Element _elt) {
        for (DealingTarot e: DealingTarot.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static EndDealTarot getEndDealTarot(Element _elt) {
        for (EndDealTarot e: EndDealTarot.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    public static Handfuls getHandfuls(Element _elt) {
        for (Handfuls e: Handfuls.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static Miseres getMiseres(Element _elt) {
        for (Miseres e: Miseres.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static ModeTarot getModeTarot(Element _elt) {
        for (ModeTarot e: ModeTarot.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static CustList<TrickTarot> getListTrickTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<TrickTarot> list_ = new CustList<TrickTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getTrickTarot(c));
        }
        return list_;
    }

    private static CustList<EnumList<Handfuls>> getListListHandfuls(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EnumList<Handfuls>> list_ = new CustList<EnumList<Handfuls>>(cap_);
        for (Element c: childElements_) {
            list_.add(getListHandfuls(c));
        }
        return list_;
    }

    public static EnumList<BidTarot> getListBidTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<BidTarot> list_ = new EnumList<BidTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getBidTarot(c));
        }
        return list_;
    }

    private static EnumList<CardTarot> getListCardTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<CardTarot> list_ = new EnumList<CardTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getCardTarot(c));
        }
        return list_;
    }

    public static EnumList<Handfuls> getListHandfuls(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<Handfuls> list_ = new EnumList<Handfuls>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandfuls(c));
        }
        return list_;
    }

    public static EnumList<Miseres> getListMiseres(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<Miseres> list_ = new EnumList<Miseres>(cap_);
        for (Element c: childElements_) {
            list_.add(getMiseres(c));
        }
        return list_;
    }

    private static EnumMap<BidTarot,Boolean> getMapBidTarotBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<BidTarot,Boolean> map_ = new EnumMap<BidTarot,Boolean>(cap_);
        CustList<BidTarot> keys_ = new CustList<BidTarot>(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getBidTarot(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static EnumMap<Handfuls,Integer> getMapHandfulsInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Handfuls,Integer> map_ = new EnumMap<Handfuls,Integer>(cap_);
        CustList<Handfuls> keys_ = new CustList<Handfuls>(cap_);
        CustList<Integer> values_ = new CustList<Integer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getHandfuls(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getInteger(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static CustList<GameTarot> getListGameTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<GameTarot> list_ = new CustList<GameTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getGameTarot(c));
        }
        return list_;
    }
    private static EqList<HandTarot> getListHandTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<HandTarot> list_ = new EqList<HandTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandTarot(c));
        }
        return list_;
    }

    private static EqList<EnumList<Miseres>> getListListMiseres(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<EnumList<Miseres>> list_ = new EqList<EnumList<Miseres>>(cap_);
        for (Element c: childElements_) {
            list_.add(getListMiseres(c));
        }
        return list_;
    }

}