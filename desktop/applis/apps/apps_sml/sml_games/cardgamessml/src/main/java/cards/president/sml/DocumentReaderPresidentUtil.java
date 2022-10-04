package cards.president.sml;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
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
import cards.president.enumerations.Playing;
import cards.president.enumerations.PresidentCardsRetrieverUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderPresidentUtil {

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final String FIELD_CARDS = "cards";
    private static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "cardsHandsAtInitialState";
    private static final String FIELD_CLOCKWISE = "clockwise";
    private static final String FIELD_DEAL = "deal";
    private static final String FIELD_DEALER = "dealer";
    private static final String FIELD_DECREASING = "decreasing";
    private static final String FIELD_DISTRIBUTION = "distribution";
    private static final String FIELD_EQUALTY = "equalty";
    private static final String FIELD_GAME = "game";
    private static final String FIELD_HAS_TO_PLAY = "hasToPlay";
    private static final String FIELD_LOC = "loc";
    private static final String FIELD_LOOSER_STARTS_FIRST = "looserStartsFirst";
    private static final String FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS = "loosingIfFinishByBestCards";
    private static final String FIELD_MIXED_CARDS = "mixedCards";
    private static final String FIELD_NB_DEALS = "nbDeals";
    private static final String FIELD_NB_PLAYERS = "nbPlayers";
    private static final String FIELD_NB_STACKS = "nbStacks";
    private static final String FIELD_NICKNAMES = "nicknames";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_NUMBER_MAX_SWITCHED_CARDS = "numberMaxSwitchedCards";
    private static final String FIELD_POSSIBLE_REVERSING = "possibleReversing";
    private static final String FIELD_PROGRESSING_TRICK = "progressingTrick";
    private static final String FIELD_RANKS = "ranks";
    private static final String FIELD_REVERSED = "reversed";
    private static final String FIELD_RULES = "rules";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_SUITS = "suits";
    private static final String FIELD_SWITCH_CARDS = "switchCards";
    private static final String FIELD_SWITCHED_CARDS = "switchedCards";
    private static final String FIELD_TRICKS = "tricks";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_USER = "user";

    private static DealPresident getDealPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DealPresident object_ = new DealPresident();
        for (Element c: childElements_) {
            getDealPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDealPresident(DealPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getListHandPresident(_element));
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

    public static DisplayingPresident getDisplayingPresident(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new DisplayingPresident();
        }
        return getDisplayingPresident(doc_.getDocumentElement());
    }

    private static DisplayingPresident getDisplayingPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DisplayingPresident object_ = new DisplayingPresident();
        for (Element c: childElements_) {
            getDisplayingPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDisplayingPresident(DisplayingPresident _object, String _fieldName, Element _element) {
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
        if (StringUtil.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static GamePresident getGamePresident(Document _doc) {
        return getGamePresident(_doc.getDocumentElement());
    }

    private static GamePresident getGamePresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GamePresident object_ = new GamePresident();
        for (Element c: childElements_) {
            getGamePresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGamePresident(GamePresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_TYPE)) {
            _object.setType(DocumentReaderCardsCommonUtil.getGameType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DEAL)) {
            _object.setDeal(getDealPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickPresident(_element));
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
            _object.setRules(getRulesPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RANKS)) {
            _object.setRanks(DocumentReaderCoreUtil.getListByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SWITCHED_CARDS)) {
            _object.setSwitchedCards(getMapByteHandPresident(_element));
            return;
        }
    }

    public static HandPresident getHandPresident(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new HandPresident();
        }
        return getHandPresident(doc_.getDocumentElement());
    }

    public static HandPresident getHandPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        HandPresident object_ = new HandPresident();
        for (Element c: childElements_) {
            getHandPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getHandPresident(HandPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getListCardPresident(_element));
            return;
        }
    }

    public static void getResultsPresident(ResultsPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_GAME)) {
            _object.setGame(getGamePresident(_element));
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

    public static RulesPresident getRulesPresident(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new RulesPresident();
        }
        return getRulesPresident(doc_.getDocumentElement());
    }

    public static RulesPresident getRulesPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        RulesPresident object_ = new RulesPresident();
        for (Element c: childElements_) {
            getRulesPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getRulesPresident(RulesPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_MIXED_CARDS)) {
            _object.getCommon().setMixedCards(DocumentReaderCardsCommonUtil.getMixCardsChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_PLAYERS)) {
            _object.setNbPlayers(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_STACKS)) {
            _object.setNbStacks(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_EQUALTY)) {
            _object.setEqualty(getEqualtyPlaying(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NB_DEALS)) {
            _object.getCommon().setNbDeals(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POSSIBLE_REVERSING)) {
            _object.setPossibleReversing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_HAS_TO_PLAY)) {
            _object.setHasToPlay(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS)) {
            _object.setLoosingIfFinishByBestCards(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SWITCH_CARDS)) {
            _object.setSwitchCards(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LOOSER_STARTS_FIRST)) {
            _object.setLooserStartsFirst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static TrickPresident getTrickPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrickPresident object_ = new TrickPresident();
        for (Element c: childElements_) {
            getTrickPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTrickPresident(TrickPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS)) {
            _object.setCards(getListHandPresident(_element));
            return;
        }
    }

    public static TricksHandsPresident getTricksHandsPresident(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TricksHandsPresident object_ = new TricksHandsPresident();
        for (Element c: childElements_) {
            getTricksHandsPresident(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTricksHandsPresident(TricksHandsPresident _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_REVERSED)) {
            _object.setReversed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DISTRIBUTION)) {
            _object.setDistribution(getDealPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TRICKS)) {
            _object.setTricks(getListTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_PROGRESSING_TRICK)) {
            _object.setProgressingTrick(getTrickPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_NUMBER_MAX_SWITCHED_CARDS)) {
            _object.setNumberMaxSwitchedCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_RANKS)) {
            _object.setRanks(DocumentReaderCoreUtil.getListByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SWITCHED_CARDS)) {
            _object.setSwitchedCards(getMapByteHandPresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CARDS_HANDS_AT_INITIAL_STATE)) {
            _object.setCardsHandsAtInitialState(getListHandPresident(_element));
            return;
        }
    }

    public static CardPresident getCardPresident(Element _elt) {
        return PresidentCardsRetrieverUtil.toCardPresident(_elt.getAttribute(ATTR_VALUE));
    }

    private static EqualtyPlaying getEqualtyPlaying(Element _elt) {
        return PresidentCardsRetrieverUtil.toEqualtyPlaying(_elt.getAttribute(ATTR_VALUE));
    }

    public static Playing getPlaying(Element _elt) {
        for (Playing e: Playing.values()) {
            if (StringUtil.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Playing.CAN_PLAY;
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
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<HandPresident> values_ = new CustList<HandPresident>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
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