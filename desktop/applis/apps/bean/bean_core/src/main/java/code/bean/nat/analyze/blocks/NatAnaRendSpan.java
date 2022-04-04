package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatAnaRendSpan extends NatAnaRendElement {
    private StringMap<String> formatted=new StringMap<String>();
    private CustList<NatOperationNode> roots;
    private StringList texts;

    NatAnaRendSpan(Element _elt) {
        super(_elt);
    }

    @Override
    protected void processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        String id_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        NatResultText result_ = new NatResultText();
        result_.buildIdAna(id_, _anaDoc, _page);
        roots = result_.getOpExpRoot();
        texts = result_.getTexts();
        for (String l: _anaDoc.getLanguages()) {
            formatted.addEntry(l, AnaRendBlockHelp.EMPTY_STRING);
        }
        String valueMessage_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        formatted = AnaRendBlockHelp.getPre(valueMessage_, _anaDoc);
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringMap<String> getFormatted() {
        return formatted;
    }
}
