package cards.consts.sml;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Order;
import cards.consts.Suit;
import code.sml.Document;
import code.sml.DocumentWriterCoreUtil;
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

    public static Element setGameType(GameType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GAME_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setMixCardsChoice(MixCardsChoice _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MIX_CARDS_CHOICE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setOrder(Order _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_ORDER);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static Element setSuit(Suit _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_SUIT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
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