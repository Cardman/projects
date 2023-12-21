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
import cards.tarot.enumerations.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.*;
import code.util.core.BoolVal;

public final class DocumentWriterTarotUtil {

    public static final String TYPE_GAME_TAROT = "GameTarot";
    public static final String TYPE_RESULTS_TAROT = "ResultsTarot";
    public static final String TYPE_RULES_TAROT = "RulesTarot";
    public static final String TYPE_TRICKS_HANDS_TAROT = "TricksHandsTarot";
    public static final String EMPTY_STRING = "";
    public static final String FIELD_ALLOWED_BIDS = "0";
    public static final String FIELD_ALLOWED_HANDFULS = "1";
    public static final String FIELD_BIDS = "2";
    public static final String FIELD_CALLED_CARDS = "3";
    public static final String FIELD_CARDS = "4";
    public static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "5";
    public static final String FIELD_CLOCKWISE = "6";
    public static final String FIELD_CONFIDENCE = "7";
    public static final String FIELD_DEAL = "8";
    public static final String FIELD_DEALER = "9";
    public static final String FIELD_DEALING = "10";
    public static final String FIELD_DECLARES_HANDFULS = "11";
    public static final String FIELD_DECLARES_MISERES = "12";
    public static final String FIELD_DECLARES_SLAM = "13";
    public static final String FIELD_DECREASING = "14";
    public static final String FIELD_DISCARD_AFTER_CALL = "15";
    public static final String FIELD_ALLOW_PLAY_CALLED_SUIT = "16";
    public static final String FIELD_DISTRIBUTION = "17";
    public static final String FIELD_END_DEAL_TAROT = "18";
    public static final String FIELD_HANDFULS = "19";
    public static final String FIELD_MISERES = "20";
    public static final String FIELD_MIXED_CARDS = "21";
    public static final String FIELD_MODE = "22";
    public static final String FIELD_NB_DEALS = "23";
    public static final String FIELD_NUMBER = "24";
    public static final String FIELD_PRENEUR = "25";
    public static final String FIELD_PROGRESSING_TRICK = "26";
    public static final String FIELD_RULES = "27";
    public static final String FIELD_SCORES = "28";
    public static final String FIELD_SEEN_BY_ALL_PLAYERS = "29";
    public static final String FIELD_SMALL_BOUND = "30";
    public static final String FIELD_STARTER = "31";
    public static final String FIELD_SUITS = "32";
    public static final String FIELD_TRICKS = "33";
    public static final String FIELD_TYPE = "34";
    private DocumentWriterTarotUtil() {
    }

    private static Element setDealTarot(DealTarot _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        doc_.appendChild(setGameTarot(_object, EMPTY_STRING, doc_, TYPE_GAME_TAROT));
        return doc_.export();
    }

    private static Element setGameTarot(GameTarot _object, String _fieldName, Document _document) {
        return setGameTarot(_object,_fieldName,_document,DocumentWriterCoreUtil.ANON_TAG);
    }
    private static Element setGameTarot(GameTarot _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
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
        return setListCardTarot(_object.getCards(),_fieldName,_document);
    }

    public static void setResultsTarot(ResultsTarot _object, Element _element, Document _document) {
        _element.appendChild(setGameTarot(_object.getGame(),DocumentWriterCardsResultsUtil.FIELD_GAME,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_element, _document, _object.getRes());
    }

    public static String setRulesTarot(RulesTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesTarot(_object, EMPTY_STRING, doc_, TYPE_RULES_TAROT));
        return doc_.export();
    }

    public static Element setRulesTarot(RulesTarot _object, String _fieldName, Document _document) {
        return setRulesTarot(_object,_fieldName,_document,DocumentWriterCoreUtil.ANON_TAG);
    }
    public static Element setRulesTarot(RulesTarot _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrickTarot(_object,element_,_document);
        return element_;
    }

    private static void setTrickTarot(TrickTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getStarter(),FIELD_STARTER,_document));
        _element.appendChild(setHandTarot(_object.getCards(),FIELD_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSeenByAllPlayers(),FIELD_SEEN_BY_ALL_PLAYERS,_document));
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, TarotCardsExporterUtil.fromBidTarot(_object));
        return elt_;
    }

    public static Element setCardTarot(CardTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromCardTarot(_object));
        return elt_;
    }

    public static Element setDealingTarot(DealingTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromDealingTarot(_object));
        return elt_;
    }

    private static Element setEndDealTarot(EndDealTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromEndDealTarot(_object));
        return elt_;
    }

    public static Element setHandfuls(Handfuls _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromHandfuls(_object));
        return elt_;
    }

    private static Element setMiseres(Miseres _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromMiseres(_object));
        return elt_;
    }

    private static Element setModeTarot(ModeTarot _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,TarotCardsExporterUtil.fromModeTarot(_object));
        return elt_;
    }

    private static Element setListTrickTarot(CustList<TrickTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickTarot s: _object) {
            elt_.appendChild(setTrickTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListListHandfuls(CustList<IdList<Handfuls>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (IdList<Handfuls> s: _object) {
            elt_.appendChild(setListHandfuls(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListBidTarot(IdList<BidTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (BidTarot s: _object) {
            elt_.appendChild(setBidTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardTarot(IdList<CardTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardTarot s: _object) {
            elt_.appendChild(setCardTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListHandfuls(IdList<Handfuls> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Handfuls s: _object) {
            elt_.appendChild(setHandfuls(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListMiseres(IdList<Miseres> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Miseres s: _object) {
            elt_.appendChild(setMiseres(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapBidTarotBoolean(IdMap<BidTarot, BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandTarot s: _object) {
            elt_.appendChild(setHandTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListListMiseres(CustList<IdList<Miseres>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (IdList<Miseres> s: _object) {
            elt_.appendChild(setListMiseres(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGameTarot(CustList<GameTarot> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GameTarot s: _object) {
            elt_.appendChild(setGameTarot(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static String resultsTarot(ResultsTarot _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_RESULTS_TAROT);
        setResultsTarot(_object,element_,doc_);
        doc_.appendChild(element_);
        return doc_.export();
    }
}
