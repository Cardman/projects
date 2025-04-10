package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.EncodedChar;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendSubmit extends AnaRendElement implements AnaRendElementAttr {

    private final StringMap<ResultExpression> opExp = new StringMap<ResultExpression>();

    private StringMap<String> preformatted;
    AnaRendSubmit(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrMessage());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            return;
        }
        CustList<EncodedChar> chs_ = DocumentBuilder.build(_doc.getEscapedChars());
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(0, e.getValue(), chs_, new IntTreeMap<Integer>()));
        }
        for (EntryCust<String,ResultExpression> e: opExp.entryList()) {
            _page.setSumOffset(e.getValue().getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
    }
    public StringList titles(AnalyzingDoc _anaDoc) {
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        StringMap<String> href_ = getPreQuick(value_, _anaDoc);
        if (href_.isEmpty()) {
            return new StringList();
        }
        return AnaRendLink.paramsList(_anaDoc,getRead());
    }

    @Override
    public StringList processListAttributes(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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

    public StringMap<ResultExpression> getOpExp() {
        return opExp;
    }
}
