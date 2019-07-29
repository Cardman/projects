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
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {

    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {

    }
}
