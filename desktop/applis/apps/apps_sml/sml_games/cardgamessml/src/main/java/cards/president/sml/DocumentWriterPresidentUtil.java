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
import cards.president.enumerations.PresidentCardsExporterUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.CustList;
import code.util.IdList;

public final class DocumentWriterPresidentUtil {
    public static final String TYPE_GAME_PRESIDENT = "GamePresident";
    public static final String TYPE_RESULTS_PRESIDENT = "ResultsPresident";
    public static final String TYPE_RULES_PRESIDENT = "RulesPresident";
    public static final String TYPE_TRICKS_HANDS_PRESIDENT = "TricksHandsPresident";
    public static final String EMPTY_STRING = "";
    public static final String FIELD_CARDS_HANDS_AT_INITIAL_STATE = "0";
    public static final String FIELD_CLOCKWISE = "1";
    public static final String FIELD_DEAL = "2";
    public static final String FIELD_DEALER = "3";
    public static final String FIELD_DECREASING = "4";
    public static final String FIELD_DISTRIBUTION = "5";
    public static final String FIELD_EQUALTY = "6";
    public static final String FIELD_HAS_TO_PLAY = "7";
    public static final String FIELD_LOOSER_STARTS_FIRST = "8";
    public static final String FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS = "9";
    public static final String FIELD_MIXED_CARDS = "10";
    public static final String FIELD_NB_DEALS = "11";
    public static final String FIELD_NB_PLAYERS = "12";
    public static final String FIELD_NB_STACKS = "13";
    public static final String FIELD_NUMBER = "14";
    public static final String FIELD_NUMBER_MAX_SWITCHED_CARDS = "15";
    public static final String FIELD_POSSIBLE_REVERSING = "16";
    public static final String FIELD_PROGRESSING_TRICK = "17";
    public static final String FIELD_RANKS = "18";
    public static final String FIELD_REVERSED = "19";
    public static final String FIELD_RULES = "20";
    public static final String FIELD_SCORES = "21";
    public static final String FIELD_SUITS = "22";
    public static final String FIELD_SWITCH_CARDS = "23";
    public static final String FIELD_SWITCHED_CARDS = "24";
    public static final String FIELD_TRICKS = "25";
    public static final String FIELD_TYPE = "26";
    private DocumentWriterPresidentUtil() {
    }

    private static Element setDealPresident(DealPresident _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDisplayingPresident(_object,element_,_document);
        return element_;
    }

    private static void setDisplayingPresident(DisplayingPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isClockwise(),FIELD_CLOCKWISE,_document));
        _element.appendChild(DocumentWriterCardsCommonUtil.setListSuit(_object.getDisplaying().getSuits(),FIELD_SUITS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisplaying().isDecreasing(),FIELD_DECREASING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbDeals(),FIELD_NB_DEALS,_document));
    }

    public static String setGamePresident(GamePresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGamePresident(_object, EMPTY_STRING, doc_, TYPE_GAME_PRESIDENT));
        return doc_.export();
    }

    private static Element setGamePresident(GamePresident _object, String _fieldName, Document _document) {
        return setGamePresident(_object, _fieldName, _document, DocumentWriterCoreUtil.ANON_TAG);
    }
    private static Element setGamePresident(GamePresident _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
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
        return setListCardPresident(_object.getCards(), _fieldName, _document);
    }

    public static void setResultsPresident(ResultsPresident _object, Element _element, Document _document) {
        _element.appendChild(setGamePresident(_object.getGame(),DocumentWriterCardsResultsUtil.FIELD_GAME,_document));
        DocumentWriterCardsResultsUtil.setResultsGame(_element, _document, _object.getRes());
    }

    public static String setRulesPresident(RulesPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setRulesPresident(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    public static Element setRulesPresident(RulesPresident _object, String _fieldName, Document _document) {
        return setRulesPresident(_object,_fieldName,_document,TYPE_RULES_PRESIDENT);
    }
    public static Element setRulesPresident(RulesPresident _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setRulesPresident(_object,element_,_document);
        return element_;
    }

    private static void setRulesPresident(RulesPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCardsCommonUtil.setMixCardsChoice(_object.getCommon().getMixedCards(),FIELD_MIXED_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbPlayers(),FIELD_NB_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbStacks(),FIELD_NB_STACKS,_document));
        _element.appendChild(setEqualtyPlaying(_object.getEqualty(),FIELD_EQUALTY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getCommon().getNbDeals(),FIELD_NB_DEALS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPossibleReversing(),FIELD_POSSIBLE_REVERSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHasToPlay(),FIELD_HAS_TO_PLAY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLoosingIfFinishByBestCards(),FIELD_LOOSING_IF_FINISH_BY_BEST_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSwitchCards(),FIELD_SWITCH_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLooserStartsFirst(),FIELD_LOOSER_STARTS_FIRST,_document));
    }

    private static Element setTrickPresident(TrickPresident _object, String _fieldName, Document _document) {
        return setListHandPresident(_object.getCards(),_fieldName,_document);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,PresidentCardsExporterUtil.fromCardPresident(_object));
        return elt_;
    }

    private static Element setEqualtyPlaying(EqualtyPlaying _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,PresidentCardsExporterUtil.fromEqualtyPlaying(_object));
        return elt_;
    }

    private static Element setListTrickPresident(CustList<TrickPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TrickPresident s: _object) {
            elt_.appendChild(setTrickPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCardPresident(IdList<CardPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CardPresident s: _object) {
            elt_.appendChild(setCardPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListHandPresident(CustList<HandPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (HandPresident s: _object) {
            elt_.appendChild(setHandPresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListGamePresident(CustList<GamePresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GamePresident s: _object) {
            elt_.appendChild(setGamePresident(s,EMPTY_STRING,_document));
        }
        return elt_;
    }
    private static Element setMapByteHandPresident(CustList<HandPresident> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        byte b_ = 0;
        for (HandPresident s: _object) {
            Element sub_ = DocumentWriterCoreUtil.setByte(b_, EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setHandPresident(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
            b_++;
        }
        return elt_;
    }

    public static String resultsPresident(ResultsPresident _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_RESULTS_PRESIDENT);
        setResultsPresident(_object,element_,doc_);
        doc_.appendChild(element_);
        return doc_.export();
    }
}