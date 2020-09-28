package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendLink extends AnaRendElement {
    private String content;
    private StringMap<ResultText> opExpTitle;
    AnaRendLink(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrHref());
        _list.removeAllString(_cont.getRendKeyWords().getAttrRel());
        String href_ = getCssHref(_cont,_read);
        opExpTitle = new StringMap<ResultText>();
        if (href_ != null) {
            StringMap<String> files_ = _cont.getFiles();
            content = files_.getVal(href_);
            int i_ = CustList.FIRST_INDEX;
            while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
                _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                String attribute_ = _read.getAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
                ResultText r_ = new ResultText();
                r_.buildAna(attribute_, rowsGrId_, _anaDoc, _page);
                opExpTitle.addEntry(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
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
