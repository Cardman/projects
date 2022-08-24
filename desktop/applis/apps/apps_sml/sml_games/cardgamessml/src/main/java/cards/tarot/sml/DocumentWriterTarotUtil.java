package cards.tarot.sml;
import cards.consts.sml.DocumentWriterCardsCommonUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
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
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.*;
import code.util.core.BoolVal;

public final class DocumentWriterTarotUtil {

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
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
    private static final String FIELD_ALLOW_PLAY_CALLED_SUIT = "allowPlayCalledSuit";
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
    private static final String TYPE_BID_TAROT = "BidTarot";
    private static final String TYPE_CARD_TAROT = "CardTarot";
    private static final String TYPE_DEAL_TAROT = "DealTarot";
    private static final String TYPE_DEALING_TAROT = "DealingTarot";
    private static final String TYPE_DISPLAYING_TAROT = "DisplayingTarot";
    private static final String TYPE_END_DEAL_TAROT = "EndDealTarot";
    private static final String TYPE_GAME_TAROT = "GameTarot";
    private static final String TYPE_HAND_TAROT = "HandTarot";
    private static final String TYPE_HANDFULS = "Handfuls";
    private static final String TYPE_LIST = "List";
    private static final String TYPE_MAP = "Map";
    private static final String TYPE_MISERES = "Miseres";
    private static final String TYPE_MODE_TAROT = "ModeTarot";
    private static final String TYPE_RULES_TAROT = "RulesTarot";
    private static final String TYPE_TRICK_TAROT = "TrickTarot";
    private static final String TYPE_TRICKS_HANDS_TAROT = "TricksHandsTarot";

    private static Element setDealTarot(DealTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DEAL_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealTarot(_object,element_,_document);
        return element_;
    }

    private static void setDealTarot(DealTarot _object, Element _element, Document _document) {
        _element.appendChild(setListHandTarot(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNbDeals(),FIELD_NB_DEALS,_document));
    }

    public static String setDisplayingTarot(DisplayingTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDisplayingTarot(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setDisplayingTarot(DisplayingTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DISPLAYING_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDisplayingTarot(_object,element_,_document);
        return element_;
    }

    private static void setDisplayingTarot(DisplayingTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isClockwise(),FIELD_CLOCKWISE,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setListSuit(_object.getDisplaying().getSuits(),FIELD_SUITS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isDecreasing(),FIELD_DECREASING,_document));
    }


    public static String setGameTarot(GameTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGameTarot(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setGameTarot(GameTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GAME_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGameTarot(_object,element_,_document);
        return element_;
    }

    private static void setGameTarot(GameTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setGameType(_object.getType(),FIELD_TYPE,_document));
        _element.appendChild(setDealTarot(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(setListListHandfuls(_object.getDeclaresHandfuls(),FIELD_DECLARES_HANDFULS,_document));
        _element.appendChild(setListListMiseres(_object.getDeclaresMiseres(),FIELD_DECLARES_MISERES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolValList(_object.getDeclaresSlam(),FIELD_DECLARES_SLAM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolValList(_object.getSmallBound(),FIELD_SMALL_BOUND,_document));
        _element.appendChild(setListHandTarot(_object.getHandfuls(),FIELD_HANDFULS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListBooleanList(_object.getConfidence(),FIELD_CONFIDENCE,_document));
        _element.appendChild(setTrickTarot(_object.getProgressingTrick(),FIELD_PROGRESSING_TRICK,_document));
        _element.appendChild(setListTrickTarot(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setHandTarot(_object.getCalledCards(),FIELD_CALLED_CARDS,_document));
        _element.appendChild(setListBidTarot(_object.getBids(),FIELD_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getScores(),FIELD_SCORES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNumber(),FIELD_NUMBER,_document));
        _element.appendChild(setRulesTarot(_object.getRules(),FIELD_RULES,_document));
    }

    public static String setHandTarot(HandTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setHandTarot(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setHandTarot(HandTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_HAND_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setHandTarot(_object,element_,_document);
        return element_;
    }

    private static void setHandTarot(HandTarot _object, Element _element, Document _document) {
        _element.appendChild(setListCardTarot(_object.getCards(),FIELD_CARDS,_document));
    }

    public static void setResultsTarot(ResultsTarot _object, Element _element, Document _document) {
        _element.appendChild(setGameTarot(_object.getGame(),FIELD_GAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getRes().getNicknames(),FIELD_NICKNAMES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getRes().getUser(),FIELD_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getRes().getLoc(),FIELD_LOC,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_object, _element, _document);
    }

    public static String setRulesTarot(RulesTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesTarot(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setRulesTarot(RulesTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_RULES_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setRulesTarot(_object,element_,_document);
        return element_;
    }

    private static void setRulesTarot(RulesTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setMixCardsChoice(_object.getCommon().getMixedCards(),FIELD_MIXED_CARDS,_document));
        _element.appendChild(setListMiseres(_object.getMiseres(),FIELD_MISERES,_document));
        _element.appendChild(setMapBidTarotBoolean(_object.getAllowedBids(),FIELD_ALLOWED_BIDS,_document));
        _element.appendChild(setModeTarot(_object.getMode(),FIELD_MODE,_document));
        _element.appendChild(setDealingTarot(_object.getDealing(),FIELD_DEALING,_document));
        _element.appendChild(setMapHandfulsInteger(_object.getAllowedHandfuls(),FIELD_ALLOWED_HANDFULS,_document));
        _element.appendChild(setEndDealTarot(_object.getEndDealTarot(),FIELD_END_DEAL_TAROT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getCommon().getNbDeals(),FIELD_NB_DEALS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDiscardAfterCall(),FIELD_DISCARD_AFTER_CALL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAllowPlayCalledSuit(),FIELD_ALLOW_PLAY_CALLED_SUIT,_document));
    }

    private static Element setTrickTarot(TrickTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICK_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrickTarot(_object,element_,_document);
        return element_;
    }

    private static void setTrickTarot(TrickTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getStarter(),FIELD_STARTER,_document));
        _element.appendChild(setHandTarot(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSeenByAllPlayers(),FIELD_SEEN_BY_ALL_PLAYERS,_document));
    }

    public static String setTricksHandsTarot(TricksHandsTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setTricksHandsTarot(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setTricksHandsTarot(TricksHandsTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICKS_HANDS_TAROT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTricksHandsTarot(_object,element_,_document);
        return element_;
    }

    private static void setTricksHandsTarot(TricksHandsTarot _object, Element _element, Document _document) {
        _element.appendChild(setDealTarot(_object.getDistribution(),FIELD_DISTRIBUTION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPreneur(),FIELD_PRENEUR,_document));
        _element.appendChild(setListTrickTarot(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setListHandTarot(_object.getCardsHandsAtInitialState(),FIELD_CARDS_HANDS_AT_INITIAL_STATE,_document));
    }

    public static Element setBidTarot(BidTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_BID_TAROT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setCardTarot(CardTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CARD_TAROT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setDealingTarot(DealingTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DEALING_TAROT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setEndDealTarot(EndDealTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_END_DEAL_TAROT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setHandfuls(Handfuls _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_HANDFULS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setMiseres(Miseres _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MISERES);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setModeTarot(ModeTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MODE_TAROT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setListTrickTarot(CustList<TrickTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickTarot s: _object) {
            elt_.appendChild(setTrickTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListListHandfuls(CustList<EnumList<Handfuls>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EnumList<Handfuls> s: _object) {
            elt_.appendChild(setListHandfuls(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListBidTarot(EnumList<BidTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (BidTarot s: _object) {
            elt_.appendChild(setBidTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardTarot(EnumList<CardTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardTarot s: _object) {
            elt_.appendChild(setCardTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListHandfuls(EnumList<Handfuls> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Handfuls s: _object) {
            elt_.appendChild(setHandfuls(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListMiseres(EnumList<Miseres> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Miseres s: _object) {
            elt_.appendChild(setMiseres(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapBidTarotBoolean(IdMap<BidTarot, BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<BidTarot, BoolVal> s: _object.entryList()) {
            Element sub_ = setBidTarot(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapHandfulsInteger(IdMap<Handfuls,Integer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Handfuls, Integer> s: _object.entryList()) {
            Element sub_ = setHandfuls(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setInteger(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setListHandTarot(CustList<HandTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandTarot s: _object) {
            elt_.appendChild(setHandTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListListMiseres(CustList<EnumList<Miseres>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EnumList<Miseres> s: _object) {
            elt_.appendChild(setListMiseres(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGameTarot(CustList<GameTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GameTarot s: _object) {
            elt_.appendChild(setGameTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }
}
