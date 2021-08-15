package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AnaRendElement extends AnaRendParentBlock implements AnaRendBuildEl {
    private final Element read;
    private final StringMap<ResultText> attributes = new StringMap<ResultText>();
    private final StringMap<ResultText> attributesText = new StringMap<ResultText>();
    AnaRendElement(Element _elt, int _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        attributesNames_.removeAllString(_anaDoc.getRendKeyWords().getAttrId());
        String id_ = read.getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText r_ = new ResultText();
            int off_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrId());
            r_.buildAna(id_, off_, _anaDoc, _page);
            attributesText.put(_anaDoc.getRendKeyWords().getAttrId(),r_);
        }
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        attributesNames_.removeAllString(prefGr_);
        String groupId_ = read.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText r_ = new ResultText();
            int off_ = getAttributeDelimiter(prefGr_);
            r_.buildIdAna(groupId_, off_, _anaDoc, _page);
            attributesText.put(prefGr_,r_);
        }
        processAttributes(_doc,read,attributesNames_, _anaDoc, _page);
        for (String a: attributesNames_) {
            String attr_ = read.getAttribute(a);
            if (attr_.trim().isEmpty()) {
                continue;
            }
            ResultText r_ = new ResultText();
            int rowsGrId_ = getAttributeDelimiter(a);
            r_.buildIdAna(attr_, rowsGrId_, _anaDoc, _page);
            attributes.addEntry(a,r_);
        }
    }

    protected abstract void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);

    public final Element getRead() {
        return read;
    }

    public StringMap<ResultText> getAttributes() {
        return attributes;
    }

    public StringMap<ResultText> getAttributesText() {
        return attributesText;
    }
}
