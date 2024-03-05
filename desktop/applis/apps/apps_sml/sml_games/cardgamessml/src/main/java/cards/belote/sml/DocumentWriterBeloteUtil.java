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
import cards.consts.sml.DocumentWriterCardsCommonUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.*;
import code.util.core.BoolVal;

public final class DocumentWriterBeloteUtil {
    public static final String TYPE_GAME_BELOTE = "0";
    public static final String TYPE_RESULTS_BELOTE = "ResultsBelote";
    public static final String TYPE_RULES_BELOTE = "RulesBelote";
    public static final String TYPE_TRICKS_HANDS_BELOTE = "TricksHandsBelote";
    public static final String EMPTY_STRING = "";
    public static final String FIELD_ALLOWED_BIDS = "0";
    public static final String FIELD_ALLOWED_DECLARES = "1";
    public static final String FIELD_BID = "2";
    public static final String FIELD_BIDS = "3";
    public static final String FIELD_CARDS = "4";
    public static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "5";
    public static final String FIELD_CLASSIC_COUNT_POINTS = "6";
    public static final String FIELD_CLOCKWISE = "7";
    public static final String FIELD_DEAL = "8";
    public static final String FIELD_DEALER = "9";
    public static final String FIELD_DEALING = "10";
    public static final String FIELD_DECLARE = "11";
    public static final String FIELD_DECLARES = "12";
    public static final String FIELD_DECLARES_BELOTE_REBELOTE = "13";
    public static final String FIELD_DECREASING = "14";
    public static final String FIELD_DISTRIBUTION = "15";
    public static final String FIELD_HAND = "16";
    public static final String FIELD_MIXED_CARDS = "17";
    public static final String FIELD_NB_DEALS = "18";
    public static final String FIELD_NUMBER = "19";
    public static final String FIELD_ORDER_BEFORE_BIDS = "20";
    public static final String FIELD_PLAYER = "21";
    public static final String FIELD_POINTS = "22";
    public static final String FIELD_PRENEUR = "23";
    public static final String FIELD_PROGRESSING_TRICK = "24";
    public static final String FIELD_RULES = "25";
    public static final String FIELD_SCORES = "26";
//    public static final String FIELD_STARTER = "27";
    public static final String FIELD_SUIT = "28";
    public static final String FIELD_SUITS = "29";
    public static final String FIELD_TRICKS = "30";
    public static final String FIELD_TRUMP_PARTNER = "31";
    public static final String FIELD_TYPE = "32";
    public static final String FIELD_UNDER_TRUMP_FOE = "33";
    public static final String FIELD_WON_LAST_TRICK = "34";

    private DocumentWriterBeloteUtil() {
    }

    public static Element setBidBeloteSuit(BidBeloteSuit _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBidBeloteSuit(_object,element_,_document);
        return element_;
    }

    private static void setBidBeloteSuit(BidBeloteSuit _object, Element _element, Document _document) {
        _element.appendChild(setBidBelote(_object.getBid(),FIELD_BID,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setSuit(_object.getSuit(),FIELD_SUIT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getPoints(),FIELD_POINTS,_document));
    }

    private static Element setDealBelote(DealBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDealBelote(_object,element_,_document);
        return element_;
    }

    private static void setDealBelote(DealBelote _object, Element _element, Document _document) {
        _element.appendChild(setListHandBelote(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getDealer(),FIELD_DEALER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNbDeals(),FIELD_NB_DEALS,_document));
    }

    public static Element setDeclareHandBelote(DeclareHandBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDeclareHandBelote(_object,element_,_document);
        return element_;
    }

    private static void setDeclareHandBelote(DeclareHandBelote _object, Element _element, Document _document) {
        _element.appendChild(setDeclaresBelote(_object.getDeclare(),FIELD_DECLARE,_document));
        _element.appendChild(setHandBelote(_object.getHand(),FIELD_HAND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlayer(),FIELD_PLAYER,_document));
    }

    public static String setDisplayingBelote(DisplayingBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDisplayingBelote(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setDisplayingBelote(DisplayingBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDisplayingBelote(_object,element_,_document);
        return element_;
    }

    private static void setDisplayingBelote(DisplayingBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isClockwise(),FIELD_CLOCKWISE,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setListSuit(_object.getDisplaying().getSuits(),FIELD_SUITS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isDecreasing(),FIELD_DECREASING,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setOrder(_object.getOrderBeforeBids(),FIELD_ORDER_BEFORE_BIDS,_document));
    }

    public static String setGameBelote(GameBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGameBelote(_object, EMPTY_STRING, doc_, TYPE_GAME_BELOTE));
        return doc_.export();
    }

    private static Element setGameBelote(GameBelote _object, String _fieldName, Document _document) {
        return setGameBelote(_object,_fieldName,_document,DocumentWriterCoreUtil.ANON_TAG);
    }
    private static Element setGameBelote(GameBelote _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGameBelote(_object,element_,_document);
        return element_;
    }

    private static void setGameBelote(GameBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setGameType(_object.getType(),FIELD_TYPE,_document));
        _element.appendChild(setDealBelote(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(setListDeclareHandBelote(_object.getDeclares(),FIELD_DECLARES,_document));
        _element.appendChild(setListHandBelote(_object.getDeclaresBeloteRebelote(),FIELD_DECLARES_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolValList(_object.getWonLastTrick(),FIELD_WON_LAST_TRICK,_document));
        _element.appendChild(setListTrickBelote(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setTrickBelote(_object.getProgressingTrick(),FIELD_PROGRESSING_TRICK,_document));
        _element.appendChild(setListBidBeloteSuit(_object.getBids(),FIELD_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getScores(),FIELD_SCORES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getNumber(),FIELD_NUMBER,_document));
        _element.appendChild(setRulesBelote(_object.getRules(),FIELD_RULES,_document));
    }

    public static String setHandBelote(HandBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setHandBelote(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setHandBelote(HandBelote _object, String _fieldName, Document _document) {
        return setListCardBelote(_object.getCards(), _fieldName,_document);
    }

    public static void setResultsBelote(ResultsBelote _object, Element _element, Document _document) {
        _element.appendChild(setGameBelote(_object.getGame(),DocumentWriterCardsResultsUtil.FIELD_GAME,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_element, _document, _object.getRes());
    }

    public static String setRulesBelote(RulesBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesBelote(_object, EMPTY_STRING, doc_, TYPE_RULES_BELOTE));
        return doc_.export();
    }

    public static Element setRulesBelote(RulesBelote _object, String _fieldName, Document _document) {
        return setRulesBelote(_object,_fieldName,_document,DocumentWriterCoreUtil.ANON_TAG);
    }
    public static Element setRulesBelote(RulesBelote _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setRulesBelote(_object,element_,_document);
        return element_;
    }

    private static void setRulesBelote(RulesBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setMixCardsChoice(_object.getCommon().getMixedCards(),FIELD_MIXED_CARDS,_document));
        _element.appendChild(setMapDeclaresBeloteBoolean(_object.getAllowedDeclares(),FIELD_ALLOWED_DECLARES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isUnderTrumpFoe(),FIELD_UNDER_TRUMP_FOE,_document));
        _element.appendChild(setBeloteTrumpPartner(_object.getTrumpPartner(),FIELD_TRUMP_PARTNER,_document));
        _element.appendChild(setMapBidBeloteBoolean(_object.getAllowedBids(),FIELD_ALLOWED_BIDS,_document));
        _element.appendChild(setDealingBelote(_object.getDealing(),FIELD_DEALING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClassicCountPoints(),FIELD_CLASSIC_COUNT_POINTS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getCommon().getNbDeals(),FIELD_NB_DEALS,_document));
    }

    private static Element setTrickBelote(TrickBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrickBelote(_object,element_,_document);
        return element_;
    }

    private static void setTrickBelote(TrickBelote _object, Element _element, Document _document) {
//        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getStarter(),FIELD_STARTER,_document));
        _element.appendChild(setHandBelote(_object.getCards(),FIELD_CARDS,_document));
    }

    public static Element setTricksHandsBelote(TricksHandsBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICKS_HANDS_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTricksHandsBelote(_object,element_,_document);
        return element_;
    }

    private static void setTricksHandsBelote(TricksHandsBelote _object, Element _element, Document _document) {
//        _element.appendChild(setRulesBelote(_object.getRules(),FIELD_RULES,_document));
        _element.appendChild(setDealBelote(_object.getDistribution(),FIELD_DISTRIBUTION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPreneur(),FIELD_PRENEUR,_document));
        _element.appendChild(setBidBeloteSuit(_object.getBid(),FIELD_BID,_document));
        _element.appendChild(setListTrickBelote(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setListHandBelote(_object.getCardsHandsAtInitialState(),FIELD_CARDS_HANDS_AT_INITIAL_STATE,_document));
    }

    private static Element setBeloteTrumpPartner(BeloteTrumpPartner _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,BeloteCardsExporterUtil.fromBeloteTrumpPartner(_object));
        return elt_;
    }

    private static Element setBidBelote(BidBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,BeloteCardsExporterUtil.fromBidBelote(_object));
        return elt_;
    }

    public static Element setCardBelote(CardBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,BeloteCardsExporterUtil.fromCardBelote(_object));
        return elt_;
    }

    public static Element setDealingBelote(DealingBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,BeloteCardsExporterUtil.fromDealingBelote(_object));
        return elt_;
    }

    private static Element setDeclaresBelote(DeclaresBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,BeloteCardsExporterUtil.fromDeclaresBelote(_object));
        return elt_;
    }

    private static Element setListTrickBelote(CustList<TrickBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickBelote s: _object) {
            elt_.appendChild(setTrickBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardBelote(IdList<CardBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardBelote s: _object) {
            elt_.appendChild(setCardBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapBidBeloteBoolean(IdMap<BidBelote, BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<BidBelote, BoolVal> s: _object.entryList()) {
            Element sub_ = setBidBelote(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapDeclaresBeloteBoolean(IdMap<DeclaresBelote,BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<DeclaresBelote, BoolVal> s: _object.entryList()) {
            Element sub_ = setDeclaresBelote(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListBidBeloteSuit(CustList<BidBeloteSuit> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (BidBeloteSuit s: _object) {
            elt_.appendChild(setBidBeloteSuit(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListDeclareHandBelote(CustList<DeclareHandBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (DeclareHandBelote s: _object) {
            elt_.appendChild(setDeclareHandBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListHandBelote(CustList<HandBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandBelote s: _object) {
            elt_.appendChild(setHandBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGameBelote(CustList<GameBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GameBelote s: _object) {
            elt_.appendChild(setGameBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static String resultsBelote(ResultsBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_RESULTS_BELOTE);
        setResultsBelote(_object,element_,doc_);
        doc_.appendChild(element_);
        return doc_.export();
    }
}