package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendLink extends AnaRendElement {
    private String content;
    private final StringMap<ResultText> opExpTitle = new StringMap<ResultText>();
    AnaRendLink(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String href_ = getCssHref(_read, _anaDoc.getRendKeyWords());
        if (href_ != null) {
            StringMap<String> files_ = _anaDoc.getFiles();
            content = files_.getVal(href_);
        }
        for (EntryCust<String,ResultText> e: opExpTitle.entryList()) {
            String attribute_ = _read.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            e.getValue().buildIdAna(attribute_, rowsGrId_, _anaDoc, _page);
        }
    }
    public StringList titles(AnalyzingDoc _anaDoc) {
        String href_ = getCssHref(getRead(), _anaDoc.getRendKeyWords());
        if (href_ == null) {
            return new StringList();
        }
        StringList list_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
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

    public StringMap<ResultText> getOpExpTitle() {
        return opExpTitle;
    }
}
