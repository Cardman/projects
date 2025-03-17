package cards.president.sml;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
import cards.president.DealPresident;
import cards.president.DisplayingPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TrickPresident;
import cards.president.TricksHandsPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsRetrieverUtil;
import code.sml.Document;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderPresidentUtil {

    private DocumentReaderPresidentUtil(){
    }

    private static DealPresident getDealPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealPresident object_ = new DealPresident();
        for (Element c: childElements_) {
            getDealPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealPresident(DealPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_DEAL)) {
            _object.setDeal(getListHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_DEALER)) {
            _object.setDealer(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        _object.setNbDeals(DocumentReaderCoreUtil.getLong(_element));
    }

    public static DisplayingPresident getDisplayingPresident(String _string) {
        return getDisplayingPresident(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    private static DisplayingPresident getDisplayingPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingPresident object_ = new DisplayingPresident();
        for (Element c: childElements_) {
            getDisplayingPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingPresident(DisplayingPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_CLOCKWISE)) {
            _object.getDisplaying().setClockwise(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_SUITS)) {
            _object.getDisplaying().setSuits(DocumentReaderCardsCommonUtil.getListSuit(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_DECREASING)) {
            _object.getDisplaying().setDecreasing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
    }

    public static GamePresident getGamePresident(Document _doc) {
        return getGamePresident(_doc.getDocumentElement());
    }

    private static GamePresident getGamePresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GamePresident object_ = new GamePresident();
        for (Element c: childElements_) {
            getGamePresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGamePresident(GamePresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_DEAL)) {
            _object.setDeal(getDealPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListLong(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_RULES)) {
            _object.setRules(getRulesPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_RANKS)) {
            _object.setRanks(DocumentReaderCoreUtil.getListInteger(_element));
            return;
        }
        _object.setSwitchedCards(getMapByteHandPresident(_element));
    }

    public static HandPresident getHandPresident(String _string) {
        return getHandPresident(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static HandPresident getHandPresident(Element _element) {
        HandPresident object_ = new HandPresident();
        object_.setCards(getListCardPresident(_element));
        return object_;
    }

    public static void getResultsPresident(ResultsPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_GAME)) {
            _object.setGame(getGamePresident(_element));
            return;
        }
        DocumentReaderCardsResultsUtil.getResultsGame(_fieldName, _element, _object.getRes());
    }

    public static RulesPresident getRulesPresident(String _string) {
        return getRulesPresident(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    public static RulesPresident getRulesPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesPresident object_ = new RulesPresident();
        for (Element c: childElements_) {
            getRulesPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getRulesPresident(RulesPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_MIXED_CARDS)) {
            _object.getCommon().setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_NB_PLAYERS)) {
            _object.setNbPlayers(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_NB_STACKS)) {
            _object.setNbStacks(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_EQUALTY)) {
            _object.setEqualty(getEqualtyPlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_NB_DEALS)) {
            _object.getCommon().setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_POSSIBLE_REVERSING)) {
            _object.setPossibleReversing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_HAS_TO_PLAY)) {
            _object.setHasToPlay(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS)) {
            _object.setLoosingIfFinishByBestCards(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_SWITCH_CARDS)) {
            _object.setSwitchCards(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setLooserStartsFirst(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static TrickPresident getTrickPresident(Element _element) {
        TrickPresident object_ = new TrickPresident();
        object_.setCards(getListHandPresident(_element));
        return object_;
    }

    public static TricksHandsPresident getTricksHandsPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsPresident object_ = new TricksHandsPresident();
        for (Element c: childElements_) {
            getTricksHandsPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsPresident(TricksHandsPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_TRICKS)) {
            _object.setTricks(getListTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_NUMBER_MAX_SWITCHED_CARDS)) {
            _object.setNumberMaxSwitchedCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_RANKS)) {
            _object.setRanks(DocumentReaderCoreUtil.getListInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterPresidentUtil.FIELD_SWITCHED_CARDS)) {
            _object.setSwitchedCards(getMapByteHandPresident(_element));
            return;
        }
        _object.setCardsHandsAtInitialState(getListHandPresident(_element));
    }

    public static CardPresident getCardPresident(Element _elt) {
        return PresidentCardsRetrieverUtil.toCardPresident(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static EqualtyPlaying getEqualtyPlaying(Element _elt) {
        return PresidentCardsRetrieverUtil.toEqualtyPlaying(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static CustList<TrickPresident> getListTrickPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<TrickPresident> list_ = new CustList<TrickPresident>(cap_);
        for (Element c: childElements_) {
            list_.add(getTrickPresident(c));
        }
        return list_;
    }

    private static IdList<CardPresident> getListCardPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<CardPresident> list_ = new IdList<CardPresident>(cap_);
        for (Element c: childElements_) {
            list_.add(getCardPresident(c));
        }
        return list_;
    }

    public static CustList<GamePresident> getListGamePresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<GamePresident> list_ = new CustList<GamePresident>(cap_);
        for (Element c: childElements_) {
            list_.add(getGamePresident(c));
        }
        return list_;
    }

    private static CustList<HandPresident> getListHandPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<HandPresident> list_ = new CustList<HandPresident>(cap_);
        for (Element c: childElements_) {
            list_.add(getHandPresident(c));
        }
        return list_;
    }

    private static CustList<HandPresident> getMapByteHandPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CustList<HandPresident> map_ = new CustList<HandPresident>(cap_);
        CustList<Integer> keys_ = new CustList<Integer>(cap_);
        CustList<HandPresident> values_ = new CustList<HandPresident>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getInteger(c));
            } else {
                values_.add(getHandPresident(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.add(values_.get(i));
        }
        return map_;
    }

    public static ResultsPresident resultsPresident(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        ResultsPresident object_ = new ResultsPresident();
        for (Element c: childElements_) {
            getResultsPresident(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }
}