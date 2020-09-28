package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendStdInput extends AnaRendInput {
    AnaRendStdInput(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(_cont, _read, _anaDoc, _page);
        _list.removeAllString(_cont.getRendKeyWords().getAttrChecked());
        _list.removeAllString(_cont.getRendKeyWords().getAttrValue());
        _list.removeAllString(_cont.getRendKeyWords().getAttrName());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrClassName()));
        _list.removeAllString(_cont.getRendKeyWords().getAttrNi());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()));
        _list.removeAllString(_cont.getRendKeyWords().getAttrType());
    }
}
