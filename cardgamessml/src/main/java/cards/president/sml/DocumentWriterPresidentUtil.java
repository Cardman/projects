package cards.president.sml;
import cards.consts.sml.DocumentWriterCardsCommonUtil;
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
import cards.president.enumerations.Playing;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EqList;
import code.util.*;
public final class DocumentWriterPresidentUtil {

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
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
    private static final String TYPE_CARD_PRESIDENT = "CardPresident";
    private static final String TYPE_DEAL_PRESIDENT = "DealPresident";
    private static final String TYPE_DISPLAYING_PRESIDENT = "DisplayingPresident";
    private static final String TYPE_EQUALTY_PLAYING = "EqualtyPlaying";
    private static final String TYPE_GAME_PRESIDENT = "GamePresident";
    private static final String TYPE_HAND_PRESIDENT = "HandPresident";
    private static final String TYPE_LIST = "List";
    private static final String TYPE_MAP = "Map";
    private static final String TYPE_PLAYING = "Playing";
    private static final String TYPE_RULES_PRESIDENT = "RulesPresident";
    private static final String TYPE_TRICK_PRESIDENT = "TrickPresident";
    private static final String TYPE_TRICKS_HANDS_PRESIDENT = "TricksHandsPresident";

    private static Element setDealPresident(DealPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DEAL_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealPresident(_object,element_,_document);
        return element_;
    }

    private static void setDealPresident(DealPresident _object, Element _element, Document _document) {
        _element.appendChild(setListHandPresident(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNbDeals(),FIELD_NB_DEALS,_document));
    }

    public static String setDisplayingPresident(DisplayingPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDisplayingPresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setDisplayingPresident(DisplayingPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DISPLAYING_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDisplayingPresident(_object,element_,_document);
        return element_;
    }

    private static void setDisplayingPresident(DisplayingPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClockwise(),FIELD_CLOCKWISE,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setListSuit(_object.getSuits(),FIELD_SUITS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDecreasing(),FIELD_DECREASING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbDeals(),FIELD_NB_DEALS,_document));
    }

    public static String setGamePresident(GamePresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGamePresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setGamePresident(GamePresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GAME_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGamePresident(_object,element_,_document);
        return element_;
    }

    private static void setGamePresident(GamePresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setGameType(_object.getType(),FIELD_TYPE,_document));
        _element.appendChild(setDealPresident(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(setTrickPresident(_object.getProgressingTrick(),FIELD_PROGRESSING_TRICK,_document));
        _element.appendChild(setListTrickPresident(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getScores(),FIELD_SCORES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNumber(),FIELD_NUMBER,_document));
        _element.appendChild(setRulesPresident(_object.getRules(),FIELD_RULES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListByte(_object.getRanks(),FIELD_RANKS,_document));
        _element.appendChild(setMapByteHandPresident(_object.getSwitchedCards(),FIELD_SWITCHED_CARDS,_document));
    }

    public static String setHandPresident(HandPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setHandPresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setHandPresident(HandPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_HAND_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setHandPresident(_object,element_,_document);
        return element_;
    }

    private static void setHandPresident(HandPresident _object, Element _element, Document _document) {
        _element.appendChild(setListCardPresident(_object.getCards(),FIELD_CARDS,_document));
    }

    public static void setResultsPresident(ResultsPresident _object, Element _element, Document _document) {
        _element.appendChild(setGamePresident(_object.getGame(),FIELD_GAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getNicknames(),FIELD_NICKNAMES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getUser(),FIELD_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLoc(),FIELD_LOC,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_object, _element, _document);
    }

    public static String setRulesPresident(RulesPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesPresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setRulesPresident(RulesPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_RULES_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setRulesPresident(_object,element_,_document);
        return element_;
    }

    private static void setRulesPresident(RulesPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setMixCardsChoice(_object.getMixedCards(),FIELD_MIXED_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbPlayers(),FIELD_NB_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbStacks(),FIELD_NB_STACKS,_document));
        _element.appendChild(setEqualtyPlaying(_object.getEqualty(),FIELD_EQUALTY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbDeals(),FIELD_NB_DEALS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPossibleReversing(),FIELD_POSSIBLE_REVERSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHasToPlay(),FIELD_HAS_TO_PLAY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLoosingIfFinishByBestCards(),FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSwitchCards(),FIELD_SWITCH_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLooserStartsFirst(),FIELD_LOOSER_STARTS_FIRST,_document));
    }

    private static Element setTrickPresident(TrickPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICK_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrickPresident(_object,element_,_document);
        return element_;
    }

    private static void setTrickPresident(TrickPresident _object, Element _element, Document _document) {
        _element.appendChild(setListHandPresident(_object.getCards(),FIELD_CARDS,_document));
    }

    public static String setTricksHandsPresident(TricksHandsPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setTricksHandsPresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setTricksHandsPresident(TricksHandsPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICKS_HANDS_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTricksHandsPresident(_object,element_,_document);
        return element_;
    }

    private static void setTricksHandsPresident(TricksHandsPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReversed(),FIELD_REVERSED,_document));
        _element.appendChild(setDealPresident(_object.getDistribution(),FIELD_DISTRIBUTION,_document));
        _element.appendChild(setListTrickPresident(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setTrickPresident(_object.getProgressingTrick(),FIELD_PROGRESSING_TRICK,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNumberMaxSwitchedCards(),FIELD_NUMBER_MAX_SWITCHED_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListByte(_object.getRanks(),FIELD_RANKS,_document));
        _element.appendChild(setMapByteHandPresident(_object.getSwitchedCards(),FIELD_SWITCHED_CARDS,_document));
        _element.appendChild(setListHandPresident(_object.getCardsHandsAtInitialState(),FIELD_CARDS_HANDS_AT_INITIAL_STATE,_document));
    }

    public static Element setCardPresident(CardPresident _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CARD_PRESIDENT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setEqualtyPlaying(EqualtyPlaying _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_EQUALTY_PLAYING);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setPlaying(Playing _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_PLAYING);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setListTrickPresident(CustList<TrickPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickPresident s: _object) {
            elt_.appendChild(setTrickPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardPresident(EnumList<CardPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardPresident s: _object) {
            elt_.appendChild(setCardPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListHandPresident(CustList<HandPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandPresident s: _object) {
            elt_.appendChild(setHandPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGamePresident(CustList<GamePresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GamePresident s: _object) {
            elt_.appendChild(setGamePresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }
    private static Element setMapByteHandPresident(ByteMap<HandPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, HandPresident> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setHandPresident(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapBytePlaying(ByteMap<Playing> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, Playing> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setPlaying(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

}