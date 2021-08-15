package code.bean.help.analyze.blocks;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.blocks.NatRendBuildEl;
import code.bean.help.analyze.HelpResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class HelpAnaRendElement extends AnaRendParentBlock implements NatRendBuildEl {
    private final Element read;
    private final StringMap<HelpResultText> attributes = new StringMap<HelpResultText>();

    HelpAnaRendElement(Element _elt, int _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        attributesNames_.removeAllString(_anaDoc.getRendKeyWords().getAttrId());
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

    protected abstract void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page);

    public final Element getRead() {
        return read;
    }

    public StringMap<HelpResultText> getAttributes() {
        return attributes;
    }

}
