package code.gui.stream;

import code.gui.TopLeftFrame;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.core.StringUtil;

public final class DocumentReaderGuiUtil {
    private DocumentReaderGuiUtil() {
    }

    public static TopLeftFrame getTopLeftFrame(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return new TopLeftFrame();
        }
        return getTopLeftFrame(doc_.getDocumentElement());
    }

    private static TopLeftFrame getTopLeftFrame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TopLeftFrame object_ = new TopLeftFrame();
        for (Element c: childElements_) {
            getTopLeftFrame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTopLeftFrame(TopLeftFrame _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterGuiUtil.WIDTH)) {
            _object.setWidth(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        _object.setHeight(DocumentReaderCoreUtil.getInteger(_element));
    }
}
