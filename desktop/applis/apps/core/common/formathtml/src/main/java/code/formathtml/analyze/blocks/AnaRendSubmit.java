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

    private final StringMap<ResultText> opExp = new StringMap<ResultText>();

    private StringMap<String> preformatted;
    AnaRendSubmit(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrMessage());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            return;
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        for (EntryCust<String,ResultText> e: opExp.entryList()) {
            String attribute_ = _read.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            e.getValue().buildIdAna(attribute_, rowsGrId_, _anaDoc, _page);
        }
    }
    public StringList titles(AnalyzingDoc _anaDoc) {
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        StringMap<String> href_ = getPreQuick(value_, _anaDoc);
        if (href_.isEmpty()) {
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
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrMessage());
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        StringMap<String> preQuick_ = getPreQuick(value_, _anaDoc);
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        if (preQuick_.isEmpty()) {
            return list_;
        }
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
        return list_;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringMap<ResultText> getOpExp() {
        return opExp;
    }
}
