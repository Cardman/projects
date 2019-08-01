package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;
import code.util.StringMap;

public final class RendSpan extends RendElement {
    private ResultText result;
    private StringMap<String> formatted=new StringMap<String>();
    RendSpan(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_FOR));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALUE_MESSAGE));
        String id_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_FOR));
        result = new ResultText();
        result.buildId(id_,_cont,_doc);
        for (String l: _cont.getAnalyzingDoc().getLanguages()) {
            formatted.addEntry(l,"");
        }
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
        _cont.getFormatId().add(formatted.getVal(_cont.getCurrentLanguage()));
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(result.getOpExp());
    }
}
