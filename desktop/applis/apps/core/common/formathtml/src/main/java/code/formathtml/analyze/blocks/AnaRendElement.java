package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.EntryCust;
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
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            String attr_ = read.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            e.getValue().buildIdAna(attr_, rowsGrId_, _anaDoc, _page);
        }
        processAttributes(_doc,read, _anaDoc, _page);
        for (EntryCust<String,ResultText> e: attributes.entryList()) {
            String attr_ = read.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            e.getValue().buildIdAna(attr_, rowsGrId_, _anaDoc, _page);
        }
    }
    public StringList attrList(AnalyzingDoc _anaDoc){
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
        return attributesNames_;
    }

    public abstract StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);
    protected abstract void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);

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
