package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendSpan extends AnaRendElement {
    private StringMap<String> formatted=new StringMap<String>();
    private CustList<OperationNode> roots;
    private StringList texts;
    private final ResultText res;

    public AnaRendSpan(Element _elt, int _offset) {
        super(_elt, _offset);
        res = new ResultText();
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String id_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        int off_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        res.buildIdAna(id_, off_, _anaDoc, _page);
        roots = res.getOpExpRoot();
        texts = res.getTexts();
        for (String l: _anaDoc.getLanguages()) {
            formatted.addEntry(l,EMPTY_STRING);
        }
        String valueMessage_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        if (!valueMessage_.isEmpty()) {
            int offMessage_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
            formatted = getPre(valueMessage_,offMessage_, _anaDoc, _page);
        }
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        return list_;
    }

    public ResultText getRes() {
        return res;
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
