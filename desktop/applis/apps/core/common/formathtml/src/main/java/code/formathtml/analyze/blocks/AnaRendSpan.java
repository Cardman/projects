package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.Configuration;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendSpan extends AnaRendElement {
    private StringMap<String> formatted=new StringMap<String>();
    private CustList<OperationNode> roots;
    private StringList texts;

    AnaRendSpan(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
        String id_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        int off_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
        ResultText result = new ResultText();
        result.buildIdAna(id_, off_, _anaDoc, _page);
        roots = result.getOpExpRoot();
        texts = result.getTexts();
        for (String l: _anaDoc.getLanguages()) {
            formatted.addEntry(l,EMPTY_STRING);
        }
        String valueMessage_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
        if (!valueMessage_.isEmpty()) {
            int offMessage_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValueMessage()));
            formatted = getPre(_cont, valueMessage_,offMessage_, _anaDoc, _page);
        }
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringMap<String> getFormatted() {
        return formatted;
    }
}
