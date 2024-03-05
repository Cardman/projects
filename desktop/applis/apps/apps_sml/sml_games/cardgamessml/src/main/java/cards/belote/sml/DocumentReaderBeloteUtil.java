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
import cards.belote.enumerations.*;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
import code.sml.Document;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderBeloteUtil {

    private DocumentReaderBeloteUtil() {
    }

    public static BidBeloteSuit getBidBeloteSuit(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        BidBeloteSuit object_ = new BidBeloteSuit();
        for (Element c: childElements_) {
            getBidBeloteSuit(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getBidBeloteSuit(BidBeloteSuit _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_BID)) {
            _object.setBid(getBidBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_SUIT)) {
            _object.setSuit(DocumentReaderCardsCommonUtil.getSuit(_element));
            return;
        }
        _object.setPoints(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static DealBelote getDealBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealBelote object_ = new DealBelote();
        for (Element c: childElements_) {
            getDealBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealBelote(DealBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DEAL)) {
            _object.setDeal(getListHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        _object.setNbDeals(DocumentReaderCoreUtil.getLong(_element));
    }

    public static DeclareHandBelote getDeclareHandBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DeclareHandBelote object_ = new DeclareHandBelote();
        for (Element c: childElements_) {
            getDeclareHandBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDeclareHandBelote(DeclareHandBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DECLARE)) {
            _object.setDeclare(getDeclaresBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_HAND)) {
            _object.setHand(getHandBelote(_element));
            return;
        }
        _object.setPlayer(DocumentReaderCoreUtil.getByte(_element));
    }

    public static DisplayingBelote getDisplayingBelote(String _string) {
        return getDisplayingBelote(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    private static DisplayingBelote getDisplayingBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingBelote object_ = new DisplayingBelote();
        for (Element c: childElements_) {
            getDisplayingBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingBelote(DisplayingBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_CLOCKWISE)) {
            _object.getDisplaying().setClockwise(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_SUITS)) {
            _object.getDisplaying().setSuits(DocumentReaderCardsCommonUtil.getListSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DECREASING)) {
            _object.getDisplaying().setDecreasing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setOrderBeforeBids(DocumentReaderCardsCommonUtil.getOrder(_element));
    }

    public static GameBelote getGameBelote(Document _doc) {
        return getGameBelote(_doc.getDocumentElement());
    }

    private static GameBelote getGameBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GameBelote object_ = new GameBelote();
        for (Element c: childElements_) {
            getGameBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGameBelote(GameBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DEAL)) {
            _object.setDeal(getDealBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DECLARES)) {
            _object.setDeclares(getListDeclareHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DECLARES_BELOTE_REBELOTE)) {
            _object.setDeclaresBeloteRebelote(getListHandBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_WON_LAST_TRICK)) {
            _object.setWonLastTrick(DocumentReaderCoreUtil.getBoolValList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_BIDS)) {
            _object.setBids(getListBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        _object.setRules(getRulesBelote(_element));
    }

    public static HandBelote getHandBelote(String _string) {
        return getHandBelote(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static HandBelote getHandBelote(Element _element) {
        HandBelote object_ = new HandBelote();
        object_.setCards(getListCardBelote(_element));
        return object_;
    }

    public static void getResultsBelote(ResultsBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_GAME)) {
            _object.setGame(getGameBelote(_element));
            return;
        }
        DocumentReaderCardsResultsUtil.getResultsGame(_fieldName, _element, _object.getRes());
    }

    public static RulesBelote getRulesBelote(String _string) {
        return getRulesBelote(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static RulesBelote getRulesBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesBelote object_ = new RulesBelote();
        for (Element c: childElements_) {
            getRulesBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getRulesBelote(RulesBelote _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_MIXED_CARDS)) {
            _object.getCommon().setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_ALLOWED_DECLARES)) {
            _object.setAllowedDeclares(getMapDeclaresBeloteBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_UNDER_TRUMP_FOE)) {
            _object.setUnderTrumpFoe(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_TRUMP_PARTNER)) {
            _object.setTrumpPartner(getBeloteTrumpPartner(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_ALLOWED_BIDS)) {
            _object.setAllowedBids(getMapBidBeloteBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DEALING)) {
            _object.setDealing(getDealingBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_CLASSIC_COUNT_POINTS)) {
            _object.setClassicCountPoints(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.getCommon().setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static TrickBelote getTrickBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrickBelote object_ = new TrickBelote();
        for (Element c: childElements_) {
            getTrickBelote(object_, c);
        }
        return object_;
    }

    private static void getTrickBelote(TrickBelote _object, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_STARTER)) {
//            _object.setStarter(DocumentReaderCoreUtil.getByte(_element));
//            return;
//        }
        _object.setCards(getHandBelote(_element));
    }

    public static TricksHandsBelote getTricksHandsBelote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsBelote object_ = new TricksHandsBelote();
        for (Element c: childElements_) {
            getTricksHandsBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsBelote(TricksHandsBelote _object, String _fieldName, Element _element) {
//        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_RULES)) {
//            _object.setRules(getRulesBelote(_element));
//            return;
//        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_PRENEUR)) {
            _object.setPreneur(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_BID)) {
            _object.setBid(getBidBeloteSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterBeloteUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickBelote(_element));
            return;
        }
        _object.setCardsHandsAtInitialState(getListHandBelote(_element));
    }

    private static BeloteTrumpPartner getBeloteTrumpPartner(Element _elt) {
        return BeloteCardsRetrieverUtil.toBeloteTrumpPartner(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static BidBelote getBidBelote(Element _elt) {
        return BeloteCardsRetrieverUtil.toBidBelote(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static CardBelote getCardBelote(Element _elt) {
        return BeloteCardsRetrieverUtil.toCardBelote(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static DealingBelote getDealingBelote(Element _elt) {
        return BeloteCardsRetrieverUtil.toDealingBelote(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static DeclaresBelote getDeclaresBelote(Element _elt) {
        return BeloteCardsRetrieverUtil.toDeclaresBelote(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
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

    private static IdList<CardBelote> getListCardBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<CardBelote> list_ = new IdList<CardBelote>(cap_);
        for (Element c: childElements_) {
            list_.add(getCardBelote(c));
        }
        return list_;
    }

    private static IdMap<BidBelote, BoolVal> getMapBidBeloteBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<BidBelote,BoolVal> map_ = new IdMap<BidBelote,BoolVal>(cap_);
        CustList<BidBelote> keys_ = new CustList<BidBelote>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getBidBelote(c));
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
    private static IdMap<DeclaresBelote,BoolVal> getMapDeclaresBeloteBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<DeclaresBelote,BoolVal> map_ = new IdMap<DeclaresBelote,BoolVal>(cap_);
        CustList<DeclaresBelote> keys_ = new CustList<DeclaresBelote>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getDeclaresBelote(c));
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

    public static ResultsBelote resultsBelote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsBelote object_ = new ResultsBelote();
        for (Element c: childElements_) {
            getResultsBelote(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }
}