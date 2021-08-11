package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendStdInput extends NatAnaRendInput {
    NatAnaRendStdInput(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(_read, _anaDoc, _page);
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrChecked());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrClassName()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValidator()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
    }
}
