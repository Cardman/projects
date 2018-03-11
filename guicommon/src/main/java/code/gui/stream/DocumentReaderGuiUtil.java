package code.gui.stream;

import code.gui.TopLeftFrame;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.StringList;

public final class DocumentReaderGuiUtil {
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String FIELD = "field";

    public static TopLeftFrame getTopLeftFrame(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        return getTopLeftFrame(doc_.getDocumentElement());
    }

    private static TopLeftFrame getTopLeftFrame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TopLeftFrame object_ = new TopLeftFrame();
        for (Element c: childElements_) {
            getTopLeftFrame(object_,c.getAttribute(FIELD),c);
        }
        return object_;
    }

    private static void getTopLeftFrame(TopLeftFrame _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, WIDTH)) {
            _object.setWidth(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, HEIGHT)) {
            _object.setHeight(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }
}
