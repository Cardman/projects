package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendSpan extends RendElement {
    private ResultText result;
    private StringMap<String> formatted=new StringMap<String>();
    RendSpan(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
        String id_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        int off_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        result = new ResultText();
        result.buildId(id_, off_,_doc, _anaDoc, _page);
        for (String l: _anaDoc.getLanguages()) {
            formatted.addEntry(l,EMPTY_STRING);
        }
        String valueMessage_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
        if (!valueMessage_.isEmpty()) {
            int offMessage_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
            formatted = getPre(_cont, valueMessage_,offMessage_, _anaDoc, _page);
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String txt_ = ResultText.render(result.getOpExp(), result.getTexts(), _cont);
        if (_cont.getContext().hasException()) {
            ((Element)_nextWrite).removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
            return;
        }
        ((Element)_nextWrite).setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()),txt_);
        CustList<StringList> stack_ = _cont.getFormatIdMapStack();
        if (stack_.isEmpty()) {
            return;
        }
        stack_.last().add(formatted.getVal(_cont.getCurrentLanguage()));
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(result.getOpExp());
    }
}
