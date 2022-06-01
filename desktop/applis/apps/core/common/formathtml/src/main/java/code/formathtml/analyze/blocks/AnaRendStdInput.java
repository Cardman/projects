package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendStdInput extends AnaRendInput implements AnaRendElementAttr {
    AnaRendStdInput(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(getRead(), _anaDoc, _page);
    }
    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrChecked());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrClassName()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValidator()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
        return list_;
    }
}
