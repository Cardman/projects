package cards.consts.sml;
import cards.consts.*;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.IdList;
public final class DocumentWriterCardsCommonUtil {

    private static final String EMPTY_STRING = "";
    private DocumentWriterCardsCommonUtil(){
    }

    public static Element setGameType(GameType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,EnumCardsExporterUtil.fromGameType(_object));
        return elt_;
    }

    public static Element setMixCardsChoice(MixCardsChoice _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,EnumCardsExporterUtil.fromMixCardsChoice(_object));
        return elt_;
    }

    public static Element setOrder(Order _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,EnumCardsExporterUtil.fromOrder(_object));
        return elt_;
    }

    public static Element setSuit(Suit _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,EnumCardsExporterUtil.fromSuit(_object));
        return elt_;
    }

    public static Element setListSuit(IdList<Suit> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Suit s: _object) {
            elt_.appendChild(setSuit(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

}