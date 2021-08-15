package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class HelpAnaRendStdElement extends HelpAnaRendElement {
    HelpAnaRendStdElement(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean()));
    }
}
