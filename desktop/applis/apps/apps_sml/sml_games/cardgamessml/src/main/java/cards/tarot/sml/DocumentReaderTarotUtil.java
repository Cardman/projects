package cards.tarot.sml;

import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.core.DocumentWriterCoreUtil;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderTarotUtil {

    private DocumentReaderTarotUtil() {
    }

    private static DealTarot getDealTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealTarot object_ = new DealTarot();
        for (Element c: childElements_) {
            getDealTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealTarot(DealTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DEAL)) {
            _object.setDeal(getListHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        _object.setNbDeals(DocumentReaderCoreUtil.getLong(_element));
    }

    public static DisplayingTarot getDisplayingTarot(String _string) {
        return getDisplayingTarot(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    private static DisplayingTarot getDisplayingTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingTarot object_ = new DisplayingTarot();
        for (Element c: childElements_) {
            getDisplayingTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingTarot(DisplayingTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_CLOCKWISE)) {
            _object.getDisplaying().setClockwise(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_SUITS)) {
            _object.getDisplaying().setSuits(DocumentReaderCardsCommonUtil.getListSuit(_element));
            return;
        }
        _object.getDisplaying().setDecreasing(DocumentReaderCoreUtil.getBoolean(_element));
    }

    public static GameTarot getGameTarot(Document _doc) {
        return getGameTarot(_doc.getDocumentElement());
    }

    private static GameTarot getGameTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GameTarot object_ = new GameTarot();
        for (Element c: childElements_) {
            getGameTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGameTarot(GameTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DEAL)) {
            _object.setDeal(getDealTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DECLARES_HANDFULS)) {
            _object.setDeclaresHandfuls(getListListHandfuls(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DECLARES_MISERES)) {
            _object.setDeclaresMiseres(getListListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DECLARES_SLAM)) {
            _object.setDeclaresSlam(DocumentReaderCoreUtil.getBoolValList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_SMALL_BOUND)) {
            _object.setSmallBound(DocumentReaderCoreUtil.getBoolValList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_HANDFULS)) {
            _object.setHandfuls(getListHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_CONFIDENCE)) {
            _object.setConfidence(DocumentReaderCoreUtil.getListBooleanList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_CALLED_CARDS)) {
            _object.setCalledCards(getHandTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_BIDS)) {
            _object.setBids(getListBidTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        _object.setRules(getRulesTarot(_element));
    }

    public static HandTarot getHandTarot(String _string) {
        return getHandTarot(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static HandTarot getHandTarot(Element _element) {
        HandTarot object_ = new HandTarot();
        object_.setCards(getListCardTarot(_element));
        return object_;
    }

    public static void getResultsTarot(ResultsTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_GAME)) {
            _object.setGame(getGameTarot(_element));
            return;
        }
        DocumentReaderCardsResultsUtil.getResultsGame(_fieldName, _element, _object.getRes());
    }

    public static RulesTarot getRulesTarot(String _string) {
        return getRulesTarot(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static RulesTarot getRulesTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesTarot object_ = new RulesTarot();
        for (Element c: childElements_) {
            getRulesTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getRulesTarot(RulesTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_MIXED_CARDS)) {
            _object.getCommon().setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_MISERES)) {
            _object.setMiseres(getListMiseres(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(getMapBidTarotBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_MODE)) {
            _object.setMode(getModeTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DEALING)) {
            _object.setDealing(getDealingTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_ALLOWED_HANDFULS)) {
            _object.setAllowedHandfuls(getMapHandfulsInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_END_DEAL_TAROT)) {
            _object.setEndDealTarot(getEndDealTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_NB_DEALS)) {
            _object.getCommon().setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DISCARD_AFTER_CALL)) {
            _object.setDiscardAfterCall(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setAllowPlayCalledSuit(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static TrickTarot getTrickTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrickTarot object_ = new TrickTarot();
        for (Element c: childElements_) {
            getTrickTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTrickTarot(TrickTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_STARTER)) {
            _object.setStarter(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_CARDS)) {
            _object.setCards(getHandTarot(_element));
            return;
        }
        _object.setSeenByAllPlayers(DocumentReaderCoreUtil.getBoolean(_element));
    }

    public static TricksHandsTarot getTricksHandsTarot(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsTarot object_ = new TricksHandsTarot();
        for (Element c: childElements_) {
            getTricksHandsTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsTarot(TricksHandsTarot _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_PRENEUR)) {
            _object.setPreneur(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterTarotUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickTarot(_element));
            return;
        }
        _object.setCardsHandsAtInitialState(getListHandTarot(_element));
    }

    public static BidTarot getBidTarot(Element _elt) {
        return TarotCardsRetrieverUtil.toBidTarot(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    public static CardTarot getCardTarot(Element _elt) {
        return TarotCardsRetrieverUtil.toCardTarot(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    public static DealingTarot getDealingTarot(Element _elt) {
        return TarotCardsRetrieverUtil.toDealingTarot(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    private static EndDealTarot getEndDealTarot(Element _elt) {
        return TarotCardsRetrieverUtil.toEndDealTarot(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    public static Handfuls getHandfuls(Element _elt) {
        return TarotCardsRetrieverUtil.toHandfuls(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    private static Miseres getMiseres(Element _elt) {
        return TarotCardsRetrieverUtil.toMiseres(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
    }

    private static ModeTarot getModeTarot(Element _elt) {
        return TarotCardsRetrieverUtil.toModeTarot(_elt.getAttribute(DocumentWriterCoreUtil.VALUE));
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

    private static CustList<IdList<Handfuls>> getListListHandfuls(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<IdList<Handfuls>> list_ = new CustList<IdList<Handfuls>>(cap_);
        for (Element c: childElements_) {
            list_.add(getListHandfuls(c));
        }
        return list_;
    }

    public static IdList<BidTarot> getListBidTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<BidTarot> list_ = new IdList<BidTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getBidTarot(c));
        }
        return list_;
    }

    private static IdList<CardTarot> getListCardTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<CardTarot> list_ = new IdList<CardTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getCardTarot(c));
        }
        return list_;
    }

    public static IdList<Handfuls> getListHandfuls(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<Handfuls> list_ = new IdList<Handfuls>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandfuls(c));
        }
        return list_;
    }

    public static IdList<Miseres> getListMiseres(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<Miseres> list_ = new IdList<Miseres>(cap_);
        for (Element c: childElements_) {
            list_.add(getMiseres(c));
        }
        return list_;
    }

    private static IdMap<BidTarot, BoolVal> getMapBidTarotBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<BidTarot,BoolVal> map_ = new IdMap<BidTarot,BoolVal>(cap_);
        CustList<BidTarot> keys_ = new CustList<BidTarot>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getBidTarot(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static IdMap<Handfuls,Integer> getMapHandfulsInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Handfuls,Integer> map_ = new IdMap<Handfuls,Integer>(cap_);
        CustList<Handfuls> keys_ = new CustList<Handfuls>(cap_);
        CustList<Integer> values_ = new CustList<Integer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getHandfuls(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getInteger(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
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
    private static CustList<HandTarot> getListHandTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<HandTarot> list_ = new CustList<HandTarot>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandTarot(c));
        }
        return list_;
    }

    private static CustList<IdList<Miseres>> getListListMiseres(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<IdList<Miseres>> list_ = new CustList<IdList<Miseres>>(cap_);
        for (Element c: childElements_) {
            list_.add(getListMiseres(c));
        }
        return list_;
    }

    public static ResultsTarot resultsTarot(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsTarot object_ = new ResultsTarot();
        for (Element c: childElements_) {
            getResultsTarot(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }
}