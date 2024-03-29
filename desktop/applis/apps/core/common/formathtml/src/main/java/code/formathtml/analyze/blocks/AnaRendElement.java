package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AnaRendElement extends AnaRendParentBlock implements AnaRendBuildEl {
    private final Element read;
    private final StringMap<ResultExpression> attributes = new StringMap<ResultExpression>();
    private final StringMap<ResultExpression> attributesText = new StringMap<ResultExpression>();
    AnaRendElement(Element _elt, int _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        for (EntryCust<String,ResultExpression> e: attributesText.entryList()) {
            _page.zeroOffset();
            _page.setSumOffset(e.getValue().getSumOffset());
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
        for (EntryCust<String,ResultExpression> e: attributes.entryList()) {
            _page.zeroOffset();
            _page.setSumOffset(e.getValue().getSumOffset());
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
    }
    public static OperationNode getRootAnalyzedOperations(int _index, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, ResultExpression _res) {
        if (_res.getAnalyzedString().trim().isEmpty()) {
            return null;
        }
        return RenderAnalysis.getRootAnalyzedOperations(_index, _anaDoc, _page, _res);
    }

    public StringList params(AnalyzingDoc _anaDoc) {
        StringList list_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.add(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        return list_;
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
        if (getParent() instanceof AnaRendDocumentBlock) {
            String bean_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrAlias());
            attributesNames_.removeAllString(bean_);
        }
        return attributesNames_;
    }

    public abstract StringList processListAttributes(AnalyzingDoc _anaDoc, AnalyzedPageEl _page);

    public final Element getRead() {
        return read;
    }

    public StringMap<ResultExpression> getAttributes() {
        return attributes;
    }

    public StringMap<ResultExpression> getAttributesText() {
        return attributesText;
    }
}
