package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendLink extends NatAnaRendElement {
    private String content;

    NatAnaRendLink(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrRel());
        String href_ = AnaRendBlockHelp.getCssHref(_read, _anaDoc.getRendKeyWords());
        StringMap<String> files_ = _anaDoc.getFiles();
        content = files_.getVal(href_);
    }

    public String getContent() {
        return content;
    }

}
