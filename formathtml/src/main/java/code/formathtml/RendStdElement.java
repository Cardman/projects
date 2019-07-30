package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;

public final class RendStdElement extends RendElement {
    RendStdElement(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        if (StringList.quickEq(_read.getTagName(),StringList.concat(_cont.getPrefix(),TAG_PARAM))) {
            _list.clear();
        }
        _list.removeAllString(StringList.concat(_cont.getPrefix(),BEAN_ATTRIBUTE));
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {

    }
}
