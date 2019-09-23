package cards.facade.sml;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.EnumList;
public final class DocumentWriterCardsUnionUtil {

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
    private static final String FIELD_DELAY_WAITING_BIDS = "delayWaitingBids";
    private static final String FIELD_DELAY_WAITING_CARDS = "delayWaitingCards";
    private static final String FIELD_DELAY_WAITING_TRICKS = "delayWaitingTricks";
    private static final String FIELD_LAUNCHING = "launching";
    private static final String FIELD_PARTIES_BELOTE = "partiesBelote";
    private static final String FIELD_PARTIES_PRESIDENT = "partiesPresident";
    private static final String FIELD_PARTIES_TAROT = "partiesTarot";
    private static final String FIELD_PLAY_CARD_CLICK = "playCardClick";
    private static final String FIELD_PSEUDO = "pseudo";
    private static final String FIELD_PSEUDOS_BELOTE = "pseudosBelote";
    private static final String FIELD_PSEUDOS_PRESIDENT = "pseudosPresident";
    private static final String FIELD_PSEUDOS_TAROT = "pseudosTarot";
    private static final String FIELD_RULES_BELOTE = "rulesBelote";
    private static final String FIELD_RULES_PRESIDENT = "rulesPresident";
    private static final String FIELD_RULES_TAROT = "rulesTarot";
    private static final String FIELD_SAVE_HOME_FOLDER = "saveHomeFolder";
    private static final String FIELD_WAIT_TRICK_CLICK = "waitTrickClick";
    private static final String TYPE_GAME_ENUM = "GameEnum";
    private static final String TYPE_GAMES = "Games";
    private static final String TYPE_LIST = "List";
    private static final String TYPE_NICKNAMES = "Nicknames";
    private static final String TYPE_SOFT_PARAMS = "SoftParams";

    public static Element setGames(Games _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GAMES);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGames(_object,element_,_document);
        return element_;
    }

    private static void setGames(Games _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterBeloteUtil.setListGameBelote(_object.getPartiesBelote(),FIELD_PARTIES_BELOTE,_document));
        _element.appendChild(DocumentWriterTarotUtil.setListGameTarot(_object.getPartiesTarot(),FIELD_PARTIES_TAROT,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setListGamePresident(_object.getPartiesPresident(),FIELD_PARTIES_PRESIDENT,_document));
        _element.appendChild(DocumentWriterBeloteUtil.setRulesBelote(_object.getRulesBelote(),FIELD_RULES_BELOTE,_document));
        _element.appendChild(DocumentWriterTarotUtil.setRulesTarot(_object.getRulesTarot(),FIELD_RULES_TAROT,_document));
        _element.appendChild(DocumentWriterPresidentUtil.setRulesPresident(_object.getRulesPresident(),FIELD_RULES_PRESIDENT,_document));
    }

    public static String setNicknames(Nicknames _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setNicknames(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setNicknames(Nicknames _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_NICKNAMES);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setNicknames(_object,element_,_document);
        return element_;
    }

    private static void setNicknames(Nicknames _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getPseudosBelote(),FIELD_PSEUDOS_BELOTE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getPseudosTarot(),FIELD_PSEUDOS_TAROT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getPseudosPresident(),FIELD_PSEUDOS_PRESIDENT,_document));
    }

    public static String setSoftParams(SoftParams _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setSoftParams(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setSoftParams(SoftParams _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_SOFT_PARAMS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setSoftParams(_object,element_,_document);
        return element_;
    }

    private static void setSoftParams(SoftParams _object, Element _element, Document _document) {
        _element.appendChild(setListGameEnum(_object.getLaunching(),FIELD_LAUNCHING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSaveHomeFolder(),FIELD_SAVE_HOME_FOLDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDelayWaitingBids(),FIELD_DELAY_WAITING_BIDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDelayWaitingCards(),FIELD_DELAY_WAITING_CARDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDelayWaitingTricks(),FIELD_DELAY_WAITING_TRICKS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isWaitTrickClick(),FIELD_WAIT_TRICK_CLICK,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPlayCardClick(),FIELD_PLAY_CARD_CLICK,_document));
    }

    private static Element setGameEnum(GameEnum _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GAME_ENUM);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setListGameEnum(EnumList<GameEnum> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (GameEnum s: _object) {
            elt_.appendChild(setGameEnum(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

}