package code.bean.help.analyze.blocks;

import code.bean.help.analyze.HelpResultText;
import code.bean.nat.analyze.blocks.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class HelpAnaRendElement extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element read;
    private final StringMap<HelpResultText> attributes = new StringMap<HelpResultText>();

    HelpAnaRendElement(Element _elt) {
        super();
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = NatAnaRendElement.buildAttrNames(_anaDoc,read);
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        attributesNames_.removeAllString(prefGr_);
        processAttributes(_doc,read,attributesNames_, _anaDoc, _page);
        for (String a: attributesNames_) {
            String attr_ = read.getAttribute(a);
            HelpResultText r_ = new HelpResultText();
            r_.buildIdAna(attr_);
            attributes.addEntry(a,r_);
        }
    }

    protected abstract void processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page);

    public final Element getRead() {
        return read;
    }

    public StringMap<HelpResultText> getAttributes() {
        return attributes;
    }

}
