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
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        processAnaInput(_cont,_doc,_read);
        _list.removeAllString(ATTRIBUTE_VALUE);
        _list.removeAllString(ATTRIBUTE_NAME);
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CLASS_NAME));
        _list.removeAllString(NUMBER_INPUT);
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_VALUE));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_FIELD));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR));
        _list.removeAllString(ATTRIBUTE_TYPE);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processIndexes(_cont,_read, (Element) _nextWrite);
    }
}
