package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendLink extends AnaRendElement {
    private String content;
    private StringMap<ResultText> opExpTitle;
    AnaRendLink(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrRel());
        String href_ = getCssHref(_read, _anaDoc.getRendKeyWords());
        opExpTitle = new StringMap<ResultText>();
        if (href_ != null) {
            StringMap<String> files_ = _anaDoc.getFiles();
            content = files_.getVal(href_);
            int i_ = IndexConstants.FIRST_INDEX;
            while (_read.hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
                _list.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                String attribute_ = _read.getAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                int rowsGrId_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                ResultText r_ = new ResultText();
                r_.buildAna(attribute_, rowsGrId_, _anaDoc, _page);
                opExpTitle.addEntry(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
                i_++;
            }
        }
    }

    public String getContent() {
        return content;
    }

    public StringMap<ResultText> getOpExpTitle() {
        return opExpTitle;
    }
}
