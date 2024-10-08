package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class NatAnaRendElement extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element read;
    private final StringMap<NatResultText> attributes = new StringMap<NatResultText>();
    private final AbstractNatBlockBuilder bu;
    protected NatAnaRendElement(Element _elt, AbstractNatBlockBuilder _builder) {
        read = _elt;
        bu = _builder;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = buildAttrNames(_anaDoc, read);
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrGroupId());
        attributesNames_.removeAllString(prefGr_);
//        if (this instanceof NatAnaRendImg) {
//            ((NatAnaRendImg)this).img(read,attributesNames_, _anaDoc, _page);
//        } else
        if (this instanceof NatAnaRendLink) {
            ((NatAnaRendLink)this).link(read,attributesNames_, _anaDoc);
        }
        if (this instanceof NatAnaRendStdElement) {
            ((NatAnaRendStdElement)this).sdtElement(attributesNames_, _anaDoc);
        }
        attributes(_anaDoc, _page, attributesNames_);
    }

    protected void attributes(NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page, StringList _attributesNames) {
        for (String a: _attributesNames) {
            String attr_ = read.getAttribute(a);
            NatResultText r_ = bu.newNatResultText();
            r_.buildIdAna(attr_, _anaDoc, _page);
            attributes.addEntry(a,r_);
        }
    }

    public static StringList buildAttrNames(NatAnalyzingDoc _anaDoc, Element _read) {
        StringList attributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = _read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        attributesNames_.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrId());
        return attributesNames_;
    }

    public final Element getRead() {
        return read;
    }

    public StringMap<NatResultText> getAttributes() {
        return attributes;
    }

}
