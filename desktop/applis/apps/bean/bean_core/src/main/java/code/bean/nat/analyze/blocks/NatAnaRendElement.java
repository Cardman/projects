package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class NatAnaRendElement extends AnaRendParentBlock implements NatRendBuildEl {
    private final Element read;
    private final StringMap<NatResultText> attributes = new StringMap<NatResultText>();
    private final StringMap<NatResultText> attributesText = new StringMap<NatResultText>();
    NatAnaRendElement(Element _elt, int _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = buildAttrNames(_anaDoc, read);
        String id_ = read.getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            NatResultText r_ = new NatResultText();
            r_.buildAna(id_, _anaDoc, _page);
            attributesText.put(_anaDoc.getRendKeyWords().getAttrId(),r_);
        }
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        attributesNames_.removeAllString(prefGr_);
        String groupId_ = read.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            NatResultText r_ = new NatResultText();
            r_.buildIdAna(groupId_, _anaDoc, _page);
            attributesText.put(prefGr_,r_);
        }
        processAttributes(_doc,read,attributesNames_, _anaDoc, _page);
        for (String a: attributesNames_) {
            String attr_ = read.getAttribute(a);
            NatResultText r_ = new NatResultText();
            r_.buildIdAna(attr_, _anaDoc, _page);
            attributes.addEntry(a,r_);
        }
    }

    public static StringList buildAttrNames(AnalyzingDoc _anaDoc, Element _read) {
        StringList attributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = _read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        attributesNames_.removeAllString(_anaDoc.getRendKeyWords().getAttrId());
        return attributesNames_;
    }

    protected abstract void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page);

    public final Element getRead() {
        return read;
    }

    public StringMap<NatResultText> getAttributes() {
        return attributes;
    }

    public StringMap<NatResultText> getAttributesText() {
        return attributesText;
    }
}
