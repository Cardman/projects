package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class HelpAnaRendStdElement extends HelpAnaRendElement {
    HelpAnaRendStdElement(Element _elt) {
        super(_elt);
    }

    @Override
    protected void processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean()));
    }
}
