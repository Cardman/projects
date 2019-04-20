package cards.facade.sml;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.tarot.sml.DocumentReaderTarotUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.stream.StreamTextFile;
import code.util.CollCapacity;
import code.util.EnumList;
import code.util.StringList;
public final class DocumentReaderCardsUnionUtil {

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final char DOT = '.';
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

    public static Object getObject(String _fileName) {
        String content_ = StreamTextFile.contentsOfFile(_fileName);
        Document doc_ = DocumentBuilder.parseSax(content_);
        if (doc_ == null) {
            return null;
        }
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = elt_.getTagName();
        if (StringList.quickEq(tagName_, "GameBelote")) {
            return DocumentReaderBeloteUtil.getGameBelote(doc_);
        }
        if (StringList.quickEq(tagName_, "GamePresident")) {
            return DocumentReaderPresidentUtil.getGamePresident(doc_);
        }
        if (StringList.quickEq(tagName_, "GameTarot")) {
            return DocumentReaderTarotUtil.getGameTarot(doc_);
        }
        return null;
    }
    public static Games getGames(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Games object_ = new Games();
        for (Element c: childElements_) {
            getGames(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGames(Games _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PARTIES_BELOTE)) {
            _object.setPartiesBelote(DocumentReaderBeloteUtil.getListGameBelote(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PARTIES_TAROT)) {
            _object.setPartiesTarot(DocumentReaderTarotUtil.getListGameTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PARTIES_PRESIDENT)) {
            _object.setPartiesPresident(DocumentReaderPresidentUtil.getListGamePresident(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RULES_BELOTE)) {
            _object.setRulesBelote(DocumentReaderBeloteUtil.getRulesBelote(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RULES_TAROT)) {
            _object.setRulesTarot(DocumentReaderTarotUtil.getRulesTarot(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RULES_PRESIDENT)) {
            _object.setRulesPresident(DocumentReaderPresidentUtil.getRulesPresident(_element));
            return;
        }
    }

    public static Nicknames getNicknames(String _lg,String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new Nicknames(_lg);
        }
        return getNicknames(doc_.getDocumentElement());
    }

    private static Nicknames getNicknames(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Nicknames object_ = new Nicknames();
        for (Element c: childElements_) {
            getNicknames(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getNicknames(Nicknames _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PSEUDOS_BELOTE)) {
            _object.setPseudosBelote(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PSEUDOS_TAROT)) {
            _object.setPseudosTarot(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PSEUDOS_PRESIDENT)) {
            _object.setPseudosPresident(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
    }

    public static SoftParams getSoftParams(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        if (doc_ == null) {
            return new SoftParams();
        }
        return getSoftParams(doc_.getDocumentElement());
    }

    private static SoftParams getSoftParams(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        SoftParams object_ = new SoftParams();
        for (Element c: childElements_) {
            getSoftParams(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getSoftParams(SoftParams _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LAUNCHING)) {
            _object.setLaunching(getListGameEnum(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SAVE_HOME_FOLDER)) {
            _object.setSaveHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELAY_WAITING_BIDS)) {
            _object.setDelayWaitingBids(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELAY_WAITING_CARDS)) {
            _object.setDelayWaitingCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELAY_WAITING_TRICKS)) {
            _object.setDelayWaitingTricks(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WAIT_TRICK_CLICK)) {
            _object.setWaitTrickClick(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAY_CARD_CLICK)) {
            _object.setPlayCardClick(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static GameEnum getGameEnum(Element _elt) {
        for (GameEnum e: GameEnum.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static EnumList<GameEnum> getListGameEnum(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<GameEnum> list_ = new EnumList<GameEnum>(cap_);
        for (Element c: childElements_) {
            list_.add(getGameEnum(c));
        }
        return list_;
    }

}