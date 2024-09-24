package cards.solitaire.sml;

import cards.solitaire.*;
import code.sml.*;
import code.sml.core.*;
import code.util.*;

public final class DocumentWriterSolitaireUtil {
    public static final String TYPE_GAME_SOLITAIRE = "3";
    public static final String FIELD_CARDS = "0";
    public static final String FIELD_ACTIONS = "1";
    public static final String SEP = ",";

    private DocumentWriterSolitaireUtil() {
    }

    public static String setGameSolitaire(AbsDealSolitaire _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGameSolitaire(_object, doc_));
        return doc_.export();
    }
    private static Element setGameSolitaire(AbsDealSolitaire _object, Document _document) {
        Element element_ = _document.createElement(DocumentWriterSolitaireUtil.TYPE_GAME_SOLITAIRE);
        DocumentWriterCoreUtil.setFieldName(element_,Integer.toString(_object.type().getKind()));
        element_.appendChild(setListHandSolitaire(_object.getHandsBegin(), _document));
        element_.appendChild(setListActionSolitaire(_object.getActions(), _document));
        return element_;
    }
    private static Element setListHandSolitaire(CustList<HandSolitaire> _object, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, FIELD_CARDS);
        for (HandSolitaire s: _object) {
            elt_.appendChild(setListCardSolitaire(s,_document));
        }
        return elt_;
    }
    private static Element setListCardSolitaire(HandSolitaire _object, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        for (CardSolitaire s: _object) {
            elt_.appendChild(setCardSolitaire(s,_document));
        }
        return elt_;
    }
    private static Element setCardSolitaire(CardSolitaire _object, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, SolitaireCardsExporterUtil.fromCardSolitaire(_object));
        return elt_;
    }
    private static Element setListActionSolitaire(CustList<ActionSolitaire> _object, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, FIELD_ACTIONS);
        for (ActionSolitaire s: _object) {
            elt_.appendChild(setActionSolitaire(s,_document));
        }
        return elt_;
    }
    private static Element setActionSolitaire(ActionSolitaire _object, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _object.getFromIndex()+ SEP +_object.getCardIndex()+ SEP +_object.getToIndex());
        return elt_;
    }
}
