package code.bean.nat.analyze.blocks;

import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendLink extends NatAnaRendElement {
    private String content;

    NatAnaRendLink(Element _elt) {
        super(_elt);
    }

    @Override
    protected StringList processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrRel());
        String href_ = AnaRendBlockHelp.getCssHref(_read, _anaDoc.getRendKeyWords());
        StringMap<String> files_ = _anaDoc.getFiles();
        content = files_.getVal(href_);
        return _list;
    }

    public String getContent() {
        return content;
    }

}
