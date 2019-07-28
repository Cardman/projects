package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;

public final class RendStdInput extends RendInput {
    RendStdInput(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        processAnaInput(_cont,_doc,_read);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processIndexes(_cont,_read, (Element) _nextWrite);
    }
}
