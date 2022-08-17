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
import cards.consts.sml.DocumentWriterCardsCommonUtil;
import cards.gameresults.sml.DocumentWriterCardsResultsUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.*;
import code.util.core.BoolVal;

public final class DocumentWriterBeloteUtil {

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
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
    private static final String TYPE_BELOTE_TRUMP_PARTNER = "BeloteTrumpPartner";
    private static final String TYPE_BID_BELOTE = "BidBelote";
    private static final String TYPE_BID_BELOTE_SUIT = "BidBeloteSuit";
    private static final String TYPE_CARD_BELOTE = "CardBelote";
    private static final String TYPE_DEAL_BELOTE = "DealBelote";
    private static final String TYPE_DEALING_BELOTE = "DealingBelote";
    private static final String TYPE_DECLARE_HAND_BELOTE = "DeclareHandBelote";
    private static final String TYPE_DECLARES_BELOTE = "DeclaresBelote";
    private static final String TYPE_DISPLAYING_BELOTE = "DisplayingBelote";
    private static final String TYPE_GAME_BELOTE = "GameBelote";
    private static final String TYPE_HAND_BELOTE = "HandBelote";
    private static final String TYPE_LIST = "List";
    private static final String TYPE_MAP = "Map";
    private static final String TYPE_RULES_BELOTE = "RulesBelote";
    private static final String TYPE_TRICK_BELOTE = "TrickBelote";
    private static final String TYPE_TRICKS_HANDS_BELOTE = "TricksHandsBelote";

    public static Element setBidBeloteSuit(BidBeloteSuit _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BID_BELOTE_SUIT);
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
        Element element_ = _document.createElement(TYPE_DEAL_BELOTE);
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
        Element element_ = _document.createElement(TYPE_DECLARE_HAND_BELOTE);
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
        Element element_ = _document.createElement(TYPE_DISPLAYING_BELOTE);
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
        doc_.appendChild(setGameBelote(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setGameBelote(GameBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GAME_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGameBelote(_object,element_,_document);
        return element_;
    }

    private static void setGameBelote(GameBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setGameType(_object.getType(),FIELD_TYPE,_document));
        _element.appendChild(setDealBelote(_object.getDeal(),FIELD_DEAL,_document));
        _element.appendChild(setListDeclareHandBelote(_object.getDeclares(),FIELD_DECLARES,_document));
        _element.appendChild(setListHandBelote(_object.getDeclaresBeloteRebelote(),FIELD_DECLARES_BELOTE_REBELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBooleanList(_object.getWonLastTrick(),FIELD_WON_LAST_TRICK,_document));
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
        Element element_ = _document.createElement(TYPE_HAND_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setHandBelote(_object,element_,_document);
        return element_;
    }

    private static void setHandBelote(HandBelote _object, Element _element, Document _document) {
        _element.appendChild(setListCardBelote(_object.getCards(),FIELD_CARDS,_document));
    }

    public static void setResultsBelote(ResultsBelote _object, Element _element, Document _document) {
        _element.appendChild(setGameBelote(_object.getGame(),FIELD_GAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getRes().getNicknames(),FIELD_NICKNAMES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getRes().getUser(),FIELD_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getRes().getLoc(),FIELD_LOC,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_object, _element, _document);
    }

    public static String setRulesBelote(RulesBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesBelote(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setRulesBelote(RulesBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_RULES_BELOTE);
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
        Element element_ = _document.createElement(TYPE_TRICK_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrickBelote(_object,element_,_document);
        return element_;
    }

    private static void setTrickBelote(TrickBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getStarter(),FIELD_STARTER,_document));
        _element.appendChild(setHandBelote(_object.getCards(),FIELD_CARDS,_document));
    }

    public static String setTricksHandsBelote(TricksHandsBelote _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setTricksHandsBelote(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setTricksHandsBelote(TricksHandsBelote _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRICKS_HANDS_BELOTE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTricksHandsBelote(_object,element_,_document);
        return element_;
    }

    private static void setTricksHandsBelote(TricksHandsBelote _object, Element _element, Document _document) {
        _element.appendChild(setRulesBelote(_object.getRules(),FIELD_RULES,_document));
        _element.appendChild(setDealBelote(_object.getDistribution(),FIELD_DISTRIBUTION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPreneur(),FIELD_PRENEUR,_document));
        _element.appendChild(setBidBeloteSuit(_object.getBid(),FIELD_BID,_document));
        _element.appendChild(setListTrickBelote(_object.getTricks(),FIELD_TRICKS,_document));
        _element.appendChild(setListHandBelote(_object.getCardsHandsAtInitialState(),FIELD_CARDS_HANDS_AT_INITIAL_STATE,_document));
    }

    private static Element setBeloteTrumpPartner(BeloteTrumpPartner _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_BELOTE_TRUMP_PARTNER);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setBidBelote(BidBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_BID_BELOTE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setCardBelote(CardBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CARD_BELOTE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setDealingBelote(DealingBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DEALING_BELOTE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setDeclaresBelote(DeclaresBelote _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DECLARES_BELOTE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setListTrickBelote(CustList<TrickBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickBelote s: _object) {
            elt_.appendChild(setTrickBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardBelote(EnumList<CardBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardBelote s: _object) {
            elt_.appendChild(setCardBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapBidBeloteBoolean(EnumMap<BidBelote, BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
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

    private static Element setMapDeclaresBeloteBoolean(EnumMap<DeclaresBelote,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<DeclaresBelote, Boolean> s: _object.entryList()) {
            Element sub_ = setDeclaresBelote(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListBidBeloteSuit(CustList<BidBeloteSuit> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (BidBeloteSuit s: _object) {
            elt_.appendChild(setBidBeloteSuit(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListDeclareHandBelote(CustList<DeclareHandBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (DeclareHandBelote s: _object) {
            elt_.appendChild(setDeclareHandBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListHandBelote(CustList<HandBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandBelote s: _object) {
            elt_.appendChild(setHandBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGameBelote(CustList<GameBelote> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GameBelote s: _object) {
            elt_.appendChild(setGameBelote(s,EMPTY_STRING,_document));
        }
        return elt_;
    }
}