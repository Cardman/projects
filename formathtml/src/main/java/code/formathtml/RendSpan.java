package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;

public class RendSpan extends RendElement {
    private ResultText result;
    private String formatted="";
    RendSpan(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        String id_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_FOR));
        result = new ResultText();
        result.buildId(id_,_cont,_doc);
        String valueMessage_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALUE_MESSAGE));
        if (!valueMessage_.isEmpty()) {
            formatted = getPre(_cont, valueMessage_);
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String txt_ = ResultText.render(result.getOpExp(), result.getTexts(), _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            ((Element)_nextWrite).removeAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_FOR));
            return;
        }
        ((Element)_nextWrite).setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_FOR),txt_);
        IdFormat id_ = new IdFormat();
        id_.setId(txt_);
        id_.setFormat(formatted);
        _cont.getFormatId().add(id_);
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(result.getOpExp());
    }
}
