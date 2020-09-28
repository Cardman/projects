package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendSubmit extends AnaRendElement {

    private StringMap<ResultText> opExp;

    private StringMap<String> preformatted;
    AnaRendSubmit(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrMessage());
        String value_ = _read.getAttribute(_cont.getRendKeyWords().getAttrMessage());
        int offMessage_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrMessage());
        preformatted = getPre(_cont,value_,offMessage_, _anaDoc, _page);
        opExp = new StringMap<ResultText>();
        if (preformatted.isEmpty()) {
            removeUseLess(_cont,_read, _list);
            return;
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_cont.getRendKeyWords().getAttrEscapedAmp())));
        }
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            ResultText r_ = new ResultText();
            r_.buildAna(attribute_, rowsGrId_, _anaDoc, _page);
            opExp.addEntry(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)),r_);
            i_++;
        }
        _list.removeAllString(_cont.getRendKeyWords().getAttrValue());
        _list.removeAllString(_cont.getRendKeyWords().getAttrType());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringMap<ResultText> getOpExp() {
        return opExp;
    }
}
