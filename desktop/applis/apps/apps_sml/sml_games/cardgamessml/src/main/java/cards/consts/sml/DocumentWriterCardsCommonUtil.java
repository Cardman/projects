package cards.consts.sml;
import cards.consts.*;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.EnumList;
public final class DocumentWriterCardsCommonUtil {

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
    private static final String TYPE_GAME_TYPE = "GameType";
    private static final String TYPE_LIST = "List";
    private static final String TYPE_MIX_CARDS_CHOICE = "MixCardsChoice";
    private static final String TYPE_ORDER = "Order";
    private static final String TYPE_SUIT = "Suit";
    private DocumentWriterCardsCommonUtil(){
    }

    public static Element setGameType(GameType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GAME_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,EnumCardsExporterUtil.fromGameType(_object));
        return elt_;
    }

    public static Element setMixCardsChoice(MixCardsChoice _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MIX_CARDS_CHOICE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,EnumCardsExporterUtil.fromMixCardsChoice(_object));
        return elt_;
    }

    public static Element setOrder(Order _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_ORDER);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,EnumCardsExporterUtil.fromOrder(_object));
        return elt_;
    }

    public static Element setSuit(Suit _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_SUIT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,EnumCardsExporterUtil.fromSuit(_object));
        return elt_;
    }

    public static Element setListSuit(EnumList<Suit> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Suit s: _object) {
            elt_.appendChild(setSuit(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

}