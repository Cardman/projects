package code.gui.stream;

import code.gui.TopLeftFrame;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;

public final class DocumentWriterGuiUtil {
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String TOP_LEFT_FRAME = "TopLeftFrame";
    private static final String EMPTY_STRING = "";

    public static String setTopLeftFrame(TopLeftFrame _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setTopLeftFrame(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setTopLeftFrame(TopLeftFrame _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TOP_LEFT_FRAME);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTopLeftFrame(_object,element_,_document);
        return element_;
    }

    private static void setTopLeftFrame(TopLeftFrame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getWidth(),WIDTH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getHeight(),HEIGHT,_document));
    }
}
