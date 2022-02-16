package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendSubmit extends AnaRendElement {

    private StringMap<ResultText> opExp;

    private StringMap<String> preformatted;
    AnaRendSubmit(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrMessage());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrMessage());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        opExp = new StringMap<ResultText>();
        if (preformatted.isEmpty()) {
            removeUseLess(_read, _list, _anaDoc.getRendKeyWords());
            return;
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        int i_ = IndexConstants.FIRST_INDEX;
        while (_read.hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            int rowsGrId_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            ResultText r_ = new ResultText();
            r_.buildIdAna(attribute_, rowsGrId_, _anaDoc, _page);
            opExp.addEntry(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringMap<ResultText> getOpExp() {
        return opExp;
    }
}
