package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendLink extends AnaRendElement implements AnaRendElementAttr {
    private String content;
    private final StringMap<ResultExpression> opExpTitle = new StringMap<ResultExpression>();
    AnaRendLink(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String href_ = getCssHref(getRead(), _anaDoc.getRendKeyWords());
        if (href_ != null) {
            StringMap<String> files_ = _anaDoc.getFiles();
            content = files_.getVal(href_);
        }
        for (EntryCust<String,ResultExpression> e: opExpTitle.entryList()) {
            _page.setSumOffset(e.getValue().getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
    }
    public StringList titles(AnalyzingDoc _anaDoc) {
        String href_ = getCssHref(getRead(), _anaDoc.getRendKeyWords());
        if (href_ == null) {
            return new StringList();
        }
        return paramsList(_anaDoc, getRead());
    }

    static StringList paramsList(AnalyzingDoc _anaDoc, Element _read) {
        StringList list_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        while (_read.hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.add(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        return list_;
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrRel());
        String href_ = getCssHref(getRead(), _anaDoc.getRendKeyWords());
        if (href_ != null) {
            int i_ = IndexConstants.FIRST_INDEX;
            while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
                list_.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                i_++;
            }
        }
        return list_;
    }

    public String getContent() {
        return content;
    }

    public StringMap<ResultExpression> getOpExpTitle() {
        return opExpTitle;
    }
}
