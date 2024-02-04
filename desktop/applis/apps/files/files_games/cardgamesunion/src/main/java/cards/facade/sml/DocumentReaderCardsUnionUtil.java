package cards.facade.sml;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.sml.DocumentReaderCardsCommonUtil;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.sml.DocumentReaderTarotUtil;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.IdList;
import code.util.core.StringUtil;

public final class DocumentReaderCardsUnionUtil {
    private DocumentReaderCardsUnionUtil() {
    }

    public static boolean isContentObject(String _content) {
        Document doc_ = DocumentBuilder.parseSax(_content);
        if (doc_ == null) {
            return false;
        }
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = elt_.getTagName();
        return StringUtil.quickEq(tagName_, DocumentWriterBeloteUtil.TYPE_GAME_BELOTE) || StringUtil.quickEq(tagName_, DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT) || StringUtil.quickEq(tagName_, DocumentWriterTarotUtil.TYPE_GAME_TAROT);
    }
    public static Games getGames(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Games object_ = new Games();
        for (Element c: childElements_) {
            getGames(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGames(Games _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PARTIES_BELOTE)) {
            _object.setPartiesBelote(DocumentReaderBeloteUtil.getListGameBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PARTIES_TAROT)) {
            _object.setPartiesTarot(DocumentReaderTarotUtil.getListGameTarot(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PARTIES_PRESIDENT)) {
            _object.setPartiesPresident(DocumentReaderPresidentUtil.getListGamePresident(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_RULES_BELOTE)) {
            _object.setRulesBelote(DocumentReaderBeloteUtil.getRulesBelote(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_RULES_TAROT)) {
            _object.setRulesTarot(DocumentReaderTarotUtil.getRulesTarot(_element));
            return;
        }
        _object.setRulesPresident(DocumentReaderPresidentUtil.getRulesPresident(_element));
    }

    public static Nicknames getNicknames(String _u, String _n,String _string) {
        return getNicknames(_u,_n,DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    private static Nicknames getNicknames(String _u, String _n,Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Nicknames object_ = new Nicknames(_u,_n);
        for (Element c: childElements_) {
            getNicknames(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getNicknames(Nicknames _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PSEUDOS_BELOTE)) {
            _object.setPseudosBelote(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_PSEUDOS_TAROT)) {
            _object.setPseudosTarot(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setPseudosPresident(DocumentReaderCoreUtil.getStringList(_element));
    }

    public static SoftParams getSoftParams(String _string) {
        return getSoftParams(DocumentReaderCardsCommonUtil.strToDocDoc(_string).getDocumentElement());
    }

    private static SoftParams getSoftParams(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        SoftParams object_ = new SoftParams();
        for (Element c: childElements_) {
            getSoftParams(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getSoftParams(SoftParams _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_LAUNCHING)) {
            _object.setLaunching(getListGameEnum(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_SAVE_HOME_FOLDER)) {
            _object.setSaveHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_DELAY_WAITING_BIDS)) {
            _object.setDelayWaitingBids(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_DELAY_WAITING_CARDS)) {
            _object.setDelayWaitingCards(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_DELAY_WAITING_TRICKS)) {
            _object.setDelayWaitingTricks(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsUnionUtil.FIELD_WAIT_TRICK_CLICK)) {
            _object.setWaitTrickClick(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setPlayCardClick(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static GameEnum getGameEnum(Element _elt) {
        return GameEnum.getStatusTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static IdList<GameEnum> getListGameEnum(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<GameEnum> list_ = new IdList<GameEnum>(cap_);
        for (Element c: childElements_) {
            list_.add(getGameEnum(c));
        }
        return list_;
    }

}